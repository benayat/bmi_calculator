package com.example.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    static String gender;
    static String ageType;
    RadioGroup Gender;
    SeekBar seekbar;
    TextView heightShow;
    Button calculate;
    EditText ageText;
    EditText weightText;
    static int age;
    static int height;
    static int weight;

    static double calculatedWeight;
    static double bmi;
    String weightStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//first, taking care of the radio group, to get the gender and put it in gender string.

        Gender = findViewById(R.id.Gender);
        Gender.clearCheck();
        Gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radiobutton = group.findViewById(checkedId);
                gender = radiobutton.getText().toString();
            }
        });
//age part: first, the spinner, and then edit text.
//the spinner - choose between years and month

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("years");
        categories.add("month");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        //taking care of the seekbar:
        heightShow = findViewById(R.id.hightShow);
        seekbar = findViewById(R.id.seekBar);
        // Get the progress value of the SeekBar
        // using setOnSeekBarChangeListener() method
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // When the progress value has changed
                // increment 1 in progress and
                // increase the textsize
                // with the value of progress
                heightShow.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//calculate button. moves us to the results activity and shows the results there.
        calculate = findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ageText = findViewById(R.id.age);
                age = Integer.parseInt(ageText.getText().toString());
                weightText = findViewById(R.id.weight);
                weight = Integer.parseInt(weightText.getText().toString());
                height = Integer.parseInt(heightShow.getText().toString());
                weightCalculation();
                bmiCalculation();
                Intent intent = new Intent(getApplicationContext(), results.class);
                intent.putExtra("bmi", String.format("%.2f",bmi));
                intent.putExtra("weight status", weightStatus);
                intent.putExtra("calculated weight", String.valueOf(calculatedWeight));

                startActivity(intent);
            }
        });

    }

    //defines what happen when spinner is set.
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        String item = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        ageType = item;
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void weightCalculation(){
        if(ageType.equals("month")){
            calculatedWeight = 0.5*age+4;
        } else if(age<10 && age>1){
            calculatedWeight = 2*age+10;
        }else if(gender.equals("male")){
            calculatedWeight = 48+1.1*(height-152);
        }else if(gender.equals("female")){
            calculatedWeight = 45.4+0.9*(height-152);
        }
    }
    public void bmiCalculation(){
        bmi = weight/(Math.pow(((double)height/100), 2));

        if(bmi<15){
            weightStatus = "Anorexic";
        }else if(bmi>=15 && bmi <18.5){
            weightStatus = "UnderWeight";
        }else if(bmi>=18.5 && bmi<24.9){
            weightStatus = "Normal";
        }else if(bmi>=25 && bmi<=29.9){
            weightStatus = "Overweight";
        }else if(bmi>=30 && bmi<=35){
            weightStatus = "Obese";
        }else{
            weightStatus = "Extreme Obese";
        }
    }

}

/*

what I want to do:
the app has two activities(an activity is a screen, which contains interactive components.
it consists of two files, a java file and an xml file,
one is the main activity, which we used to get all te input from the user - gender, age, weight and height.
to do that we used radio buttons(https://en.wikipedia.org/wiki/Radio_button) for the gender,
spinner(https://en.wikipedia.org/wiki/Spinner_(computing)) for the age(month or years),
a seekbar(https://developer.android.com/reference/android/widget/SeekBar) for the height,
and edittext(https://developer.android.com/reference/android/widget/EditText) for
height and age.
I added the "calculate" button, to calculate the result and take us to the next activity, "results". the sole purpose of "results" activity
is to display three results: the calculated weight(according to the age based formulas as listed below), the bmi and
weight status. we used textviews(https://developer.android.com/reference/android/widget/TextView) to display the results.
the formulas:
- calculated weight:
For kids: weight = 0/5 * months + 4	 	0 <= age <= 1
 weight = 2 * years + 10			1 <= age <= 10
for other ages:
male weight (kg) = 48 + 1.1 * (h – 152)		h = height in cm
female weight (kg) = 45.4 + 0.9 * (h – 152)	h = height in cm

- bmi formula:
BMI = weight (kg) / height2 (m)

- the program was built with the guidelines of MVC pattern: the model is the "res" directory, which contains all the resources - strings, pictures, etc.
the view is the xml file/s, and the controler is the .java file/s. this separation makes it easier to understand the structure of the program.

Run/close the application
To run the application, open it in android studio, make an emulator, and press "run".
To close the application:  from the second/result screen click the "exit" button.
and then "quit".

 */