package com.test.atlanta_sys;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by mktuser on 29/August/2020
 */
public class Splash_Screen extends Activity {
    Animation topAnim;
    Animation bottomAnim;
    TextView logo, slogan;
    ImageView image;

    private static final String TAG = "Splash_Screen";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        image = findViewById(R.id.ImageView);
        logo = findViewById(R.id.TextView);
        slogan = findViewById(R.id.TextView2);

        image.setAnimation(topAnim);
        logo.setAnimation(bottomAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //------------------------------------------------//
                    Intent in = new Intent(Splash_Screen.this, Selection_Class.class);
                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View, String>(image, "logo_image");
                    pairs[1] = new Pair<View, String>(logo, "logo_text");

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Splash_Screen.this, pairs);
                        startActivity(in, options.toBundle());
                        finish();
                    }
            }
        }, 2*1000);
    }
}
