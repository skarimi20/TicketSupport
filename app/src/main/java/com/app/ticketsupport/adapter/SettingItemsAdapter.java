package com.app.ticketsupport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.ticketsupport.R;
import com.app.ticketsupport.models.SettingItemModel;
import com.app.ticketsupport.ui.setting.DarkmodeClass;
import com.app.ticketsupport.ui.ticket.TicketClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RecyclerView Adapter for show List of Setting Items in Setting Fragment
 */

public class SettingItemsAdapter extends RecyclerView.Adapter<SettingItemsAdapter.SettingVH> {

    Context context;
    List<SettingItemModel> settingItemModels;
    private LayoutInflater inflater;
    private static TicketClickListener clickListener;


    public SettingItemsAdapter(Context context, List<SettingItemModel> settingItemModels) {
        this.context = context;
        this.settingItemModels = settingItemModels;
        inflater = LayoutInflater.from(context);
    }




    @NonNull
    @Override
    public SettingVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.setting_row_items,null);

        return new SettingVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingVH holder, int position) {

        SettingItemModel itemModel = settingItemModels.get(position);

        holder.textView.setText(itemModel.getName());
        holder.imageView.setImageDrawable(itemModel.getDrawable());

        if(position %2 == 0){
            holder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.login_end));
            holder.textView.setTextColor(context.getResources().getColor(R.color.white));
            holder.imageView.setColorFilter(context.getResources().getColor(R.color.white));

        }

        DarkmodeClass darkmodeClass = new DarkmodeClass(context);
        if(darkmodeClass.getSetting()){
            holder.textView.setTextColor(context.getResources().getColor(R.color.white));
            holder.imageView.setColorFilter(context.getResources().getColor(R.color.white));
        }



    }

    @Override
    public int getItemCount() {
        return settingItemModels.size();
    }

    public class SettingVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imageview) AppCompatImageView imageView;
        @BindView(R.id.textview) AppCompatTextView textView;
        @BindView(R.id.relativelayout) RelativeLayout relativeLayout;


        public SettingVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(),v);
        }
    }
    public void setOnItemClickListener(TicketClickListener clickListener) {
        SettingItemsAdapter.clickListener = clickListener;
    }


}
