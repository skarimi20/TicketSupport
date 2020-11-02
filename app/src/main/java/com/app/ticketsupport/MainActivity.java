package com.app.ticketsupport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.app.ticketsupport.ui.setting.DarkmodeClass;
import com.app.ticketsupport.ui.setting.SettingFragment;
import com.app.ticketsupport.ui.ticket.AddFragment;
import com.app.ticketsupport.ui.ticket.TicketFragment;
import com.gauravk.bubblenavigation.BubbleNavigationConstraintView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Bind TicketFragment, AddFragment, SettingFragment and bubbleNavigationConstraintView Library(Navigation Bar)
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation_constraint)
    BubbleNavigationConstraintView bubbleNavigationConstraintView;
    @BindView(R.id.frame_lay)
    FrameLayout frameLayout;
    @BindView(R.id.main_page)
    RelativeLayout mainPage;

    private Fragment fragment = null;
    private int close ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        darkMode();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_lay,new TicketFragment()).commit();
        close = 0;

        bubbleNavigationConstraintView.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position){
                    case 0:
                        fragment = new TicketFragment();
                        break;
                    case 1:
                        fragment = new AddFragment();
                        break;
                    case 2:
                        fragment = new SettingFragment();
                        break;

                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.fragment_fade_enter,R.anim.fragment_fade_exit);
                transaction.addToBackStack(null);
                transaction.replace(R.id.frame_lay,fragment).commit();
            }
        });




    }

    public void onBackPressed() {
        if(close == 0){
            Toast.makeText(this, "Press one more time to close!", Toast.LENGTH_LONG).show();
            close += 1;
            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    close =0;
                }
            }, 3000);

        }else {
            finishAffinity();
            close = 0;
        }

    }

    public void darkMode(){
        DarkmodeClass darkmodeClass = new DarkmodeClass(getApplicationContext());
        if(darkmodeClass.getSetting()){
            mainPage.setBackgroundColor(getResources().getColor(R.color.black));
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.black));
        }

    }
}