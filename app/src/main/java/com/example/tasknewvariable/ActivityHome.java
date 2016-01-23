package com.example.tasknewvariable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by deepa on 23/01/2016.
 */
public class ActivityHome extends AppCompatActivity {
    Button screen,push_note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        screen=(Button)findViewById(R.id.bt_screen_ui);
        push_note=(Button)findViewById(R.id.bt_push_noti);
        screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ActivityHome.this,MainActivity.class);
                startActivity(in);
            }
        });
        push_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(ActivityHome.this,SignUp.class);
                startActivity(in);
            }
        });
    }
}
