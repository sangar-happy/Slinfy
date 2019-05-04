package com.example.challenge1.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.challenge1.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
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
public class LoginSignupFragment extends Fragment implements View.OnClickListener {

    private Button verifyCode;
    private TextInputEditText phoneNumber, verificationCode;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String verificationID;

	private static final String TAG = "GoogleActivity";
	private static final int RC_SIGN_IN = 9001;

	private FirebaseAuth mAuth;
	private GoogleSignInClient mGoogleSignInClient;

	public LoginSignupFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_login_signup, container, false);

		verifyCode = view.findViewById(R.id.verify_code);
        verifyCode.setOnClickListener(this);

		phoneNumber = view.findViewById(R.id.phone_number);
		verificationCode = view.findViewById(R.id.verification_code);

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {}

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationID = s;
                verifyCode.setText("Verify Code");
            }
        };

        view.findViewById(R.id.googleSignInButton).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

		return view;
	}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        FirebaseApp.initializeApp(context);
        userLoggedIn();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    private void startPhoneNumberVerification() {
        String number = phoneNumber.getText().toString();

        if(number.length() != 0) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    number,
                    60,
                    TimeUnit.SECONDS,
                    getActivity(),
                    callbacks);
        } else {
            Toast.makeText(getActivity(), "Enter a phone number", Toast.LENGTH_SHORT).show();
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user != null) {
                        final DatabaseReference usersDbReference = FirebaseDatabase.getInstance().getReference().child("user").child(user.getUid());
                        usersDbReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(!dataSnapshot.exists()) {
                                    Map<String, Object> userMap = new HashMap<>();
                                    userMap.put("phone", user.getPhoneNumber());
                                    userMap.put("name", user.getDisplayName());

                                    usersDbReference.updateChildren(userMap);
                                }
                                userLoggedIn();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {}
                        });
                    }

                }
            }
        });
    }

    private void verifyPhoneNumberWithCode() {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, verificationCode.getText().toString());
        signInWithPhoneAuthCredential(credential);

    }

    private void userLoggedIn() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Log.d(TAG, "Checking if user is logged in");
        if(user != null) {
            //start new activity
            //TODO send message to main activity to update UI
            Toast.makeText(getContext(), "User Identified", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "User is not logged in");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verify_code:
                if(verificationID != null)
                    verifyPhoneNumberWithCode();
                else
                    startPhoneNumberVerification();
                break;
        }
    }
}
