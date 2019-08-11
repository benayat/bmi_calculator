package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class results extends AppCompatActivity {
    TextView calculatedWeight;
    TextView bmi;
    TextView weightStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        calculatedWeight = findViewById(R.id.weightResult);
        Intent intent = getIntent();
        String calculatedWeightResult = intent.getStringExtra("calculated weight");
        calculatedWeight.setText(calculatedWeightResult);
        bmi = findViewById(R.id.bmiResult);
        String bmiResult = intent.getStringExtra("bmi");
        bmi.setText(bmiResult);
        weightStatus = findViewById(R.id.weightStatusResult);
        String weightStatusResult = intent.getStringExtra("weight status");
        weightStatus.setText(weightStatusResult);

    }
    public void clickExit(View v){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

}
