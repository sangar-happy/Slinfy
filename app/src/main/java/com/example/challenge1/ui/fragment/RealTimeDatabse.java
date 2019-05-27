package com.example.challenge1.ui.fragment;

//import android.content.Context;
//import android.net.Uri;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import com.example.challenge1.R;
//import com.example.challenge1.ui.adapter.ContactListAdapter;
//import com.example.challenge1.database.entity.User;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.ArrayList;
//
///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link RealTimeDatabse.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link RealTimeDatabse#newInstance} factory method to
// * create an instance of this fragment.
// */
//
//public class RealTimeDatabse extends Fragment {
//
//    private RecyclerView recyclerView;
//    private RecyclerView.Adapter userListAdapter;
//
//    ArrayList<User> userList;
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String firebseUserId = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private FirebaseUser user;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//    private DatabaseReference users;
//
//    public RealTimeDatabse() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param firebaseUser Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment RealTimeDatabse.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static RealTimeDatabse newInstance(FirebaseUser firebaseUser, String param2) {
//        RealTimeDatabse fragment = new RealTimeDatabse();
//        Bundle args = new Bundle();
//        args.putString(firebseUserId, firebaseUser.getUid());
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            //mParam1 = getArguments().getString(firebseUserId);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        recyclerView = container.findViewById(R.id.userList);
//        writeNewUser();
//
////        users = FirebaseDatabase.getInstance().getReference().child("users");
////
////        users.child("FirstUser").child("username").setValue("harpreet");
//
////        ValueEventListener postListener = new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                // Get Post object and use the values to update the UI
////                Post post = dataSnapshot.getValue(Post.class);
////                // ...
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////                // Getting Post failed, log a message
////                Log.w("RealTimeDatabase", "loadPost:onCancelled", databaseError.toException());
////                // ...
////            }
////        };
////        mDatabase.addValueEventListener(postListener);
//
//
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_real_time_databse, container, false);
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//
//    private void writeNewUser() {
//
//        DatabaseReference usersDbRef = FirebaseDatabase.getInstance().getReference().child("users");
//
//        usersDbRef.child("name").setValue("harpreet").addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful()) Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
//                else Toast.makeText(getContext(), "Unccessful", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
////        User user = new User("Harpreet", "sangarhappy@gmail.com");
////        users.child(userId).setValue(user)
////                .addOnSuccessListener(new OnSuccessListener<Void>() {
////                    @Override
////                    public void onSuccess(Void aVoid) {
////                        // Write was successful!
////                        // ...
////                    }
////                })
////                .addOnFailureListener(new OnFailureListener() {
////                    @Override
////                    public void onFailure(@NonNull Exception e) {
////                        // Write failed
////                        // ...
////                    }
////                });
//
//    private void initializeRecyclerView() {
//        recyclerView.setNestedScrollingEnabled(false);
//        recyclerView.setHasFixedSize(false);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        userListAdapter = new ContactListAdapter(userList);
//        recyclerView.setAdapter(userListAdapter);
//    }
//}
