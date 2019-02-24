package com.allinonesocial.allsocialactivitie;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by ragnar on 15/1/18.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int NoofTabs;


    public PagerAdapter(FragmentManager fm,int NoofTabs) {
        super(fm);
        this.NoofTabs=NoofTabs;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                BlankFragment blankFragment=new BlankFragment();
                return blankFragment;
            case 1:
                BlankFragmentinsta blankFragmentinsta=new BlankFragmentinsta();
                return blankFragmentinsta;
            case 2:
                Blanktwitter t=new Blanktwitter();
                return t;

            case 3:

           BlankFragmentyou blankFragmentyou=new BlankFragmentyou();
            return blankFragmentyou;


            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return NoofTabs;
    }
}
