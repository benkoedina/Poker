package com.example.poker.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.poker.Adapter.AnswerListAdapter;
import com.example.poker.R;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AnswerListFragment extends Fragment {
    AnswerListAdapter adapter;
    RecyclerView recyclerView;

    public AnswerListFragment(){};

    //AnswerListFragment => kilistazza az osszes usert es a valaszaikat az adott kerdesre

    public void Oncreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(
                R.layout.fragment_answer_user, container, false);

        Bundle bundle = this.getArguments();
        ArrayList<String> userAnswers = bundle.getStringArrayList("answers");


        //RecyclerView es adapter megvalositas
        recyclerView = (RecyclerView)view.findViewById(R.id.answerrecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL)); //elem elvalaszto
        adapter = new AnswerListAdapter(userAnswers,getContext());

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return  view;
    }
}
