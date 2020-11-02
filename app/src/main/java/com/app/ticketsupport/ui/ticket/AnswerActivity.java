package com.app.ticketsupport.ui.ticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.ticketsupport.R;
import com.app.ticketsupport.adapter.AnswerListAdapter;
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

public class AnswerActivity extends AppCompatActivity {

    @BindView(R.id.title)
    AppCompatTextView mTitle;

    @BindView(R.id.date)
    AppCompatTextView mDate;
    @BindView(R.id.department)
    AppCompatTextView mDepartment;
    @BindView(R.id.priority)
    AppCompatTextView mPriority;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.edit_text_send)
    AppCompatEditText mEditText;
    @BindView(R.id.send_btn)
    FloatingActionButton mSend;
    @BindView(R.id.empty_answer)
    AppCompatTextView mEmpty;
    @BindView(R.id.relativelayout)
    RelativeLayout relativeLayout;

    private int position;
    private String[] answers;
    private TicketRepostory ticketRepostory;
    private AnswerListAdapter answerListAdapter;
    private TicketModel ticketAnswerData;
    ArrayList<String> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        ButterKnife.bind(this);
        ticketRepostory = new TicketRepostory(getApplicationContext());
        darkMode();
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.nav_start));

        position = getIntent().getIntExtra("position", 0);
        ticketRepostory.getTicket(new ILogin() {
            @Override
            public void onSuccess(Object response) {
                List<TicketModel> ticketModel = (List<TicketModel>) response;
                ticketAnswerData = ticketModel.get(position);
                mTitle.setText(ticketAnswerData.getTitle());
                mDepartment.setText(ticketAnswerData.getDepartment());
                mPriority.setText(ticketAnswerData.getPriority());
                mDate.setText(String.valueOf(ticketAnswerData.getDate().toLocaleString()));
                answers = ticketAnswerData.getAnswers();
                if (answers != null) {
                    mEmpty.setVisibility(View.GONE);
                }
                initRecycler();


            }

            @Override
            public void onFailure(String Erorr) {
                Toast.makeText(AnswerActivity.this, Erorr, Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void initRecycler(){
        if(answerListAdapter == null){
            for (int i = 0; i < answers.length; i++) {
                arrayList.add(answers[i]);
            }
            answerListAdapter = new AnswerListAdapter(ticketAnswerData.getUser().getName(), getApplicationContext(), arrayList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
            layoutManager.setStackFromEnd(true);
            recyclerView.setAdapter(answerListAdapter);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        }

    }

    @OnClick(R.id.send_btn)
    public void setmSend(){
        String message = ticketAnswerData.getUser().getName() +": "+mEditText.getText().toString();
        mEditText.setText("");
        if(!message.isEmpty()){
            ticketRepostory = new TicketRepostory(getApplicationContext());
            ticketRepostory.sendAnswer(message, ticketAnswerData.getID(), new ILogin() {
                @Override
                public void onSuccess(Object response) {
                    Toast.makeText(getApplicationContext(), "Successfully sent!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }

                @Override
                public void onFailure(String Erorr) {
                    Toast.makeText(getApplicationContext(), Erorr, Toast.LENGTH_SHORT).show();

                }
            });

        }
    }



    public void darkMode(){
        DarkmodeClass darkmodeClass = new DarkmodeClass(getApplicationContext());
        if(darkmodeClass.getSetting()){

            relativeLayout.setBackgroundColor(getResources().getColor(R.color.black));

        }

    }



}