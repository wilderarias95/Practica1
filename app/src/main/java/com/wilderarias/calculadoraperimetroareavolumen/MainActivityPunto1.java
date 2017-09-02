package com.wilderarias.calculadoraperimetroareavolumen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivityPunto1 extends AppCompatActivity {

    private RadioButton rCirculo,rCuadrado,rCubo,rTriangulo;
    private LinearLayout ledit2,ledit3;
    private EditText eedit1, eedit2, eedit3;
    private TextView tResultados;
    private static double pi=3.141592;
    private double area, perimetro, volumen;
    private ImageView iFiguras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_punto1);

        rCirculo=(RadioButton)findViewById(R.id.rCirculo);
        rCuadrado=(RadioButton)findViewById(R.id.rCuadrado);
        rCubo=(RadioButton)findViewById(R.id.rCubo);
        rTriangulo=(RadioButton)findViewById(R.id.rTriangulo);
        ledit2=(LinearLayout) findViewById(R.id.ledit2);
        ledit3=(LinearLayout) findViewById(R.id.ledit3);
        eedit1=(EditText) findViewById(R.id.eedit1);
        eedit2=(EditText) findViewById(R.id.eedit2);
        eedit3=(EditText) findViewById(R.id.eedit3);
        tResultados=(TextView) findViewById(R.id.tResultados);
        iFiguras=(ImageView) findViewById(R.id.iFiguras);
    }

    public void onRadioButtonClicked(View view) {

        //Hace visible los distintos EditText, según la figura seleccionada por el usuario
        switch(view.getId()){
            case R.id.rCirculo:
                ledit2.setVisibility(View.GONE);
                ledit3.setVisibility(View.GONE);
                tResultados.setVisibility(View.GONE);
                eedit1.setText("");
                eedit1.setHint(R.string.radio);
                iFiguras.setImageResource(R.drawable.radio);
//Hola
                break;
            case R.id.rCuadrado:
                ledit2.setVisibility(View.VISIBLE);
                ledit3.setVisibility(View.GONE);
                tResultados.setVisibility(View.GONE);
                eedit1.setText("");
                eedit2.setText("");
                eedit1.setHint(R.string.lado1);
                eedit2.setHint(R.string.lado2);
                iFiguras.setImageResource(R.drawable.cuadrado);
                break;

            case R.id.rCubo:
                ledit2.setVisibility(View.VISIBLE);
                ledit3.setVisibility(View.VISIBLE);
                tResultados.setVisibility(View.GONE);
                eedit1.setText("");
                eedit2.setText("");
                eedit3.setText("");
                eedit1.setHint(R.string.lado1);
                eedit2.setHint(R.string.lado2);
                eedit3.setHint(R.string.altura);
                iFiguras.setImageResource(R.drawable.cubo);
                break;
            case R.id.rTriangulo:
                ledit2.setVisibility(View.VISIBLE);
                ledit3.setVisibility(View.VISIBLE);
                tResultados.setVisibility(View.GONE);
                eedit1.setText("");
                eedit2.setText("");
                eedit3.setText("");
                eedit1.setHint(R.string.cateto1);
                eedit2.setHint(R.string.cateto2);
                eedit3.setHint(R.string.hipotenusa);
                iFiguras.setImageResource(R.drawable.triangulo);
                break;
        }
    }

    public void calcularResultados(View view) {

        //Realiza los calculos de area, perimetro o volumen segun el RadioButton seleccionado
        if(rCirculo.isChecked()){
            if(eedit1.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(),R.string.wValores,Toast.LENGTH_LONG).show();
            }else{
                double radio = Double.parseDouble(eedit1.getText().toString());
                perimetro = 2 * pi * radio;
                area = pi * radio * radio;
                tResultados.setVisibility(View.VISIBLE);
                tResultados.setText(getText(R.string.perimetro) + ": " + perimetro + "\n" + getText(R.string.area) + ": " + area);
            }

        }else if (rCuadrado.isChecked()) {
            if(eedit1.getText().toString().equals("") || eedit2.getText().toString().equals("") ) {
                Toast.makeText(getApplicationContext(),R.string.wValores,Toast.LENGTH_LONG).show();
            }else {
                double lado1 = Double.parseDouble(eedit1.getText().toString());
                double lado2 = Double.parseDouble(eedit2.getText().toString());
                perimetro = 2 * (lado1 + lado2);
                area = lado1 * lado2;
                tResultados.setVisibility(View.VISIBLE);
                tResultados.setText(getText(R.string.perimetro) + ": " + perimetro + "\n" + getText(R.string.area) + ": " + area);
            }

        } else if (rCubo.isChecked()) {
            if(eedit1.getText().toString().equals("") || eedit2.getText().toString().equals("")
                    || eedit3.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(),R.string.wValores,Toast.LENGTH_LONG).show();
            }else {
                double lado1 = Double.parseDouble(eedit1.getText().toString());
                double lado2 = Double.parseDouble(eedit2.getText().toString());
                double altura = Double.parseDouble(eedit3.getText().toString());
                area = 2 * (lado1 * lado2 + lado1 * altura + lado2 * altura);
                volumen = lado1 * lado2 * altura;
                tResultados.setVisibility(View.VISIBLE);
                tResultados.setText(getText(R.string.area) + ": " + area + "\n" + getText(R.string.volumen) + ": " + volumen);
            }

        } else if (rTriangulo.isChecked()) {
            if(eedit1.getText().toString().equals("") || eedit2.getText().toString().equals("")
                    || eedit3.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(),R.string.wValores,Toast.LENGTH_LONG).show();
            }else {
                double cateto1 = Double.parseDouble(eedit1.getText().toString());
                double cateto2 = Double.parseDouble(eedit2.getText().toString());
                double hipotenusa = Double.parseDouble(eedit3.getText().toString());
                perimetro = cateto1 + cateto2 + hipotenusa;
                area = 0.5*cateto1*cateto2;
                tResultados.setVisibility(View.VISIBLE);
                tResultados.setText(getText(R.string.perimetro) + ": " + perimetro + "\n" + getText(R.string.area) + ": " + area);
            }
        }
    }
}
