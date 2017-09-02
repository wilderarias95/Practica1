package com.wilderarias.formularioderegistro;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;

public class MainActivityPunto2 extends AppCompatActivity {

    private static TextView tFechaNaci,tUsername,tPassw1,tPassw2,tEmail,tDate,tCity,tData;
    private EditText eUsername, eEmail, ePass1, ePass2;
    private CheckBox cRead, cSleep, cSwim, cSing;
    private Spinner sCiudades;
    private int y,m,d,valemail=1,valpass=1,valuserna=1,valfec=1;
    private String ciudadNaci="Medellín",sexo="Male",fechaNaci="";
    private String userName,email,pass1,pass2,hobbies, datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_punto2);

        tFechaNaci=(TextView) findViewById(R.id.tFechaNaci);
        sCiudades  = (Spinner) findViewById(R.id.sCiudades);
        eUsername=(EditText) findViewById(R.id.eUserName);
        eEmail=(EditText) findViewById(R.id.eCorreo);
        ePass1=(EditText) findViewById(R.id.eUserPassw1);
        ePass2=(EditText) findViewById(R.id.eUserPassw2);
        tUsername=(TextView) findViewById(R.id.tUsername);
        tPassw1=(TextView) findViewById(R.id.tPassw1);
        tPassw2=(TextView) findViewById(R.id.tPassw2);
        tEmail=(TextView) findViewById(R.id.tEmail);
        tDate=(TextView) findViewById(R.id.tDate);
        tCity=(TextView) findViewById(R.id.tCity);
        tData=(TextView) findViewById(R.id.tData);
        cRead=(CheckBox) findViewById(R.id.cRead);
        cSleep=(CheckBox) findViewById(R.id.cSleep);
        cSwim=(CheckBox) findViewById(R.id.cSwim);
        cSing=(CheckBox) findViewById(R.id.csing);


// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.cityOfColombia, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        sCiudades.setAdapter(adapter);
        sCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ciudadNaci=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void onRadioButtonClicked(View view) {

        if(view.getId()==R.id.rMale){
            sexo="Male";
        }else{
            sexo="Famale";
        }

    }

    public void registrar(View view) {
        hobbies="";
        userName=eUsername.getText().toString();
        if(userName.equals("")){
            tUsername.setTextColor(getResources().getColor(R.color.red));
            valuserna=1;
        }else{
            valuserna=0;
            tUsername.setTextColor(getResources().getColor(R.color.black));
        }
        pass1=ePass1.getText().toString();
        pass2=ePass2.getText().toString();
        if(!(pass1.equals(pass2))||pass1.equals("")||pass2.equals("")){
            tPassw1.setTextColor(getResources().getColor(R.color.red));
            tPassw2.setTextColor(getResources().getColor(R.color.red));
            valpass=1;
        }else{
            valpass=0;
            tPassw1.setTextColor(getResources().getColor(R.color.black));
            tPassw2.setTextColor(getResources().getColor(R.color.black));
        }
        email=eEmail.getText().toString();

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        if(!pattern.matcher(email).matches()){
            tEmail.setTextColor(getResources().getColor(R.color.red));
            valemail=1;
        }else{
            valemail=0;
            tEmail.setTextColor(getResources().getColor(R.color.black));
        }
        fechaNaci=tFechaNaci.getText().toString();
        if(fechaNaci.equals("dd/mm/yy")){
            tDate.setTextColor(getResources().getColor(R.color.red));
            valfec=1;
        }
        else{
            valfec=0;
            tDate.setTextColor(getResources().getColor(R.color.black));
        }


        if(cRead.isChecked())
            hobbies+="Read. ";
        if (cSwim.isChecked())
            hobbies+="Swim. ";
        if(cSleep.isChecked())
            hobbies+="Sleep. ";
        if (cSing.isChecked())
            hobbies+="Sing.";

        if(valemail==0 && valuserna==0 && valpass==0 && valfec==0){
            Toast.makeText(this, "Registration successful.", Toast.LENGTH_LONG).show();
            datos="Username: "+userName+"\nEmail: "+email+"\nSexo: "+sexo+"\nDate of birth: "+fechaNaci+"\nCity of birth: "
                    +ciudadNaci+"\nHobbies: "+hobbies ;
            tData.setText(datos);
        }else{
            hobbies="";
            Toast.makeText(this,"Check the information in red",Toast.LENGTH_LONG).show();
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
            tFechaNaci.setText(day+"/"+month+"/"+year);
        }
    }

    public void fechaNacimiento(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

}

