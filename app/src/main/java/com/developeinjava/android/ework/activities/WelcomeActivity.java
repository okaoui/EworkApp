package com.developeinjava.android.ework.activities;

import android.net.Uri;
import android.support.v4.app.Fragment;

import com.developeinjava.android.ework.fragments.SingleFragmentActivity;
import com.developeinjava.android.ework.fragments.WelcomeFragment;


public class WelcomeActivity extends SingleFragmentActivity implements WelcomeFragment.OnFragmentInteractionListener{
    private static final String TAG = "WelcomeActivity";

    @Override
    protected Fragment createFragment() {
        return WelcomeFragment.newInstance();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
