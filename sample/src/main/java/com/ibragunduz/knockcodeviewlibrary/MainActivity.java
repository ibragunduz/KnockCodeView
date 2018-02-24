package com.ibragunduz.knockcodeviewlibrary;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.ibragunduz.knockcodeview.views.ClicksIndicatorView;
import com.ibragunduz.knockcodeview.views.KnockCodeView;

public class MainActivity extends AppCompatActivity {


    KnockCodeView knockCodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        knockCodeView = (KnockCodeView)findViewById(R.id.my_knock_view);

        final ClicksIndicatorView myIndicator = (ClicksIndicatorView)findViewById(R.id.my_knock_inditactor);

        knockCodeView.setInditactor(myIndicator);

        knockCodeView.setType(KnockCodeView.SET_PASSWORD);
        knockCodeView.hideCenterLine(0);

        //knockCodeView.setVibrationActive(true);
        //knockCodeView.setTruePassword(new int[]{1,4,3});






    }

    public void asd(View view){
        knockCodeView.resetClicksSquence();
    }




}
