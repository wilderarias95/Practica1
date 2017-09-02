package com.wilderarias.calculadoraderesistencias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivityPunto4 extends AppCompatActivity {
    private Spinner sBanda1,sBanda2,sMult,sTole;
    private EditText eResultados,eResulTole;
    private EditText eBanda1, eBanda2, eMult, eTole;
    private int banda1=0, banda2=0, mult=0, valorTole=1;
    private double valorResis=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_punto4);

        sBanda1=(Spinner) findViewById(R.id.sbanda1);
        sBanda2=(Spinner) findViewById(R.id.sbanda2);
        sMult=(Spinner) findViewById(R.id.smultiplicador);
        sTole=(Spinner) findViewById(R.id.stolerancia);
        eResultados=(EditText) findViewById(R.id.eResultados);
        eResultados.setInputType(InputType.TYPE_NULL);
        eResulTole=(EditText)findViewById(R.id.eResultTole);
        eResulTole.setInputType(InputType.TYPE_NULL);
        eBanda1=(EditText) findViewById(R.id.ebanda1);
        eBanda1.setInputType(InputType.TYPE_NULL);
        eBanda2=(EditText) findViewById(R.id.ebanda2);
        eBanda2.setInputType(InputType.TYPE_NULL);
        eMult=(EditText)findViewById(R.id.emultiplicador);
        eMult.setInputType(InputType.TYPE_NULL);
        eTole=(EditText)findViewById(R.id.etolerancia);
        eTole.setInputType(InputType.TYPE_NULL);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterColoresBanda = ArrayAdapter.createFromResource(this,
                R.array.coloresbandaIandII, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterColoresBanda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner

        sBanda1.setAdapter(adapterColoresBanda);
        sBanda1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                banda1=i;
                eBanda1.setText(""+i);
                setColorMulti(i,1);
                calcularResistencia();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sBanda2.setAdapter(adapterColoresBanda);
        sBanda2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                banda2=i;
                eBanda2.setText(""+i);
                setColorMulti(i,2);
                calcularResistencia();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterColoresMult = ArrayAdapter.createFromResource(this,
                R.array.coloresmultiplicador, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterColoresMult.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner

        sMult.setAdapter(adapterColoresMult);
        sMult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mult=i;
                setColorMulti(i,3);
                calcularResistencia();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterColoresTole = ArrayAdapter.createFromResource(this,
                R.array.colorestolerancia, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapterColoresTole.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner

        sTole.setAdapter(adapterColoresTole);
        sTole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setColorMulti(i,4);
                calcularResistencia();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    public void calcularResistencia() {
        if(mult==7){
            valorResis = ((double)banda1 * 10 + (double) banda2) /10;
            eResultados.setText(valorResis+" Ohms");
            eResulTole.setText(valorTole+" %");

        }else if(mult==8){
            valorResis = ((double) banda1 * 10 + (double) banda2) /100;
            eResultados.setText(valorResis+" Ohms");
            eResulTole.setText(valorTole+" %");
        }else {
            valorResis = (banda1 * 10 + banda2) * (int) Math.pow(10, mult);
            eResultados.setText(""+ (int)valorResis+" Ohms");
            eResulTole.setText(valorTole+" %");
        }



    }

    public void setColorMulti(int pos, int band){
        switch (pos){
            case 0:
                switch (band){
                    case 1:
                        eBanda1.setBackgroundColor(getResources().getColor(R.color.black));
                        eBanda1.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case 2:
                        eBanda2.setBackgroundColor(getResources().getColor(R.color.black));
                        eBanda2.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case 3:
                        eMult.setBackgroundColor(getResources().getColor(R.color.black));
                        eMult.setTextColor(getResources().getColor(R.color.white));
                        eMult.setText("x"+(int) Math.pow(10, mult));
                        break;
                    case 4:
                        eTole.setBackgroundColor(getResources().getColor(R.color.brown));
                        eTole.setTextColor(getResources().getColor(R.color.white));
                        valorTole=1;
                        eTole.setText(""+valorTole+"%");
                        break;
                }
                break;
            case 1:
                switch (band){
                    case 1:
                        eBanda1.setBackgroundColor(getResources().getColor(R.color.brown));
                        eBanda1.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 2:
                        eBanda2.setBackgroundColor(getResources().getColor(R.color.brown));
                        eBanda2.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 3:
                        eMult.setBackgroundColor(getResources().getColor(R.color.brown));
                        eMult.setTextColor(getResources().getColor(R.color.black));
                        eMult.setText("x"+(int) Math.pow(10, mult));
                        break;
                    case 4:
                        eTole.setBackgroundColor(getResources().getColor(R.color.red));
                        eTole.setTextColor(getResources().getColor(R.color.black));
                        valorTole=2;
                        eTole.setText(""+valorTole+"%");
                        break;
                }
                break;
            case 2:
                switch (band){
                    case 1:
                        eBanda1.setBackgroundColor(getResources().getColor(R.color.red));
                        eBanda1.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 2:
                        eBanda2.setBackgroundColor(getResources().getColor(R.color.red));
                        eBanda2.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 3:
                        eMult.setBackgroundColor(getResources().getColor(R.color.red));
                        eMult.setTextColor(getResources().getColor(R.color.black));
                        eMult.setText("x"+(int) Math.pow(10, mult));
                        break;
                    case 4:
                        eTole.setBackgroundColor(getResources().getColor(R.color.goldenrod));
                        eTole.setTextColor(getResources().getColor(R.color.black));
                        valorTole=5;
                        eTole.setText(""+valorTole+"%");
                        break;
                }
                break;
            case 3:
                switch (band){
                    case 1:
                        eBanda1.setBackgroundColor(getResources().getColor(R.color.orange));
                        eBanda1.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 2:
                        eBanda2.setBackgroundColor(getResources().getColor(R.color.orange));
                        eBanda2.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 3:
                        eMult.setBackgroundColor(getResources().getColor(R.color.orange));
                        eMult.setTextColor(getResources().getColor(R.color.black));
                        eMult.setText("x"+(int) Math.pow(10, mult));
                        break;
                    case 4:
                        eTole.setBackgroundColor(getResources().getColor(R.color.silver));
                        eTole.setTextColor(getResources().getColor(R.color.black));
                        valorTole=10;
                        eTole.setText(""+valorTole+"%");
                        break;
                }
                break;
            case 4:
                switch (band) {
                    case 1:
                        eBanda1.setBackgroundColor(getResources().getColor(R.color.yellow));
                        eBanda1.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 2:
                        eBanda2.setBackgroundColor(getResources().getColor(R.color.yellow));
                        eBanda2.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 3:
                        eMult.setBackgroundColor(getResources().getColor(R.color.yellow));
                        eMult.setTextColor(getResources().getColor(R.color.black));
                        eMult.setText("x"+(int) Math.pow(10, mult));
                        break;
                }
                break;
            case 5:
                switch (band) {
                    case 1:
                        eBanda1.setBackgroundColor(getResources().getColor(R.color.green));
                        eBanda1.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 2:
                        eBanda2.setBackgroundColor(getResources().getColor(R.color.green));
                        eBanda2.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 3:
                        eMult.setBackgroundColor(getResources().getColor(R.color.green));
                        eMult.setTextColor(getResources().getColor(R.color.black));
                        eMult.setText("x"+(int) Math.pow(10, mult));
                        break;
                }
                break;
            case 6:
                switch (band) {
                    case 1:
                        eBanda1.setBackgroundColor(getResources().getColor(R.color.blue));
                        eBanda1.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 2:
                        eBanda2.setBackgroundColor(getResources().getColor(R.color.blue));
                        eBanda2.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 3:
                        eMult.setBackgroundColor(getResources().getColor(R.color.blue));
                        eMult.setTextColor(getResources().getColor(R.color.black));
                        eMult.setText("x"+(int) Math.pow(10, mult));
                        break;
                }
                break;
            case 7:
                switch (band) {
                    case 1:
                        eBanda1.setBackgroundColor(getResources().getColor(R.color.purple));
                        eBanda1.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 2:
                        eBanda2.setBackgroundColor(getResources().getColor(R.color.purple));
                        eBanda2.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 3:
                        eMult.setBackgroundColor(getResources().getColor(R.color.goldenrod));
                        eMult.setTextColor(getResources().getColor(R.color.black));
                        eMult.setText("x"+" /10");
                        break;
                }
                break;
            case 8:
                switch (band) {
                    case 1:
                        eBanda1.setBackgroundColor(getResources().getColor(R.color.darkgray));
                        eBanda1.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 2:
                        eBanda2.setBackgroundColor(getResources().getColor(R.color.darkgray));
                        eBanda2.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 3:
                        eMult.setBackgroundColor(getResources().getColor(R.color.silver));
                        eMult.setTextColor(getResources().getColor(R.color.black));
                        eMult.setText("x"+"/100");
                        break;
                }
                break;
            case 9:
                switch (band) {
                    case 1:
                        eBanda1.setBackgroundColor(getResources().getColor(R.color.white));
                        eBanda1.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case 3:
                        eMult.setBackgroundColor(getResources().getColor(R.color.white));
                        eMult.setTextColor(getResources().getColor(R.color.black));
                        break;
                }
                break;

        }
    }
}
