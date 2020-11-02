package com.app.ticketsupport.ui.setting;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.ticketsupport.R;
import com.app.ticketsupport.adapter.AnswerListAdapter;
import com.app.ticketsupport.adapter.SettingItemsAdapter;
import com.app.ticketsupport.models.SettingItemModel;
import com.app.ticketsupport.models.SettingUserModel;
import com.app.ticketsupport.models.UserModel;
import com.app.ticketsupport.serverConnection.CheckForUpdate;
import com.app.ticketsupport.ui.login.ILogin;
import com.app.ticketsupport.ui.splash.SplashScreenActivity;
import com.app.ticketsupport.ui.ticket.AboutUsFragment;
import com.app.ticketsupport.ui.ticket.TicketClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingFragment extends Fragment {

    @BindView(R.id.setting_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.user_name)
    AppCompatTextView mUserName;
    @BindView(R.id.user_mobile_number)
    AppCompatTextView mUserNumber;
    @BindView(R.id.name)
    AppCompatTextView name;
    @BindView(R.id.number)
    AppCompatTextView number;

    private SettingItemsAdapter itemsAdapter;
    private ArrayList<SettingItemModel> settingList = new ArrayList<>();
    private SettingRepostory settingRepostory;
    private SettingUserModel userModel;


    public SettingFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this,view);
        darkMode();
        getUserInfo();
        setSettingList();
        initRecycler();

        itemsAdapter.setOnItemClickListener(new TicketClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                SettingItemModel itemModel = settingList.get(position);

                switch (position){
                    //Edit user
                    case 0:
                        EditUserFragment fragment = new EditUserFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("name", userModel.getName());
                        bundle.putString("number", userModel.getNumber());
                        fragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_page, fragment)
                                .addToBackStack(null)
                                .commit();
                        break;
                        //Darkmode
                    case 1:
                        DarkmodeClass darkmodeClass = new DarkmodeClass(getActivity());
                        darkmodeClass.setSetting();
                        Toast.makeText(getActivity(), "Please restart application!", Toast.LENGTH_LONG).show();
                        break;
                        //about us
                    case 2:
                        AboutUsFragment fragment2 = new AboutUsFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.main_page, fragment2)
                                .addToBackStack(null)
                                .commit();
                        break;
                        //Logout

                    case 3:
                        settingRepostory.logout();
                        Intent i = new Intent(getActivity(), SplashScreenActivity.class);
                        startActivity(i);
                        break;
                        //check for update
                    case 4:
                        CheckForUpdate checkForUpdate = new CheckForUpdate(getActivity());
                        checkForUpdate.isUpDate();
                        break;
                }
            }


        });



        return view;
    }

    public void initRecycler(){
        if(itemsAdapter == null){
            itemsAdapter = new SettingItemsAdapter(getActivity(),settingList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            recyclerView.setAdapter(itemsAdapter);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setNestedScrollingEnabled(true);
        }


    }

    public void setSettingList(){
        @SuppressLint("UseCompatLoadingForDrawables") SettingItemModel one = new SettingItemModel("Edit User", getActivity().getResources().getDrawable(R.drawable.ic_baseline_edit_24));
        @SuppressLint("UseCompatLoadingForDrawables") SettingItemModel two = new SettingItemModel("Dark Mode", getActivity().getResources().getDrawable(R.drawable.ic_baseline_brightness_6_24));
        @SuppressLint("UseCompatLoadingForDrawables") SettingItemModel three = new SettingItemModel("About Us", getActivity().getResources().getDrawable(R.drawable.ic_baseline_code_24));
        @SuppressLint("UseCompatLoadingForDrawables") SettingItemModel five = new SettingItemModel("Check for Update", getActivity().getResources().getDrawable(R.drawable.ic_baseline_system_update_24));
        @SuppressLint("UseCompatLoadingForDrawables") SettingItemModel four = new SettingItemModel("Log out", getActivity().getResources().getDrawable(R.drawable.ic_baseline_close_24));

        settingList.add(one);
        settingList.add(two);
        settingList.add(three);
        settingList.add(four);
        settingList.add(five);




    }

    public void getUserInfo() {
        settingRepostory = new SettingRepostory(getActivity());
        settingRepostory.getUser(new ILogin() {
            @Override
            public void onSuccess(Object response) {
                userModel = (SettingUserModel) response;
                mUserName.setText(userModel.getName());
                mUserNumber.setText(userModel.getNumber());
            }

            @Override
            public void onFailure(String Erorr) {
                Toast.makeText(getActivity(), Erorr, Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void darkMode(){
        DarkmodeClass darkmodeClass = new DarkmodeClass(getActivity());
        if(darkmodeClass.getSetting()){
            name.setTextColor(getResources().getColor(R.color.Lgrey));
            number.setTextColor(getResources().getColor(R.color.Lgrey));
            mUserName.setTextColor(getResources().getColor(R.color.white));
            mUserNumber.setTextColor(getResources().getColor(R.color.white));
        }

    }

}