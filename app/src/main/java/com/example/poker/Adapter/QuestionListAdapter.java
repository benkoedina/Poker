package com.example.poker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.poker.Interface.onClickInterface;
import com.example.poker.Model.Question;
import com.example.poker.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.ViewHolder> {

    private List<Question> questionList;
    private Context context;
    onClickInterface onClickInterface;


    public QuestionListAdapter(List<Question> questionList, Context context, com.example.poker.Interface.onClickInterface onClickInterface) {
        this.questionList=questionList;
        this.context = context;
        this.onClickInterface = onClickInterface;
    }


    @NonNull
    @Override
    public QuestionListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.question_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionListAdapter.ViewHolder holder, final int position) {

        final Question myQuestion= questionList.get(position);
        holder.textView.setText(myQuestion.getQuestion());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClickInterface.setClick(position);

            }

        });


    }
    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            this.textView = (TextView) itemView.findViewById(R.id.tv_question);
            linearLayout =itemView.findViewById(R.id.linear_layout1);
        }
    }


}