package com.example.poker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.poker.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class QuestionFragment extends Fragment {

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