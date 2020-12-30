package com.example.detailapp;

import android.os.Bundle;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
public String fname,lname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button register = findViewById(R.id.register);


        register.setOnClickListener(
                v ->
                {
                    fname = ((EditText) findViewById(R.id.fname)).getText().toString();
                    lname = ((EditText) findViewById(R.id.lname)).getText().toString();
                    save_data(fname,lname);
                }
        );

    }
    void save_data(String fname,String lname){
        if(fname.isEmpty() && lname.isEmpty()){
           Toast.makeText(MainActivity.this,"Fill all details",Toast.LENGTH_LONG).show();

        }
        else if(fname.isEmpty() || fname.length()<2 ){
            Toast.makeText(MainActivity.this,"Enter Valid First Name",Toast.LENGTH_LONG).show();
        }
        else if(lname.isEmpty() || lname.length()<2){
            Toast.makeText(MainActivity.this,"Enter Valid Last Name",Toast.LENGTH_LONG).show();
        }

        else{
            new Thread(()-> {
                OkHttpClient okHttpClient = new OkHttpClient();

                FormBody formBody = new FormBody.Builder().add("fname", fname).add("lname",lname).build();
                Request request = new Request.Builder()
                        .url(Constant.ADD)
                        .post(formBody)
                        .build();
                try (Response response = okHttpClient.newCall(request).execute()) {
                    Looper.prepare();
                    if (Boolean.parseBoolean(response.body().string()))
                    {
                        Toast.makeText(this, "registration success", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this, "registration failed", Toast.LENGTH_SHORT).show();
                    }
                    Looper.loop();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
