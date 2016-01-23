package com.example.tasknewvariable;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tasknewvariable.*;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by deepa on 23/01/2016.
 */
public class SignUp extends AppCompatActivity {
    EditText user_name, user_pwd, user_cpwd;
    Button bt_signup;
    String u_name,u_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        user_name = (EditText) findViewById(R.id.et_user_eid);
        user_pwd = (EditText) findViewById(R.id.et_user_pass);
        user_cpwd = (EditText) findViewById(R.id.et_user_cpass);
        bt_signup = (Button) findViewById(R.id.bt_submit);

        bt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u_name=String.valueOf(user_name.getText());
                u_pass=String.valueOf(user_pwd.getText());
                new LogOn().execute();
                Intent in=new Intent(SignUp.this,SendPushMessage.class);
                startActivity(in);
            }
        });
    }

    public class LogOn extends AsyncTask<String, Integer, Boolean>{
        private String data,json_result;
        protected void onPreExecute(){
            super.onPreExecute();
        }
        protected Boolean doInBackground(String... params){
            try{
                Log.i("Helllloooo=", "");
                InstanceID instance=InstanceID.getInstance(SignUp.this);
                String token=instance.getToken("985779802104", GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                Log.i("Helll=",token);
                URL url= new URL("http://newvariable.orgfree.com/signup.php?user_name="+u_name+"&user_pass="+u_pass+"&user_gcm="+token);
                Log.i("called","");
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuilder sb = new StringBuilder();
                while ((data=reader.readLine())!=null){
                    sb.append(data).append("\n");
                }
                json_result=sb.toString();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Boolean bln){
            Snackbar.make(findViewById(android.R.id.content),"Successfull Registration",Snackbar.LENGTH_LONG).show();
        }

    }

}