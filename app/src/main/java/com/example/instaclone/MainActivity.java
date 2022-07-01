package com.example.instaclone;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private static int Splash_screen_time_out=200;
    int activeplayer=0;
    Button closebutton;
    boolean playerisactive=true;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void clickme(View view) {
        ImageView counter = (ImageView) view;
        int tappedcounter=Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter]==2&&playerisactive) {
            gamestate[tappedcounter]=activeplayer;
            counter.setTranslationY(-1000f);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.xe);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.xero);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000f).rotationBy(180).setDuration(200);
            for(int[] winningposition:winningpos){
                if(gamestate[winningposition[0]]==gamestate[winningposition[1]]&&gamestate[winningposition[1]]==gamestate[winningposition[2]]&&gamestate[winningposition[0]]!=2){
                    playerisactive=false;
                    String winner="0";
                    if(gamestate[winningposition[0]]==0){
                        winner="x";
                    }

                    String finalWinner = winner;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                            Bundle b=new Bundle();
                            b.putString("key", finalWinner);
                            intent.putExtras(b);
                            startActivity(intent);
                            finish();
                        }
                    },Splash_screen_time_out);


                }
            }
        }
        else{
            boolean gameIsover=true;
            for(int counterstate:gamestate){
                if(counterstate==2){
                    gameIsover=false;
                }
            }
//            if (gameIsover){
//                TextView text=findViewById(R.id.txt);
//                text.setText("Oops It's Draw");
//            }
        }
    }
    public void playbtn(View view){
        Button button=findViewById(R.id.btn);
        activeplayer=0;
        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }

        GridLayout gridlayout=findViewById(R.id.grid);
        for (int i=0;i<gridlayout.getChildCount();i++){
            ((ImageView)gridlayout.getChildAt(i)).setImageResource(0);
        }
        playerisactive=true;
//        TextView text=findViewById(R.id.txt);
//        text.setText("Winner is: ");

    }
    public void volleylib(){

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url ="https://www.google.com";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error occured!", Toast.LENGTH_SHORT).show();

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        CardView cardView=findViewById(R.id.cardid);
        TextView txt=findViewById(R.id.txt);
        txt.animate().scaleY(1f).scaleX(1f).setDuration(1000);
        cardView.animate().scaleX(1f).scaleY(1f).setDuration(1000);

    }
}