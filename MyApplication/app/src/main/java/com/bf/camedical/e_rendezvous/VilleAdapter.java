package com.bf.camedical.e_rendezvous;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bobisolo on 17/01/2016.
 */
public class VilleAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Ville> villes;
    public VilleAdapter(Context applicationContext, ArrayList<Ville> villes) {
        context = applicationContext;
        this.villes = villes;
    }

    @Override
    public int getCount() {
        return villes.size();
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
        TextView textView = (TextView) convertView.findViewById(R.id.text_grd_elt);
        textView.setText(villes.get(position).getLabel());
        return convertView;
    }
}
