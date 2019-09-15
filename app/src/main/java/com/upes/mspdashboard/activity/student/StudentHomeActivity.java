package com.upes.mspdashboard.activity.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.upes.mspdashboard.R;
import com.upes.mspdashboard.activity.LoginActivity;
import com.upes.mspdashboard.fragment.student.ProfileFragment;
import com.upes.mspdashboard.fragment.student.FacultyListFragment;
import com.upes.mspdashboard.fragment.student.StudentProposalFragment;
import com.upes.mspdashboard.util.SessionManager;

public class StudentHomeActivity extends AppCompatActivity implements
        View.OnClickListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        FacultyListFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener,
        StudentProposalFragment.OnFragmentInteractionListener {
    private static final String TAG = "StudentHomeActivity";
    private FrameLayout frameLayout;
    private BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        frameLayout = findViewById(R.id.frame_layout_stu_home);
        bnv = findViewById(R.id.bnv_stu_home_activity);
        bnv.setOnNavigationItemSelectedListener(this);
    }

    void makeToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void setCurrentFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(frameLayout.getId(), fragment);
        if (addToBackStack)
            ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bnv.setOnNavigationItemSelectedListener(null);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_stu_proposals: {
                setCurrentFragment(StudentProposalFragment.newInstance(), false);
                break;
            }
            case R.id.action_faculties: {
                setCurrentFragment(FacultyListFragment.newInstance(), false);
                break;
            }
            case R.id.action_stu_profile: {
                setCurrentFragment(ProfileFragment.newInstance(), false);
                break;
            }
            case R.id.action_ac: {
                break;
            }
        }
        return true;
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onStuLogout(boolean success, String errorMsg) {
        SessionManager.getInstance(this).logout();
        if(success) {
            makeToast("Successfully Logged out");
            startActivity(new Intent(this, LoginActivity.class));
            this.finish();
        } else {
            makeToast(errorMsg);
        }
    }
}
