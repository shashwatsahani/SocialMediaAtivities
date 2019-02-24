package com.allinonesocial.allsocialactivitie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.firebase.analytics.FirebaseAnalytics;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener,BlankFragmentyou.OnFragmentInteractionListener,BlankFragmentinsta.OnFragmentInteractionListener,Blanktwitter.OnFragmentInteractionListener{
    private FirebaseAnalytics mFirebaseAnalytics;
    //private InterstitialAd interstitialAd;
    boolean doubleBackToExitPressedOnce = false;
    private DrawerLayout drawerLayout;
    NavigationView navig;
    static int i=0;
  static TabLayout tabLayout;
    static ViewPager viewPager;
    static int j=0,k=0;
    ImageButton img;
    static String lm="m";
    boolean a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        navig=(NavigationView)findViewById(R.id.navig);
        navig.setItemIconTintList(null);
        AdRequest adRequest = new AdRequest.Builder().build();
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        img=(ImageButton)findViewById(R.id.img);
        if(j==1)
            img.setVisibility(View.GONE);
        else
            img.setVisibility(View.VISIBLE);
        if(k==1)
            tabLayout.setVisibility(View.GONE);
        else
            tabLayout.setVisibility(View.VISIBLE);
        tabLayout.addTab(tabLayout.newTab().setText("Facebook"));
        tabLayout.addTab(tabLayout.newTab().setText("Insta"));
        tabLayout.addTab(tabLayout.newTab().setText("Twitter"));
       // if(i==0) {
            tabLayout.addTab(tabLayout.newTab().setText("Youtube"));
        //}
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
         viewPager=(ViewPager)findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(3);
        final PagerAdapter adapter=new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
       tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0)
                {
                    tabLayout.setBackgroundColor(Color.parseColor("#3b5998"));

                }
                else if(tab.getPosition()==1)
                {

                    tabLayout.setBackgroundColor(Color.parseColor("#E91E63"));

                }
                else if(tab.getPosition()==2)
                {
                    tabLayout.setBackgroundColor(Color.parseColor("#00aced"));

                }
                else if(tab.getPosition()==3)
                {
                    tabLayout.setBackgroundColor(Color.parseColor("#FF0000"));

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                try{
                    BlankFragmentyou.web.getClass().getMethod("onPause").invoke(BlankFragmentyou.web,(Object[])null);}
                catch (Exception e)
                {

                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                try{
                    BlankFragmentyou.web.getClass().getMethod("onResume").invoke(BlankFragmentyou.web,(Object[])null);}
                catch (Exception e)
                {

                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        navig.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId())
                {
                   //case R.id.db1:if(i==1) makeText(MainActivity.this,"Youtube is already hidden",Toast.LENGTH_SHORT).show();else {startActivity(new Intent(MainActivity.this,MainActivity.class));finishAffinity();i=1;}return true;
                    //case R.id.db5:if(i==0) makeText(MainActivity.this,"Youtube is already showing",Toast.LENGTH_SHORT).show();else {startActivity(new Intent(MainActivity.this,MainActivity.class));finishAffinity();i=0;}return true;
                    case R.id.db6:  try {

                        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.sec);
                        String pathofBmp = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(),icon,"title", null);
                        Uri bmpUri = Uri.parse(pathofBmp);
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.allinonesocial.allsocialactivities \n All in one social media app,give it a try I loved it");
                        shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                        shareIntent.setType("image/jpeg");
                        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivity(Intent.createChooser(shareIntent, "send"));


                    } catch(Exception e) {
                        //e.toString();
                        Log.i("Errrrr",e+"");
                        makeText(MainActivity.this," Please give the required permissions",Toast.LENGTH_SHORT).show();
                    }
                    return true;
                    /*case R.id.db2: {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("PayPal Support").
                                setMessage("Support us with PayPal donation and  buy us a cup of coffee." +
                                        "A cup of coffee from you help keep the team awake to deliver you great content.").
                                setCancelable(true).
                                setPositiveButton("Yes,sure", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.me/shashwatsahani"));
                                        startActivity(browserIntent);

                                    }
                                }).setNegativeButton("No Coffee", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dlg, int which) {
                                dlg.cancel();
                            }
                        });
                        AlertDialog al = dialog.create();
                        al.show();
                    }
                    return true;*/
                  /*  case R.id.db3: {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("Paytm Support").
                                setMessage("Support us and buy us a cup of coffee." +
                                        "A cup of coffee from you help keep the team awake to deliver you great content." +
                                        "Paytm No-9915105920").
                                setCancelable(true).
                                setPositiveButton("Yes,sure", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent LaunchIntent = getPackageManager().getLaunchIntentForPackage("net.one97.paytm");
                                        if(LaunchIntent!=null)
                                        startActivity( LaunchIntent );
                                        else
                                            Toast.makeText(MainActivity.this,"App not found",Toast.LENGTH_SHORT).show();

                                    }
                                }).setNegativeButton("No Coffee", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dlg, int which) {
                                dlg.cancel();
                            }
                        });
                        AlertDialog al = dialog.create();
                        al.show();
                    }
                    return true;*/
                    case R.id.db4:startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/dev?id=6594079173480651700")));
                        return true;
                    case R.id.db8:img.setVisibility(View.GONE);j=1;return true;
                    case R.id.db9:img.setVisibility(View.VISIBLE);j=0;return true;
                    case R.id.db10:tabLayout.setVisibility(View.GONE);k=1;return true;
                    case R.id.db11:tabLayout.setVisibility(View.VISIBLE);k=0;return true;
                    default:
                        return false;
                }
            }
        });
    }



public void bo(View v)
{
    if(this.drawerLayout.isDrawerOpen(GravityCompat.START))
    {
        this.drawerLayout.closeDrawer(GravityCompat.START);

    }
    else
    this.drawerLayout.openDrawer(GravityCompat.START);
}



    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
@Override
protected void onResume() {
    super.onResume();
    SharedPreferences sharedPref= getSharedPreferences("mypref4", 0);
    int state = sharedPref.getInt("state",0);
    int but=sharedPref.getInt("but",0);
     int tab=  sharedPref.getInt("tab",0);
    i=state;
    j=but;
    k=tab;



    a=haveNetworkConnection();
    if(!a)
    {
           /* View CustomToast=Front.this.getLayoutInflater().inflate(R.layout.toast,null);
            Toast toast=new Toast(getApplicationContext());
            toast.setView(CustomToast);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.show();*/
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(" ").append(" ");
        builder.setSpan(new ImageSpan(MainActivity.this, R.drawable.error), builder.length() - 1, builder.length(), 0);
        builder.append(" \t   No active internet or Slow speed");
        Snackbar.make(findViewById(android.R.id.content), builder, Snackbar.LENGTH_LONG).show();
    }
}
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {

            super.onBackPressed();


            return;

        }
        if(this.drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            this.drawerLayout.closeDrawer(GravityCompat.START);

        }


        this.doubleBackToExitPressedOnce = true;
        //  Toast.makeText(this, "To Exit ,Click twice simultaneously", Toast.LENGTH_SHORT).show();
        Snackbar.make(findViewById(android.R.id.content),"Press back again to exit",Snackbar.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                doubleBackToExitPressedOnce = false;
            }
        }, 2000);

    }

    @Override
    protected void onPause() {

        super.onPause();
        SharedPreferences sharedPref= getSharedPreferences("mypref4", 0);
        SharedPreferences.Editor editor= sharedPref.edit();
        editor.putInt("state",i);
        editor.putInt("but",j);
        editor.putInt("tab",k);
        editor.commit();

    }

}
