package com.bf.camedical.e_rendezvous;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SectionsInfosPagerAdapter extends FragmentPagerAdapter {

    public SectionsInfosPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public static int[] infos = new int[]{R.drawable.info1, R.drawable.info2, R.drawable.info3, R.drawable.info4, R.drawable.info5};


    @Override
    public Fragment getItem(int position) {
        return InfosPlaceHolderFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return infos.length;
    }


    public static class InfosPlaceHolderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public static InfosPlaceHolderFragment newInstance(int sectionNumber) {
            InfosPlaceHolderFragment fragment = new InfosPlaceHolderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public InfosPlaceHolderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.infos_fragment, container, false);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.section_infos);
            imageView.setImageResource(infos[getArguments().getInt(ARG_SECTION_NUMBER)]);
            return rootView;
        }
    }
}
