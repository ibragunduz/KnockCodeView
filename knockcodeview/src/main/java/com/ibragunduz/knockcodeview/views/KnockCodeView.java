package com.ibragunduz.knockcodeview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.media.Image;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ibragunduz.knockcodeview.interfaces.ClickDetected;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.ibragunduz.knockcodeview.R;

public class KnockCodeView extends LinearLayout implements View.OnClickListener,ClickDetected {

    public static final int SET_PASSWORD = 0;
    public static final int LOCK_SCREEN = 1;
    private int type;
    private boolean isVibrationActive = true;

    public KnockCodeView(Context context) {
        super(context);
        initialize(context,null);
    }

    public KnockCodeView(Context context, @Nullable AttributeSet attrs) {
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
        timer = new Timer();


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
                    R.styleable.KnockCodeView, 0, 0);

            setLinesDrawable(typedArray.getColor(R.styleable.KnockCodeView_knock_line_drawable,R.drawable.line));

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


    public void setColorFilter(int color){
        lineCenter.setColorFilter(color);
    }

    public void hideCenterLine(int resId)
    {



        if (resId!=0){
            setLinesDrawable(resId);
        }else{
        setLinesDrawable(R.drawable.line2);
        }
    }

    public void resetClicksSquence(){
        x=0;
         clearClicks();
        firstClicked = false;

        clickDetected(clicksSquence);

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

ClicksIndicatorView inditactor;
    public void setInditactor(ClicksIndicatorView inditactor) {
        this.inditactor = inditactor;
        inditactor.SetInditactor(new int[]{-1,-1,-1,-1,-1,-1,-1,-1});

    }
    int sure =0;
  private   boolean isEntryTrue = false;

    final Handler handler = new Handler();

    TimerTask timertask = new TimerTask() {
        @Override
        public void run() {
            handler.post(new Runnable() {
                public void run() {
                    if (++sure > 6 && firstClicked&&!isEntryTrue){
                        inCorrectEntry();
                        Log.i("Süre : " ,sure+ "  ---" );

                    }
                }
            });
        }
    };
    Timer timer;
    boolean isTimerStarted=false;
    boolean firstClicked = false;


    int x = 0 ;

    @Override
    public void clickDetected(final int[] clicks) {




    if (getType() == LOCK_SCREEN){
            sure = 0;
            firstClicked = true;
            if (getClicksCount()==1 && !isTimerStarted){
                timer.schedule(timertask, 0, 100);
                isTimerStarted = true;
            }


                isEntryTrue = false;
               if (clicks!=null){
                   if (isEntryIsTrue(clicks)){
                       correctEntry();
                   }
               }


}else{
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (clicks[clicks.length-1]!=-1){

                    if (++x==3){
                        resetClicksSquence();
                    }
                }

                }

        },100);
    }





        if (clickDetector!=null){
            clickDetector.clickDetected(clicksSquence);
        }
        if (inditactor!=null){
            inditactor.SetInditactor(clicks);
        }
    }

    @Override
    public void inCorrectEntry() {
        clearClicks();
        firstClicked = false;
clickDetector.inCorrectEntry();

    }

    @Override
    public void correctEntry() {
        clearClicks();
        firstClicked = false;
        isEntryTrue = true;
clickDetector.correctEntry();
    }

    private void clearClicks(){

        for (int i = 0;i<clicksSquence.length;i++){
            clicksSquence[i] = -1;
        }

        if (inditactor!=null){
            inditactor.SetInditactor(clicksSquence);
        }
    }




    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public boolean isVibrationActive() {
        return isVibrationActive;
    }

    public void setVibrationActive(boolean b) {
        this.isVibrationActive = b;
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
    public void setTruePassword(int[] truePassword) {
        this.truePassword = truePassword;
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
        lineCenter.setBackgroundResource(resId);

    }
}

