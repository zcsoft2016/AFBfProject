package com.bf.camedical.e_rendezvous;


import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmFragment extends DialogFragment {
    private Ville ville=null;
    private Specialite specialite=null;
    private  Clinique clinique=null;
    private Creneau creneau=null;
    private int day;
    private int month;
    private int year;
    public ConfirmFragment() {
        // Required empty public constructor
    }

    public static ConfirmFragment newInstance(Ville ville, Clinique clinique,Specialite specialite,Creneau creneau,int day,int month,int year){
        ConfirmFragment confirmFragment=new ConfirmFragment();
        Bundle args=new Bundle();
        args.putInt("day", day);
        args.putInt("month", month);
        args.putInt("year", year);
        args.putParcelable("ville",ville);
        args.putParcelable("clinique",clinique);
        args.putParcelable("specialite",specialite);
        args.putParcelable("creneau",creneau);
        confirmFragment.setArguments(args);
        return confirmFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style=DialogFragment.STYLE_NORMAL;
        int theme=android.R.style.Theme_Holo_Dialog;
        setStyle(style, theme);
        ville=getArguments().getParcelable("ville");
        clinique=getArguments().getParcelable("clinique");
        specialite=getArguments().getParcelable("specialite");
        creneau=getArguments().getParcelable("creneau");
        day=getArguments().getInt("day");
        month=getArguments().getInt("month");
        year=getArguments().getInt("year");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_confirm, container, false);
        this.getDialog().setTitle("Confirmer le rendez-vous");
        TextView villeTextViewBox=(TextView)view.findViewById(R.id.box_ville);
        TextView specialiteTextViewBox = (TextView) view.findViewById(R.id.box_specialite);
        TextView cliniqueTextViewBox = (TextView) view.findViewById(R.id.box_clinique);
        TextView dateTextViewBox = (TextView) view.findViewById(R.id.box_date);
        TextView creneauTextViewBox =  (TextView) view.findViewById(R.id.box_creneau);
        villeTextViewBox.setText(ville.getLabel());
        specialiteTextViewBox.setText(specialite.getLabel());
        cliniqueTextViewBox.setText(clinique.getLabel());
        dateTextViewBox.setText("" + day + " / " + month + " / " + year);
        creneauTextViewBox.setText(creneau.getLabel());
        Button confirmerRdv = (Button) view.findViewById(R.id.box_confirmer);
        confirmerRdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage();
                dismiss();
                Intent mainActivity = new Intent(getActivity(), MainActivity.class);
                startActivity(mainActivity);
            }
        });
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
    protected void SendMessage() {
        String dateSelectionnee = day + "/" + month + "/" + year;
        String message = "CLINIC_ID="+clinique.getId()+
                "&SPE_ID="+specialite.getId()+
                "&DATE_RDV="+dateSelectionnee+
                "&RDV_CRENEAU="+creneau.getPeriod();
        Log.i("Send SMS", "");

        //String phoneNo = "0681320744";
        String phoneNo = "73541385";
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);
            Toast.makeText(getActivity().getBaseContext(), "Message envoyé", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(getActivity().getBaseContext(), "Message non envoyé, veuillez réessayer", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
