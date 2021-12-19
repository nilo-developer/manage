package com.example.myapplication.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class SplashActivity extends AppCompatActivity {

    private final int splash_lenght = 8000;
    ProgressBar progressBar;
    Animation animationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if (internetConnection()) {
            Toast.makeText(this, "اینترنت شما متصل است", Toast.LENGTH_SHORT).show();
        } else {
            new AlertDialog.Builder(SplashActivity.this)
                    .setTitle("خطا در اتصال به اینترنت ")
                    .setMessage("لطفا اتصال اینتنرنت را چک کنید ...")
                    .setPositiveButton("انصراف", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setNegativeButton("رفتن به تنظیمات", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (internetConnection()) {
                                Toast.makeText(SplashActivity.this, "اینترنت شما متصل شد", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(WifiManager.ACTION_PICK_WIFI_NETWORK));
//                                Toast.makeText(SplashActivity.this, "اینترنت شما متصل نشد", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .show();
        }

        ImageView imageView = findViewById(R.id.imgSplash);
        TextView textView = findViewById(R.id.txtSplash);
//        TextView textView1 = findViewById(R.id.txt2Splash);
//        ProgressBar progressBar = findViewById(R.id.progress_bar);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Animation anim1, anim2, anim3;

        anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.open_next);
        anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clock_wise);
        anim3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clock_wise);


        imageView.startAnimation(anim1);
        textView.startAnimation(anim2);
//        textView1.startAnimation(anim3);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(intent);
                SplashActivity.this.finish();
//                progressBar.setProgress(2);
            }
        }, splash_lenght);
    }


    public boolean internetConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifi != null && wifi.isConnected()) {
            return true;
        }
        NetworkInfo data = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (data != null && data.isConnected()) {
            return true;
        }
        NetworkInfo active = cm.getActiveNetworkInfo();
        if (active != null && active.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);

    }
}