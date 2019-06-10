package com.example.challenge1.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.challenge1.R;
import com.example.challenge1.ViewModifier;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneAuthFragment extends Fragment implements View.OnClickListener {

    private Callbacks fragmentCallbacks;
    private ViewModifier viewModifier;

    private Button verifyCode;
    private TextInputEditText phoneNumber, otp, getName;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String sentOtp;

    private static final String TAG = "PhoneAuthFragment";

    public PhoneAuthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phone_auth, container, false);

        verifyCode = view.findViewById(R.id.verify_code);
        verifyCode.setOnClickListener(this);

        phoneNumber = view.findViewById(R.id.phone_number);
        otp = view.findViewById(R.id.otp);
        getName = view.findViewById(R.id.name);

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                sentOtp = s;
                verifyCode.setText("Verify");
            }
        };

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof Callbacks)) {
            throw new RuntimeException("Context must implement callbacks");
        }
        fragmentCallbacks = (Callbacks) context;

        viewModifier = (ViewModifier) context;
        viewModifier.setTitleInterface("Login");

        FirebaseApp.initializeApp(context);
        userLoggedIn();
    }

    public interface Callbacks {
        void updateUser(FirebaseUser user);
    }

    private void startPhoneNumberVerification() {
        String number = "+91" + phoneNumber.getText().toString();

        if (phoneNumber.length() == 10) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    number,
                    60,
                    TimeUnit.SECONDS,
                    getActivity(),
                    callbacks);
        } else {
            Toast.makeText(getActivity(), "Enter valid number", Toast.LENGTH_SHORT).show();
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user != null) {
                        final DatabaseReference usersDbReference = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
                        usersDbReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (!dataSnapshot.exists()) {
                                    Map<String, Object> userMap = new HashMap<>();
                                    userMap.put("phone", user.getPhoneNumber());
                                    userMap.put("name", getName.getText().toString());

                                    usersDbReference.updateChildren(userMap);
                                }
                                userLoggedIn();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        });
                    }

                }
            }
        });
    }

    private void verifyPhoneNumberWithCode() {
        if (otp != null && otp.getText().length() == 6) {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(sentOtp, otp.getText().toString());
            signInWithPhoneAuthCredential(credential);
        } else {
            Toast.makeText(getContext(), "Enter Otp", Toast.LENGTH_SHORT).show();
        }
    }

    private void userLoggedIn() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d(TAG, "Checking if user is logged in");
        if (user != null) {
            //send message to main activity to update UI
            Toast.makeText(getContext(), "Welcome " + getName.getText(), Toast.LENGTH_LONG).show();

        } else {
            Log.d(TAG, "User is not logged in");
        }
        fragmentCallbacks.updateUser(user);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verify_code:
                if (sentOtp != null)
                    verifyPhoneNumberWithCode();
                else if (getName.getText().length() == 0) {
                    Toast.makeText(getContext(), "Name is required.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phoneNumber != null) startPhoneNumberVerification();
                else
                    Toast.makeText(getContext(), "Phone number required.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
