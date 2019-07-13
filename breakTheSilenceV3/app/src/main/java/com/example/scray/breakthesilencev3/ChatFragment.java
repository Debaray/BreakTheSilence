package com.example.scray.breakthesilencev3;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import com.example.scray.breakthesilencev3.Fragments.ChatsFragment;
import com.example.scray.breakthesilencev3.Fragments.FriendFragment;
import com.example.scray.breakthesilencev3.Fragments.UsersFragment;
import com.example.scray.breakthesilencev3.Model.Chat;

import java.util.HashMap;


public class ChatFragment extends Fragment {


    FirebaseUser firebaseUser;
    DatabaseReference reference;
   private int unread = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        final TabLayout tabLayout = view.findViewById(R.id.tabs);
        final ViewPager viewPager = view.findViewById(R.id.container);


        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getReceiver().equals(firebaseUser.getUid()) && !chat.isIsseen()){
                        unread++;
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(),unread);

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Chat");
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        int unread;

        public ViewPagerAdapter(FragmentManager fm){
            super(fm);

        }

        public ViewPagerAdapter(FragmentManager fm, int unread) {
            super(fm);
            this.unread = unread;
        }

        @Override
        public Fragment getItem(int position) {

            switch(position) {
                case 0:

                    ChatsFragment chatsFragment = new ChatsFragment();
                    return  chatsFragment;

                case 1:
                    UsersFragment requestsFragment = new UsersFragment();

                    return requestsFragment;

                case 2:
                    UserFriendsFragment friendsFragment = new UserFriendsFragment();

                    return friendsFragment;

                default:
                    return  null;
            }
        }

        @Override
        public int getCount() {

            return 3;
        }

        // Ctrl + O

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    if(unread == 0)
                    {
                        return "Chats";
                    }
                    else
                    {
                        return "("+unread+")Chats";
                    }

                case 1:
                    return "Users";

                case 2:
                    return "Favourite Users";

                default:
                    return null;
            }
        }
    }

    private void status(String status){
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", status);

        reference.updateChildren(hashMap);
        setOnline(status);
    }

    private void setOnline(String status) {

        if(status.equals("true"))
        {
            reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
            reference.child("online").setValue(100000);
        }
        else if(status.equals("false"))
        {
            reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
            reference.child("online").setValue(ServerValue.TIMESTAMP);
        }


    }

    @Override
    public void onResume() {

        super.onResume();
        status("true");
    }

    @Override
    public void onPause() {
        super.onPause();
        status("false");
    }
}
