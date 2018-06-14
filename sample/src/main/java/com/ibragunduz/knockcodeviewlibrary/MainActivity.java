package com.ibragunduz.knockcodeviewlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.ibragunduz.knockcodeview.interfaces.ClickDetected;
import com.ibragunduz.knockcodeview.views.lockScreen.IndicatorLockScreen;
import com.ibragunduz.knockcodeview.views.lockScreen.LockScreenKnockCodeView;
import com.ibragunduz.knockcodeview.views.setLock.SetLockKnockCode;

public class MainActivity extends AppCompatActivity {


    LockScreenKnockCodeView setLockKnockCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setLockKnockCode = (LockScreenKnockCodeView)findViewById(R.id.my_knock_view);

        final IndicatorLockScreen myIndicator = (IndicatorLockScreen)findViewById(R.id.my_knock_inditactor);
        //ClicksIndicatorView myIndicator = (ClicksIndicatorView)findViewById(R.id.my_knock_inditactor);

        setLockKnockCode.setInditactor(myIndicator);

        myIndicator.setIsSecretIndicator(false);


        setLockKnockCode.setClickDetector(new ClickDetected() {
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
                //finish();
                Toast.makeText(MainActivity.this, "doğru", Toast.LENGTH_SHORT).show();
            }
        });

        myIndicator.setHeightWidth(50,50);
        //myIndicator.setIsSecretIndicator(false);



        setLockKnockCode.setVibrationActive(true);
        setLockKnockCode.setTruePassword(new int[]{1,4,3});






    }

    public void asd(View view){
        setLockKnockCode.clearClicks();

    }




}
