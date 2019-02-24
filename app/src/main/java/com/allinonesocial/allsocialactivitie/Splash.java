package com.allinonesocial.allsocialactivitie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class Splash extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener
{
    private static final int DELAYED=2000;
    Button sub;
    Animation frombottom,fromtop;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

       // BlankFragment lastSMSFragment = (BlankFragment) getSupportFragmentManager().findFragmentByTag("Facebook");


imageView =(ImageView)findViewById(R.id.imageView);
        fromtop= AnimationUtils.loadAnimation(this,R.anim.fromtop);
        imageView.setAnimation(fromtop);

    BlankFragment b=new BlankFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .detach(b)
                .attach(b)
                .commit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(Splash.this,MainActivity.class);
                startActivity(i);

            }
        },DELAYED);


    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
