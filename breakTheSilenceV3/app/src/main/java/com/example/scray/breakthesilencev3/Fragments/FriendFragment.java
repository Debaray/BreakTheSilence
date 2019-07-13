package com.example.scray.breakthesilencev3.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scray.breakthesilencev3.Friends;
import com.example.scray.breakthesilencev3.MessageActivity;
import com.example.scray.breakthesilencev3.Model.User;
import com.example.scray.breakthesilencev3.ProfileActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class FriendFragment extends Fragment {

    private RecyclerView mFriendsList;

    private DatabaseReference mFriendsDatabase;
    private DatabaseReference mUsersDatabase;

    private FirebaseAuth mAuth;

    private String mCurrent_user_id;

    private View mMainView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMainView = inflater.inflate(R.layout.fragment_friend, container, false);

        mFriendsList =  mMainView.findViewById(R.id.recycler_view );
        mAuth = FirebaseAuth.getInstance();

        mCurrent_user_id = mAuth.getCurrentUser().getUid();

        mFriendsDatabase = FirebaseDatabase.getInstance().getReference().child("Friends").child(mCurrent_user_id);
        mFriendsDatabase.keepSynced(true);
        mUsersDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mUsersDatabase.keepSynced(true);

        mFriendsList.setHasFixedSize(true);
        mFriendsList.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inflate the layout for this fragment
        return mMainView;
    }

//    private void searchUsers(String s) {
//
//        final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
//        Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild("search")
//                .startAt(s)
//                .endAt(s+"\uf8ff");
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                try{
//
//                    mUsers.clear();
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                        User user = snapshot.getValue(User.class);
//
//                        assert user != null;
//                        assert fuser != null;
//                        if (!user.getId().equals(fuser.getUid())){
//                                mUsers.add(user);
//                        }
//                    }
//
//                    userAdapter = new UserAdapter(getContext(), mUsers, false);
//                    recyclerView.setAdapter(userAdapter);
//
//                }catch (Exception ex)
//                {
//                    Log.d("Exception",ex.toString());
//                    Toast.makeText(getContext(),"Error in user",Toast.LENGTH_LONG).show();
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//    }

//    private void readUsers() {
//
//        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (search_users.getText().toString().equals("")) {
//                    mUsers.clear();
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        User user = snapshot.getValue(User.class);
//
//                        Double latitudeUser = Double.parseDouble(user.getLatitude());
//                        Double longitudeUser = Double.parseDouble(user.getLongitude()) ;
//
//                        Double latres = latitudeUser-latitudeCurrent;
//                        Double longres = longitudeUser - longitudeCurrent;
//
//                        Double latlongres = Math.sqrt((Math.pow(latres,2))+ (Math.pow(longres,2)));
//                        if(latlongres < 0.5)
//                        {
//                            mUsers.add(user);
//                        }
//                    }
//                    userAdapter = new UserAdapter(getContext(), mUsers, true);
//                    recyclerView.setAdapter(userAdapter);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Friends, FriendFragment.FriendsViewHolder> friendsRecyclerViewAdapter = new FirebaseRecyclerAdapter<Friends, FriendFragment.FriendsViewHolder>(

                Friends.class,
                R.layout.user_item,
                FriendFragment.FriendsViewHolder.class,
                mFriendsDatabase


        ) {

            protected void populateViewHolder(final FriendFragment.FriendsViewHolder friendsViewHolder, Friends friends, int i) {

                //  friendsViewHolder.setDate(friends.getDate());

                final String list_user_id = getRef(i).getKey();

                mUsersDatabase.child(list_user_id).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        final String userName = dataSnapshot.child("username").getValue().toString();
                        final String userStatus = dataSnapshot.child("userStatus").getValue().toString();
                        String userThumb = dataSnapshot.child("thumb_image").getValue().toString();

                        if(dataSnapshot.hasChild("status")) {

                            String userOnline = dataSnapshot.child("status").getValue().toString();
                            friendsViewHolder.setUserOnline(userOnline);

                        }

                        friendsViewHolder.setName(userName);
                        friendsViewHolder.setStatus(userStatus);
                        friendsViewHolder.setUserImage(userThumb, getContext());

                        friendsViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                CharSequence options[] = new CharSequence[]{"Open Profile", "Send message"};

                                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                                builder.setTitle("Select Options");
                                builder.setItems(options,  new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        //Click Event for each item.
                                        if(i == 0){

                                            Intent profileIntent = new Intent(getContext(), ProfileActivity.class);
                                            profileIntent.putExtra("userid", list_user_id);
                                            startActivity(profileIntent);

                                        }

                                        if(i == 1){

                                            Intent chatIntent = new Intent(getContext(), MessageActivity.class);
                                           chatIntent.putExtra("userid", list_user_id);
                                            //chatIntent.putExtra("user_name", userName);
                                            startActivity(chatIntent);

                                        }

                                    }
                                });

                                builder.show();

                            }
                        });


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        };

        mFriendsList.setAdapter(friendsRecyclerViewAdapter);


    }


    public static class FriendsViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public FriendsViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setDate(String date){

            //TextView userStatusView = (TextView) mView.findViewById(R.id.user_single_status);
            // userStatusView.setText(date);

        }
        public void setStatus(String status){

            TextView userStatusView = (TextView) mView.findViewById(R.id.last_msg);
            userStatusView.setText(status);

        }

        public void setName(String name){

            TextView userNameView = (TextView) mView.findViewById(R.id.username);
            userNameView.setText(name);

        }

        public void setUserImage(String thumb_image, Context ctx){

            CircleImageView userImageView = (CircleImageView) mView.findViewById(R.id.profile_image);
            Picasso.with(ctx).load(thumb_image).placeholder(R.drawable.default_avatar).into(userImageView);

        }

        public void setUserOnline(String online_status) {

            ImageView img_on = (ImageView) mView.findViewById(R.id.img_on);
            ImageView img_off = (ImageView) mView.findViewById(R.id.img_off);

            if(online_status.equals("true")){

                img_on.setVisibility(View.VISIBLE);
                img_off.setVisibility(View.GONE);


            } else {

                img_on.setVisibility(View.GONE);
                img_off.setVisibility(View.VISIBLE);

            }

        }


    }

}
