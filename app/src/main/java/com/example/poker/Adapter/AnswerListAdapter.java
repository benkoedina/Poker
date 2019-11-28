package com.example.poker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.poker.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AnswerListAdapter extends RecyclerView.Adapter<AnswerListAdapter.ViewHolder> {

    private List<String > answerList;
    private Context context;



    public AnswerListAdapter(List<String> answerList, Context context) {
        this.answerList=answerList;
        this.context = context;

    }


    @NonNull
    @Override
    public AnswerListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.answer_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AnswerListAdapter.ViewHolder holder, final int position) {

        final String myAnswer= answerList.get(position);
        holder.textView.setText(myAnswer);



    }
    @Override
    public int getItemCount() {
        return answerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public LinearLayout linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);

            this.textView = (TextView) itemView.findViewById(R.id.tv_answer);
            linearLayout =itemView.findViewById(R.id.linear_layout2);
        }
    }


}
