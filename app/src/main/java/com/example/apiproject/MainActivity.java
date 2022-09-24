package com.example.apiproject;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    AppCompatImageView image;
    AppCompatButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image= findViewById(R.id.random_image);
        button=findViewById(R.id.call_api_button);
        getImageFromApiCall();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getImageFromApiCall();

            }
        });




    }
    void getImageFromApiCall(){
        ImageAPI apiServer = User.getRetrofit().create(ImageAPI.class);
        apiServer.getImage().enqueue(new Callback<JsonObject>() {


            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try {
                    JSONObject jsonObject=new JSONObject(new Gson().toJson(response.body()));
                    String url=jsonObject.getString("message");

                    Log.i(TAG,"onResponse: "+ jsonObject.toString());
                    Glide.with(MainActivity.this)
                            .load(url)
                            .into(image);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {


            }
        });

    }
}