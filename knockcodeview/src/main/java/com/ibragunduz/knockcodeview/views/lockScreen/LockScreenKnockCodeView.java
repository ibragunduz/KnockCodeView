package com.ibragunduz.knockcodeview.views.lockScreen;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ibragunduz.knockcodeview.R;
import com.ibragunduz.knockcodeview.interfaces.ClickDetected;
import com.ibragunduz.knockcodeview.views.setLock.ClicksIndicatorView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.Context.WINDOW_SERVICE;

public class LockScreenKnockCodeView extends LinearLayout implements View.OnClickListener,ClickDetected {

    public LockScreenKnockCodeView(Context context) {
        super(context);
        initialize(context,null);
    }

    public LockScreenKnockCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize(context,attrs);
    }


   private ClickDetected clickDetector;
    private int[] clicksSquence = new int[8];
    private View rootView;
    private CardView card1,card2,card3,card4;
    private   List<CardView> cardViewsList;
    private ImageView lineCenter;


    private void initialize(Context context,AttributeSet attributeSet){

        rootView =  LayoutInflater.from(context).inflate(R.layout.activity_knock_code_view, this);
        card1 = (CardView) rootView.findViewById(R.id.cardview_1);
        card2 = (CardView) rootView.findViewById(R.id.cardview_2);
        card3 = (CardView) rootView.findViewById(R.id.cardview_3);
        card4 = (CardView) rootView.findViewById(R.id.cardview_4);

        lineCenter = (ImageView) rootView.findViewById(R.id.knock_view_line_center);


        cardViewsList = new ArrayList<>();
        cardViewsList.add(card1);
        cardViewsList.add(card2);
        cardViewsList.add(card3);
        cardViewsList.add(card4);


        for (CardView crd : cardViewsList){
            crd.setOnClickListener(this);
            crd.setPreventCornerOverlap(false);
            crd.setCardElevation(0);

        }

        for (int i = 0; i< clicksSquence.length; i++){
            clicksSquence[i] = -1;
        }

        if (attributeSet != null){
            final TypedArray typedArray = context.obtainStyledAttributes(attributeSet,
                    R.styleable.SetLockKnockCode, 0, 0);

            setLinesDrawable(typedArray.getColor(R.styleable.SetLockKnockCode_knock_line_drawable,R.drawable.line));

            setButtonsColor();

        }









    }











    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.cardview_1) {
            addClickToSquence(1);

        } else if (i == R.id.cardview_2) {
            addClickToSquence(2);

        } else if (i == R.id.cardview_3) {
            addClickToSquence(3);

        } else if (i == R.id.cardview_4) {
            addClickToSquence(4);
        }
    }






    private void addClickToSquence(int number){

        if (getClicksCount()<clicksSquence.length){
            try {
            clicksSquence[getClicksCount()] = number;
            }catch (IndexOutOfBoundsException e){

            }
        }


            clickDetected(clicksSquence);


    }








    IndicatorLockScreen indicator;
    public void setInditactor(IndicatorLockScreen inditactor) {
        this.indicator = inditactor;
    }




    boolean isVibrationActive = true;
    Vibrator v;
    boolean isWillWait = false;
    @Override
    public void clickDetected(final int[] clicks) {

        if (isTimerStarted==false){
            startChecking();
        }


        if (!isWillWait){


        vibrate();
        indicator.updateClicks(clicks);

        if (clickDetector!=null){
            clickDetector.clickDetected(clicksSquence);
        }



        currentClicks = clicks;

        isWillCount = true;
        counterClicked = 0;
    if (isEntryIsTrue(clicksSquence)){
        isWillWait=true;
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
        correctEntry();

            }
        },250);
    }
        }
    }


    /**
     * Sıfır tıklama varsa hiçbirşey yapmayacak
     * Her tıklamadan sonra
     *
     *
     * **/

    boolean isTimerStarted = false;
    int counterClicked = 0;
    boolean isWillCount = false;
    Handler handlerChecking ;
    Runnable runnableChecking = new Runnable() {
        @Override
        public void run() {

            isTimerStarted = true;
            try {


                if (isWillCount) {
                    if (++counterClicked > 4) {
                        if (currentClicks!=null){
                            if (!isEntryIsTrue(currentClicks)){
                                inCorrectEntry();
                            }
                            isWillCount = false;
                        }
                    }
                }







                previousEntries = currentClicks;

            }finally {
                handlerChecking.postDelayed(runnableChecking,100);
            }
        }
    };
    private void startChecking(){
        try {
        if (handlerChecking==null) handlerChecking = 
            ler(Looper.getMainLooper());
        runnableChecking.run();
        }catch (Exception e){

        }

    }
    private void stopChecking(){
        isTimerStarted = false;
        handlerChecking.removeCallbacks(runnableChecking);
    }



    int previousEntries[];
    int currentClicks[];



    private void vibrate(){
        if (isVibrationActive) {
            if (v == null)
                v = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(30);
        }
    }






    @Override
    public void inCorrectEntry() {
        stopChecking();
        clearClicks();
        clickDetector.inCorrectEntry();
        isWillWait = false;

    }

    @Override
    public void correctEntry() {
        stopChecking();
        clearClicks();
        clickDetector.correctEntry();
        isWillWait = false;

    }

    public void clearClicks(){
        for (int i = 0;i<clicksSquence.length;i++){
            clicksSquence[i] = -1;
        }
        indicator.updateClicks(clicksSquence);



    }










    public void hideCenterLine(int resId)
    {



        if (resId!=0){
            setLinesDrawable(resId);
        }else{
            setLinesDrawable(R.drawable.line2);
        }
    }

    public void setColorFilter(int color){
        lineCenter.setColorFilter(color);
    }

    private boolean isEntryIsTrue(int[] clicks){

        for (int i = 0; i<clicks.length;i++){
            if (clicks[i]!= getTruePassword()[i]){
                return false;
            }
        }

        return true;


    }

    public int getClicksCount(){
        for (int i = 0; i< clicksSquence.length; i++){
            if (clicksSquence[i]==-1)return i;

        }
        return clicksSquence.length;
    }

    private int[] truePassword;
    public int[] getTruePassword() {
        if (truePassword == null)truePassword =  new int[]{-1,-1,-1,-1,-1,-1,-1,1};

        if (truePassword.length<clicksSquence.length){
            int [] yeni = new int[]{-1,-1,-1,-1,-1,-1,-1,-1};
            for (int i = 0; i<truePassword.length;i++){
                yeni[i] = truePassword[i];
            }
            truePassword = yeni;
        }
        return truePassword;
    }
    public void setClickDetector(ClickDetected detector){
        this.clickDetector = detector;
    }

    public void setButtonsColor(){
        for (CardView crd : cardViewsList){
            crd.setCardBackgroundColor(Color.TRANSPARENT);
        }

    }


    public void setLinesDrawable(int resId) {
        lineCenter.setImageResource(resId);

    }



    public boolean isVibrationActive() {
        return isVibrationActive;
    }

    public void setVibrationActive(boolean b) {
        this.isVibrationActive = b;
    }

    public void setTruePassword(int[] truePassword) {
        this.truePassword = truePassword;
    }

}

