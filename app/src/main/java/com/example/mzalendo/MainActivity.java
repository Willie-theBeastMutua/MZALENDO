package com.example.mzalendo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static int SPLASH_SCREEN = 7000;
    ImageView wazaLogo;
    TextView waza;
    TextView hock;
    CharSequence charseq, charseq2;
    long delay = 200, delay2=200;
    int index,index2;
    Handler handler = new Handler();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wazaLogo=findViewById(R.id.waza_Logo);
        waza=findViewById(R.id.waza_text);
        hock = findViewById(R.id.hock_text);

        // set full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
        ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //object animator
        ObjectAnimator objanim = ObjectAnimator.ofPropertyValuesHolder(
                wazaLogo,
                PropertyValuesHolder.ofFloat("scaleX",1.2f),
                PropertyValuesHolder.ofFloat("scaleY",1.2f)
        );
        //duration
        objanim.setDuration(1000);
        //repeat count
        objanim.setRepeatCount(ValueAnimator.INFINITE);
        //repeat mode
        objanim.setRepeatMode(ValueAnimator.REVERSE);
        objanim.start();
        //set text
        animetext("WAZALENDO");
        animetext2("HOCKEY CLUB");

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, Dashboard.class).
                    setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        },SPLASH_SCREEN);

    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //set text when runnable is run
            waza.setText(charseq.subSequence(0,index++));
            if (index<=charseq.length()){
                handler.postDelayed(runnable,delay);
            }
        }
    };
    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            //set text when runnable is run
            hock.setText(charseq2.subSequence(0,index2++));
            if (index2<=charseq2.length()){
                handler.postDelayed(runnable2,delay);
            }
        }
    };
    //animated text method
    public void animetext (CharSequence cs){
        charseq=cs;
        index=0;
        waza.setText("");
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable,delay);

    }
    public void animetext2 (CharSequence cs){
        charseq2=cs;
        index2=0;
        hock.setText("");
        handler.removeCallbacks(runnable2);
        handler.postDelayed(runnable2,delay2);

    }
}
