package com.rsschool.android2021;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.effect.EffectFactory;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FirstFragment.PressGenerate, SecondFragment.PressBack {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openFirstFragment(0);


    }

    private void openFirstFragment(int previousNumber) {
        final Fragment firstFragment = FirstFragment.newInstance(previousNumber);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void onGenerateButtonPressed(int min, int max) {
        openSecondFragment(min, max);
    }

    private void openSecondFragment(int min, int max) {

        final Fragment secondFragment = SecondFragment.newInstance(min, max);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, secondFragment);
            transaction.commit();

    }

    public void onBackButtonPressed(int generatedRandomNumber) {

        openFirstFragment(generatedRandomNumber);
    }
}
