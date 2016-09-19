package com.bf.camedical.e_rendezvous;


import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class CliniqueFragment extends DialogFragment {
    Specialite specialite;
    Ville ville;

    public static CliniqueFragment newInstance(Ville ville, Specialite specialite) {
        CliniqueFragment fragment = new CliniqueFragment();
        Bundle args = new Bundle();
        args.putParcelable(VilleDao.vTabName,ville);
        args.putParcelable(SpecialiteDao.speTabName,specialite);
        fragment.setArguments(args);
        return fragment;
    }

    public CliniqueFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int style=DialogFragment.STYLE_NORMAL;
        int theme=android.R.style.Theme_Holo_Dialog;
        setStyle(style, theme);
        ville=getArguments().getParcelable(VilleDao.vTabName);
        specialite=getArguments().getParcelable(SpecialiteDao.speTabName);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_clinique, container, false);
        this.getDialog().setTitle("Clinique");
        TextView messageText=(TextView)view.findViewById(R.id.messageClinique);
        messageText.setText("Pas de clinique disponible pour la spécialité "+specialite.getLabel()+" dans la ville de "+ville.getLabel());
        Button buttonRetour=(Button)view.findViewById(R.id.btnRetour);
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }




}
