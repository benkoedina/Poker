package com.example.poker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

import com.example.poker.Fragments.LoginFragment;
import com.example.poker.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //firebaseDatabase = FirebaseDatabase.getInstance();
      //  databaseReference = firebaseDatabase.getReference("user");
       // User user = new User(1,"Edina",  0);
      //  User user1 = new User(2,"Matyi",1);
     //   databaseReference.setValue(user1);



        if (savedInstanceState == null)
        {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.container, new LoginFragment()).commit();
        }

    }
}
