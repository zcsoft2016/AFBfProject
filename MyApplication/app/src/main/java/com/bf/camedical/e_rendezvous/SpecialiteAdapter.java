package com.bf.camedical.e_rendezvous;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class SpecialiteAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Specialite> specialites;
    public SpecialiteAdapter(Context applicationContext, ArrayList<Specialite> specialites ) {
        context = applicationContext;
        this.specialites = specialites;
    }

    @Override
    public int getCount() {
        return specialites.size();
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
        textView.setText(specialites.get(position).getLabel());
        return convertView;
    }
}
