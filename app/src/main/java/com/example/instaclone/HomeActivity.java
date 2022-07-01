package com.example.instaclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle b=getIntent().getExtras();
        String recevingdata=b.getString("key");
        System.out.print(recevingdata);
        TextView text=findViewById(R.id.textview);
        text.setText("Player "+recevingdata);
        coordinatorLayout=findViewById(R.id.coordinator);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        },(1000));
    }
}