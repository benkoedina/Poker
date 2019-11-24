package com.example.poker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.poker.R;

import androidx.fragment.app.Fragment;

public class QuestionListFragment extends Fragment {

    public QuestionListFragment(){};

    public void Oncreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_question_list, container, false);

        return view;
    }

}
