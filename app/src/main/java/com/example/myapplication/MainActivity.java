package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    TextView sliderText;
    Button pinButton;
    SeekBar slider;
    EditText ip, port;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderText = findViewById(R.id.textView3);

        pinButton = findViewById(R.id.button_pin);
        slider = findViewById(R.id.seekBar);

        ip = findViewById(R.id.editTextIPAddress);
        port = findViewById(R.id.editTextPortNumber);

        pinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUrl();
            }
        });

        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sliderText.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                checkUrl();
            }
        });
    }

    private void checkUrl(){
        String ipAddress = ip.getText().toString().trim();
        String portNumber = port.getText().toString().trim();

        if(ipAddress.length()>0 && portNumber.length()>0) {
            String url = "http://" + ipAddress + ":" + portNumber+"/";
            sendPing(url);
        }
    }

    private void sendPing(String url){
        String pin = sliderText.getText().toString();
        NetworkClient.getInstance(url)
                .getApi()
                .sendPing(pin)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()){
                            Log.d("myTag", "Success");
                        }
                        else{
                            Log.d("myTag", "Some error");
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.d("myTag", "failed");
                    }
                });
    }
}
