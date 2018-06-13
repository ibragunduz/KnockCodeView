package com.ibragunduz.knockcodeviewlibrary;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


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

        final IndicatorLockScreen myIndicator = (IndicatorLockScreen)findViewById(R.id.my_knock_inditactor);
        //ClicksIndicatorView myIndicator = (ClicksIndicatorView)findViewById(R.id.my_knock_inditactor);

        knockCodeView.setInditactor(myIndicator);

        knockCodeView.setType(KnockCodeView.LOCK_SCREEN);
        myIndicator.setIsSecretIndicator(false);


        knockCodeView.setClickDetector(new ClickDetected() {
            @Override
            public void clickDetected(int[] clicks) {
                Log.i("--","clicks : "+clicks.toString());

            }

            @Override
            public void inCorrectEntry() {
                Toast.makeText(MainActivity.this, "yanlış", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void correctEntry() {
                Toast.makeText(MainActivity.this, "doğru", Toast.LENGTH_SHORT).show();
            }
        });

        myIndicator.setHeightWidth(50,50);
        //myIndicator.setIsSecretIndicator(false);



        knockCodeView.setVibrationActive(true);
        knockCodeView.setTruePassword(new int[]{1,4,3});






    }

    public void asd(View view){
        knockCodeView.resetClicksSquence();
    }




}
