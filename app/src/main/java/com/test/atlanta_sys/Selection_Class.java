package com.test.atlanta_sys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.test.atlanta_sys.NOTIFICATION.NotiFy;
import com.test.atlanta_sys.RETROFIT.MainActivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

/**
 * Created by mktuser on 31/March/2021
 */
public class Selection_Class extends AppCompatActivity implements View.OnClickListener {
    CardView CardView_Main_Screen_RetroFit, CardView_Main_Screen_Notification;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_class);
        CardView_Main_Screen_RetroFit       =   (CardView) findViewById(R.id.CardView_Main_Screen_RetroFit);
        CardView_Main_Screen_RetroFit.setOnClickListener(this);
        CardView_Main_Screen_Notification   =   (CardView) findViewById(R.id.CardView_Main_Screen_Notification);
        CardView_Main_Screen_Notification.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if( v == CardView_Main_Screen_Notification)
        {
            Intent in = new Intent(this, NotiFy.class);
            startActivity(in);
            finish();
        }
        if( v == CardView_Main_Screen_RetroFit)
        {
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);
            finish();
        }
    }
}
