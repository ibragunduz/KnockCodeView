package com.ibragunduz.knockcodeviewlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.ibragunduz.knockcodeview.views.ClicksIndicatorView;
import com.ibragunduz.knockcodeview.views.KnockCodeView;

public class MainActivity extends AppCompatActivity {


    KnockCodeView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deneme);

        view = (KnockCodeView)findViewById(R.id.my_knock_view);

        final ClicksIndicatorView myIndicator = (ClicksIndicatorView)findViewById(R.id.my_knock_inditactor);

        view.setInditactor(myIndicator);

        view.setType(KnockCodeView.SET_PASSWORD);
        view.setVibrationActive(true);
        view.setTruePassword(new int[]{1,4,3});






    }





}
