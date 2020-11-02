package com.app.ticketsupport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.ticketsupport.R;
import com.app.ticketsupport.models.TicketModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView Adapter for show List of Answer in Answer Activity
 */

public class AnswerListAdapter extends RecyclerView.Adapter<AnswerListAdapter.AnswerVH> {

    private Context context;
    private List<String> answers;
    private LayoutInflater inflater;
    private String userName;



    public AnswerListAdapter(String userName,Context context, List<String> answers) {
        this.context = context;
        this.answers = answers;
        this.userName = userName;
        inflater = LayoutInflater.from(context);

    }



    @NonNull
    @Override
    public AnswerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.answer_row,null);
        return new AnswerVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswerVH holder, int position) {

        String answer = answers.get(position);
        holder.mAnswer.setText(answer);
        if(!answer.startsWith(userName+": ")){
            holder.mAnswer.setBackground(context.getDrawable(R.drawable.answered_ticket));
        }else {
            holder.mAnswer.setBackground(context.getDrawable(R.drawable.waiting_for_answer));

        }


    }

    @Override
    public int getItemCount() {
        return answers.size();
    }

    public class AnswerVH extends RecyclerView.ViewHolder {

        @BindView(R.id.answer)
        AppCompatTextView mAnswer;


        public AnswerVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
