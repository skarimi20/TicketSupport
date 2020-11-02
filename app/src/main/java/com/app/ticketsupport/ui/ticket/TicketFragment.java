package com.app.ticketsupport.ui.ticket;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.ticketsupport.R;
import com.app.ticketsupport.adapter.TicketListAdapter;
import com.app.ticketsupport.models.TicketModel;
import com.app.ticketsupport.ui.login.ILogin;
import com.app.ticketsupport.ui.setting.DarkmodeClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TicketFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.title)
    AppCompatTextView title;
    @BindView(R.id.empty_ticket)
    AppCompatTextView empty_ticket;
    private TicketRepostory ticketRepostory;
    private TicketListAdapter ticketListAdapter;
    private List<TicketModel> mticketModels ;
    ArrayList<TicketModel> arrayList = new ArrayList<>();


    public TicketFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        ButterKnife.bind(this,view);

        ticketRepostory = new TicketRepostory(getActivity());
        darkMode();

        getTickets();

        initRecycler();

        setRefreshLayout();




        ticketListAdapter.setOnItemClickListener(new TicketClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent(getActivity(),AnswerActivity.class);
                i.putExtra("position",position );
                startActivity(i);

            }
        });

        return view;
    }

    public void setData(){

    }

    public void setRefreshLayout(){
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTickets();
                refreshLayout.setRefreshing(false);
            }
        });

    }


    public void initRecycler(){
        if(ticketListAdapter == null){
            ticketListAdapter = new TicketListAdapter(requireActivity(),arrayList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL,false);
            recyclerView.setAdapter(ticketListAdapter);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);

        }else{
            ticketListAdapter.notifyDataSetChanged();
        }

    }

    public void clear() {
        arrayList.clear();
        ticketListAdapter.notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<TicketModel> list) {
        arrayList.addAll(list);
        ticketListAdapter.notifyDataSetChanged();
    }
    public void getTickets(){
        progressBar.setVisibility(View.VISIBLE);
        ticketRepostory.getTicket(new ILogin() {
            @Override
            public void onSuccess(Object response) {
                List<TicketModel> ticketModel = (List<TicketModel>) response;
                clear();
                addAll(ticketModel);
                progressBar.setVisibility(View.GONE);
                if (!ticketModel.isEmpty()){
                    empty_ticket.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(String Erorr) {

            }
        });


    }

    public void darkMode(){
        DarkmodeClass darkmodeClass = new DarkmodeClass(getActivity());
        if(darkmodeClass.getSetting()){

            title.setTextColor(getResources().getColor(R.color.white));
            empty_ticket.setTextColor(getResources().getColor(R.color.white));


        }

    }




}