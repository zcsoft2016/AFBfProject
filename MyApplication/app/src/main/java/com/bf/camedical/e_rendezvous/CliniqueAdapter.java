package com.bf.camedical.e_rendezvous;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CliniqueAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Clinique> cliniques;
    public CliniqueAdapter(Context applicationContext, ArrayList<Clinique> cliniques) {
        context = applicationContext;
        this.cliniques = cliniques;
    }
    @Override
    public int getCount() {
        return cliniques.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_element, null);
        }
       /* RelativeLayout relativeLayout = (RelativeLayout) convertView.findViewById(R.id.relativeLayout_elt);
        relativeLayout.setLayoutParams(new GridView.LayoutParams(300,300));
        ImageView imageView = (ImageView) convertView.findViewById(R.id.img_grd_elt);*/
        TextView textView = (TextView) convertView.findViewById(R.id.text_grd_elt);
        String label = cliniques.get(position).getLabel();
        label=label+" ";
        textView.setText(label);
        return convertView;
    }
}
