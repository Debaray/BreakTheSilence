package com.example.scray.breakthesilencev3.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scray.breakthesilencev3.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.example.scray.breakthesilencev3.Adapter.UserAdapter;

import com.example.scray.breakthesilencev3.R;

import java.util.ArrayList;
import java.util.List;


public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;

    private UserAdapter userAdapter;
    private List<User> mUsers;
    private DatabaseReference reference;
    private ProgressDialog mRegProgressDialog;
    private EditText search_users;
    private  Button refreshBtn;

    private Double latitudeCurrent,longitudeCurrent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_users, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        refreshBtn = view.findViewById(R.id.userRefresh_btn);
        mRegProgressDialog = new ProgressDialog(getContext());

        mUsers = new ArrayList<>();

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRegProgressDialog.setTitle("Searching User");
                mRegProgressDialog.setMessage("Searching Users Around Your Current Location");
                mRegProgressDialog.setCanceledOnTouchOutside(false);
                mRegProgressDialog.show();
                readUsers();
                mRegProgressDialog.hide();
                Toast.makeText(getContext(),"Refreshed",Toast.LENGTH_SHORT).show();

            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                latitudeCurrent = Double.parseDouble(dataSnapshot.child("Latitude").getValue().toString()) ;
                longitudeCurrent = Double.parseDouble(dataSnapshot.child("Longitude").getValue().toString()) ;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        readUsers();

        search_users = view.findViewById(R.id.search_users);
        search_users.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchUsers(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;
    }

    private void searchUsers(String s) {

        final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild("search")
                .startAt(s)
                .endAt(s+"\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{

                    mUsers.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        User user = snapshot.getValue(User.class);

                        assert user != null;
                        assert fuser != null;
                        if (!user.getId().equals(fuser.getUid())){

//                           Double latitudeUser = Double.parseDouble(user.getLatitude());
//                           Double longitudeUser = Double.parseDouble(user.getLongitude()) ;
//
//                           Double latres = latitudeUser-latitudeCurrent;
//                           Double longres = longitudeUser - longitudeCurrent;
//
//                           Double latlongres = Math.sqrt((Math.pow(latres,2))+ (Math.pow(longres,2)));
//                           if(latlongres < 0.5)
//                           {
//
//                           }
                            mUsers.add(user);
                        }
                    }
                    userAdapter = new UserAdapter(getContext(), mUsers, false);
                    recyclerView.setAdapter(userAdapter);

                }catch (Exception ex)
                {
                    Log.d("Exception",ex.toString());
                   // Toast.makeText(getContext(),"Error in user",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void readUsers() {

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (search_users.getText().toString().equals("")) {
                    mUsers.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);

                        assert user != null;
                        assert firebaseUser != null;
                        if (!user.getId().equals(firebaseUser.getUid())){

//                            Log.d("Location",user.getLatitude());
//                            Log.d("Location",user.getLongitude());
//                            if(user.getLatitude() != null && user.getLongitude() != null)
//                            {
//                                Double latitudeUser = Double.parseDouble(user.getLatitude().trim());
//
//                                Double longitudeUser = Double.parseDouble(user.getLongitude().trim()) ;
//
//
//                                Double latres = latitudeUser-latitudeCurrent;
//                                Log.d("Location",latres.toString());
//                                Double longres = longitudeUser - longitudeCurrent;
//                                Log.d("Location",longres.toString());
//
//                                Double latlongres = Math.sqrt((Math.pow(latres,2))+ (Math.pow(longres,2)));
//                                Log.d("Location",latlongres.toString());
//                                if(latlongres < 0.5)
//                                {
//
//                                }
//
//                            }
                            mUsers.add(user);

                        }
                    }
                    userAdapter = new UserAdapter(getContext(), mUsers, false);
                    recyclerView.setAdapter(userAdapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
