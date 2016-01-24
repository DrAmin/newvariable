package com.example.tasknewvariable;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by deepa on 23/01/2016.
 */
public class SendPushMessage extends AppCompatActivity {
    EditText msg_title,msg_body;
    Button bt_send;
    String msg_t,msg_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_push_message);
        msg_title=(EditText)findViewById(R.id.et_msg_title);
        msg_body=(EditText)findViewById(R.id.msg_body);
        bt_send=(Button)findViewById(R.id.bt_submit_msg);
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"HEllo Clicked",Snackbar.LENGTH_LONG).show();
                msg_t=String.valueOf(msg_title.getText());
                msg_b=String.valueOf(msg_body.getText());
                new SendNotification().execute();
            }
        });
    }


    public class SendNotification extends AsyncTask<String,Integer,Boolean>{
        private String data,json_result;

        @Override
        protected Boolean doInBackground(String... params) {
            try {
                URL url = new URL("http://newvariable.orgfree.com/sendnotification.php?message="+msg_t+"::"+msg_b);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                BufferedReader reader=new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuffer buffer=new StringBuffer();
                while ((data=reader.readLine())!=null){
                    buffer.append(data+"\n");
                }
                json_result=buffer.toString();
            }catch (Exception e){
                e.printStackTrace();
            }
                return null;
        }
    }
}
