package com.bf.camedical.e_rendezvous;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class LoadingActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

    }
    @Override
    public void onBackPressed(){
        if(DownFragment.isterminated) {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}

