package com.example.poker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.poker.Adapter.VoteRecyclerViewAdapter;
import com.example.poker.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionFragment extends Fragment {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    static int position = 0;
    VoteRecyclerViewAdapter adapter;
    public QuestionFragment()
    {
        //required empty constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //setting te layout
        View view = inflater.inflate(R.layout.fragment_question, container, false);

        Button bt_submit = view.findViewById(R.id.submit);


        final String[] data = {"0", "3", "6", "9", "12", "15", "18", "21", "24","27","30","33"};

        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        // set up the RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvNumbers);
        int numberOfColumns = 4;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numberOfColumns));
        adapter = new VoteRecyclerViewAdapter(getContext(), data,0);

        recyclerView.setAdapter(adapter);

        Bundle bundle = this.getArguments();
        final String groupid = bundle.getString("groupid");
        final String name = bundle.getString("name");

        //Log.d("tags", groupid + " "+ name);
        TextView question_tv = view.findViewById(R.id.Question_textview);
        Button submit_button = view.findViewById(R.id.submit);


        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, new AnswerGroup());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;


    }
}