package project.listick.fake.UI;

import android.annotation.SuppressLint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import project.listick.fake.MainServiceControl;
import project.listick.fake.R;
import project.listick.fake.Services.FixedSpooferService;


/*
 * Created by LittleAngry on 25.12.18 (macOS 10.12)
 * */
public class MapsActivity extends AppCompatActivity {


    /**
     * 1) its a main class and starter class in hole app
     * and responsible for call own presenter
     */


    private static final String TAG = "MapsActivity";


    private MaterialButton mStopContainer;

    private MaterialButton mDoneContainer;



    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_material3);



        mStopContainer = findViewById(R.id.stop_spoofing);
        mDoneContainer = findViewById(R.id.start_spoofing);



        mDoneContainer.setOnClickListener(view -> {
            startService(new Intent(getApplicationContext(), FixedSpooferService.class)
                    .putExtra("latitude", 62.393303)
                    .putExtra("longitude", -96.8181455));

            setResult(Activity.RESULT_OK);
        });


        mStopContainer.setOnClickListener(view -> {
            stopService(new Intent(getApplicationContext(), FixedSpooferService.class));
        });


    }


}
