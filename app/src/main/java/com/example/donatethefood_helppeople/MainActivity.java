package com.example.donatethefood_helppeople;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.os.Bundle;

import com.example.donatethefood_helppeople.ui.DonationInformationFragment;
import com.example.donatethefood_helppeople.ui.DonationListFragment;
import com.example.donatethefood_helppeople.ui.StartPageFragment;
import com.example.donatethefood_helppeople.ui.VolunteerRegistrationFragment;
import com.example.donatethefood_helppeople.ui.VoluteerLogin;
import com.example.donatethefood_helppeople.ui.WelcomeFragment;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements
        StartPageFragment.DonatorVlounteerTransactionListener,
        DonationInformationFragment.DonationCompleteListener,
        VoluteerLogin.VolunteerLoginSuccessfulListener,
        VolunteerRegistrationFragment.RegistrationSuccessfulListener {
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        StartPageFragment startPageFragment = new StartPageFragment();
        ft.add(R.id.fragmentContainer, startPageFragment);
        ft.commit();
    }

    @Override
    public void goToDonatorInfoPage() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        DonationInformationFragment informationFragment = new DonationInformationFragment();
        ft.replace(R.id.fragmentContainer, informationFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void goToVolunterLogin() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        VoluteerLogin voluteerLogin = new VoluteerLogin();
        ft.replace(R.id.fragmentContainer, voluteerLogin);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void donationComplete() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        WelcomeFragment welcomeFragment = new WelcomeFragment();
        ft.replace(R.id.fragmentContainer, welcomeFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void loginSuccessful() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        DonationListFragment listFragment = new DonationListFragment();
        ft.replace(R.id.fragmentContainer, listFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void goToRegistration() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        VolunteerRegistrationFragment registrationFragment = new VolunteerRegistrationFragment();
        ft.replace(R.id.fragmentContainer, registrationFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void registrationSuccess() {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        VoluteerLogin voluteerLogin = new VoluteerLogin();
        ft.replace(R.id.fragmentContainer, voluteerLogin);
        ft.addToBackStack(null);
        ft.commit();
    }
}
