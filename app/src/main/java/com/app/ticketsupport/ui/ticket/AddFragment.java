package com.app.ticketsupport.ui.ticket;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.ticketsupport.R;
import com.app.ticketsupport.serverConnection.TokenClass;
import com.app.ticketsupport.ui.login.ILogin;
import com.app.ticketsupport.ui.setting.DarkmodeClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddFragment extends Fragment {

    @BindView(R.id.spi_department)
    Spinner sDepartment;
    @BindView(R.id.spi_priority)
    Spinner sPriority;
    @BindView(R.id.edt_title)
    AppCompatEditText mTitle;
    @BindView(R.id.edt_message)
    AppCompatEditText mMessage;
    @BindView(R.id.send_btn)
    FloatingActionButton sendBtn;
    @BindView(R.id.progressRel)
    RelativeLayout mProgressBar;
    @BindView(R.id.progress_text)
    AppCompatTextView mProgressText;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.title_add_new)
    AppCompatTextView title_add_new;

    private TicketRepostory ticketRepostory;
    public AddFragment() {
        // Required empty public constructor
    }

//    public static AddFragment newInstance(String param1, String param2) {
//        AddFragment fragment = new AddFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(this,view);
        darkMode();
        String[] depaermentArray = new String[]{"Customer service",
        "Sales department",
        "Technical support",
        "General manager"};

        String[] priorityArray = new String[]{"Low",
        "Normal",
        "High",
        "Critical"};


        ArrayAdapter<String> department = new ArrayAdapter<String>(getActivity(), R.layout.add_spinner_items,depaermentArray);
        ArrayAdapter<String> priority = new ArrayAdapter<String>(getActivity(), R.layout.add_spinner_items,priorityArray);

        sDepartment.setAdapter(department);
        sPriority.setAdapter(priority);


        return view;
    }

    @OnClick(R.id.send_btn)
    public void setSendBtn(){
        mProgressBar.setVisibility(View.VISIBLE);


        final String title = mTitle.getText().toString();
        final String message = mMessage.getText().toString();
        final String department = sDepartment.getSelectedItem().toString();
        final String priority = sPriority.getSelectedItem().toString();
        final String TicketType = getResources().getStringArray(R.array.ticket_type)[0];

        if(title.isEmpty() && message.isEmpty() && department == null && priority == null){
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Please Enter title and message", Toast.LENGTH_SHORT).show();
        }else{
            ticketRepostory = new TicketRepostory(getActivity());

            ticketRepostory.NewTicket(department, priority, title, message, TicketType, new ILogin() {
                @Override
                public void onSuccess(Object response) {
                    Toast.makeText(getActivity(), "Ticket successfully sent", Toast.LENGTH_SHORT).show();
                    mProgressBar.setVisibility(View.GONE);


                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {













                        }
                    }, 1500);
                }

                @Override
                public void onFailure(String Erorr) {
                    mProgressBar.setVisibility(View.GONE);

                    Toast.makeText(getActivity(), Erorr, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }

    public void darkMode(){
        DarkmodeClass darkmodeClass = new DarkmodeClass(getActivity());
        if(darkmodeClass.getSetting()){
            title_add_new.setTextColor(getResources().getColor(R.color.white));
        }

    }
}