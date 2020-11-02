package com.app.ticketsupport.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.ticketsupport.R;
import com.app.ticketsupport.models.TicketModel;
import com.app.ticketsupport.ui.ticket.TicketClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView Adapter for show List of user Tickets in Ticket Fragment
 */

public class TicketListAdapter extends RecyclerView.Adapter<TicketListAdapter.TicketVH> {



    private Context context;
    private List<TicketModel> ticketModels;
    private LayoutInflater inflater;
    private static TicketClickListener clickListener;



    public TicketListAdapter(Context context, List<TicketModel> ticketModels) {
        this.context = context;
        this.ticketModels = ticketModels;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TicketVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ticket_row,null);
        return new TicketVH(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull TicketVH holder, int position) {
        TicketModel ticketModel = ticketModels.get(position);

         String time = String.valueOf(ticketModel.getDate().toLocaleString());
         String mtype = ticketModel.getTicketType();
         switch (mtype){
             case "Waiting":
                 holder.relativeLayout.setBackground(context.getDrawable(R.drawable.waiting_ticket));
                 break;
             case "Answered":
                 holder.relativeLayout.setBackground(context.getDrawable(R.drawable.answered_ticket));

                 break;
             case "Waiting for answer":
                 holder.relativeLayout.setBackground(context.getDrawable(R.drawable.waiting_for_answer));
                 mtype = "More Info";

                 break;
             case "Completed":
                 holder.relativeLayout.setBackground(context.getDrawable(R.drawable.completed_ticket));

                 break;

         }

        holder.mTitle.setText(ticketModel.getTitle());
        holder.mMessage.setText(ticketModel.getMessage());
        holder.mDate.setText(time);
        holder.mdepartment.setText(ticketModel.getDepartment());
        holder.mAnswer_number.setText(String.valueOf(ticketModel.getAnswers().length));
        holder.mStatus.setText(mtype);
        holder.mpriority.setText(ticketModel.getPriority());


    }

    @Override
    public int getItemCount() {
        return ticketModels.size();
    }

    public class  TicketVH extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.title)
        AppCompatTextView mTitle;
        @BindView(R.id.message)
        AppCompatTextView mMessage;
        @BindView(R.id.date)
        AppCompatTextView mDate;
        @BindView(R.id.department)
        AppCompatTextView mdepartment;
        @BindView(R.id.priority)
        AppCompatTextView mpriority;
        @BindView(R.id.answer_number)
        AppCompatTextView mAnswer_number;
        @BindView(R.id.status)
        AppCompatTextView mStatus;
        @BindView(R.id.ticket_back)
        RelativeLayout relativeLayout;

        public TicketVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }
    public void setOnItemClickListener(TicketClickListener clickListener) {
        TicketListAdapter.clickListener = clickListener;
    }
}
