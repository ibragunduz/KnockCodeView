package com.ibragunduz.knockcodeviewlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import com.ibragunduz.knockcodeview.interfaces.ClickDetected;
import com.ibragunduz.knockcodeview.views.lockScreen.IndicatorLockScreen;
import com.ibragunduz.knockcodeview.views.KnockCodeView;
import com.ibragunduz.knockcodeview.views.setLock.ClicksIndicatorView;

public class MainActivity extends AppCompatActivity {


    KnockCodeView knockCodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        knockCodeView = (KnockCodeView)findViewById(R.id.my_knock_view);

        //final IndicatorLockScreen myIndicator = (IndicatorLockScreen)findViewById(R.id.my_knock_inditactor);
        ClicksIndicatorView cc = (ClicksIndicatorView)findViewById(R.id.my_knock_inditactor);
        knockCodeView.setInditactor(cc);

        knockCodeView.setType(KnockCodeView.SET_PASSWORD);
        knockCodeView.hideCenterLine(0);

        cc.setHeightWidth(30,30);
        //knockCodeView.setVibrationActive(true);
        knockCodeView.setTruePassword(new int[]{1,4,3});






    }

    public void asd(View view){
        knockCodeView.resetClicksSquence();
    }




}
