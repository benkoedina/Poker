package com.example.poker.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.poker.Model.Group;
import com.example.poker.Model.Question;
import com.example.poker.Model.User;
import com.example.poker.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class LoginFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public LoginFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_login, container, false);
        final  EditText et_name = view.findViewById(R.id.et_name);
        final EditText et_groupid=view.findViewById(R.id.et_groupid);
        Button bt_login = view.findViewById(R.id.bt_login);

        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        //kezdetleges adatok hozzaadasa az adabazishoz
        insertData();

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //login ellenorzes
                if (et_name.getText().toString().isEmpty() || (et_groupid.toString().isEmpty())) {

                    Toast.makeText(getContext(), "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                } else {

                    final String groupId = et_groupid.getText().toString();
                    final String name = et_name.getText().toString();

                    //megnezi hogy aktiv-e vagy nem aktiv a group ahova csatlakozni szeretne a user
                    checkGroupStatus(groupId,name);

                }

            }
        });

        return view;

    }
    public void insertData(){

        ArrayList <User> users = new ArrayList<>();

        Question question1 = new Question(0,"Rate this app!", "active", users );
        Question question2 = new Question(1,"Do you like Java?", "active", users );
        Question question3 = new Question(2,"Answer this question?", "inactive", users );
        Question question4 = new Question(3,"Next question?", "active", users );

        ArrayList <Question> questions = new ArrayList<>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);


        Group group1 = new Group("1", true, questions);
        Group group2 = new Group("2", false, questions);
        Group group3 = new Group("3", false, questions);
        Group group4 = new Group("4", true, questions);
        Group group5 = new Group("5", true, questions);
        Group group6 = new Group("6", true, questions);

        databaseReference.child(group1.getId()).setValue(group1);
        databaseReference.child(group2.getId()).setValue(group2);
        databaseReference.child(group3.getId()).setValue(group3);
        databaseReference.child(group4.getId()).setValue(group4);
        databaseReference.child(group5.getId()).setValue(group5);
        databaseReference.child(group6.getId()).setValue(group6);


    }



    public void checkGroupStatus (final String groupid, final String name)
    {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Group g = dataSnapshot.child(groupid).getValue(Group.class);
                Log.d("Proba",g.isStatus()+"");
                if (g.isStatus()==true) {
                    //ha aktiv akktor tovabb adja az ertekeket a nevet es a group-t a kovetkezo fragmentnek
                    final Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putString("groupid", groupid);

                    //fragment valtas
                    QuestionFragment Qfragment = new QuestionFragment();
                    Qfragment.setArguments(bundle);
                    FragmentTransaction fr = getFragmentManager().beginTransaction();
                    fr.replace(R.id.container, Qfragment);
                    fr.addToBackStack(null);
                    fr.commit();
                }
                else {
                    //ha nem aktiv
                    Toast.makeText(getContext(), "Group is not active!", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}