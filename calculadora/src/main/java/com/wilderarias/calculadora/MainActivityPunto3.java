package com.wilderarias.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

public class MainActivityPunto3 extends AppCompatActivity {

    private EditText eRes;
    private String valor1="", valor2="";
    private Double valor11,valor22, resultado;
    private int val=0, ope=0, leng=15, punt1=0,punt2=0; //ope={1:div,2:prom,3:sum,4:res} val={0:ingreso valor1,1:Ingreso valor2}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_punto3);

        eRes=(EditText) findViewById(R.id.eres);
        eRes.setInputType(InputType.TYPE_NULL);

    }

    public void soluOperacion(View view) {

        switch (view.getId()){
            case R.id.bdel:
                val=0;
                valor1="";
                valor2="";
                ope=0;
                eRes.setText("");
                punt1=0;
                punt2=0;
                leng=5;
                break;
            case R.id.bdiv:
                if(!valor1.equals("") && valor2.equals("")) {
                        ope = 1;
                        val=1;
                        eRes.setText(valor1 + "/");
                }else if(!valor1.equals("") && !valor2.equals("")){
                   totalOperacion();
                    valor1=""+resultado;
                    valor2="";
                    ope=1;
                    val=1;
                    punt2=0;
                    eRes.setText(valor1+"/");
                }
                break;
            case R.id.bpro:
                if(!valor1.equals("") && valor2.equals("")) {
                    ope = 2;
                    val=1;
                    eRes.setText(valor1 + "*");
                }else if(!valor1.equals("") && !valor2.equals("")){
                    totalOperacion();
                    valor1=""+resultado;
                    valor2="";
                    ope=2;
                    val=1;
                    punt2=0;
                    eRes.setText(valor1+"*");
                }
                break;
            case R.id.breg:
                if(!valor1.equals("") && valor2.equals("") && ope==0){
                    valor1=valor1.substring(0,valor1.length()-1);
                    eRes.setText(valor1);
                    val=0;
                }else if(!valor1.equals("") && valor2.equals("") && ope!=0){
                    ope=0;
                    eRes.setText(valor1);
                }else if(!valor1.equals("") && !valor2.equals("")){
                    valor2=valor2.substring(0,valor2.length()-1);
                    switch (ope){
                        case 1:
                            eRes.setText(valor1+"/"+valor2);
                            break;
                        case 2:
                            eRes.setText(valor1+"*"+valor2);
                            break;
                        case 3:
                            eRes.setText(valor1+"+"+valor2);
                            break;
                        case 4:
                            eRes.setText(valor1+"-"+valor2);
                            break;
                    }
                    if(valor2.length()==0){
                        val=1;
                    }
                }

                break;
            case R.id.bsum:
                if(!valor1.equals("") && valor2.equals("")) {
                    ope = 3;
                    val=1;
                    eRes.setText(valor1 + "+");
                }else if(!valor1.equals("") && !valor2.equals("")){
                    totalOperacion();
                    valor1=""+resultado;
                    valor2="";
                    ope=3;
                    val=1;
                    punt2=0;
                    eRes.setText(valor1+"+");
                }
                break;
            case R.id.bres:
                if(valor1.equals("")) {
                    eRes.setText("-");
                    valor1+="-";
                    leng++;
                }else if(!valor1.equals("") && valor2.equals("")){
                    ope = 4;
                    val=1;
                    eRes.setText(valor1 + "-");
                }else if(!valor1.equals("") && !valor2.equals("")){
                    totalOperacion();
                    valor1=""+resultado;
                    valor2="";
                    ope=4;
                    val=1;
                    punt2=0;
                    eRes.setText(valor1+"-");
                }
                break;
            case R.id.bigu:
                totalOperacion();
                break;
            case R.id.bpun:
                if(val==0 && punt1==0){
                    ingresoValores(".");
                    leng++;
                    punt1++;
                }else if(val==1 && punt2==0){
                    ingresoValores(".");
                    leng++;
                    punt2++;
                }
                break;
            case R.id.b9:
                ingresoValores("9");
                break;
            case R.id.b8:
                ingresoValores("8");
                break;
            case R.id.b7:
                ingresoValores("7");
                break;
            case R.id.b6:
                ingresoValores("6");
                break;
            case R.id.b5:
                ingresoValores("5");
                break;
            case R.id.b4:
                ingresoValores("4");
                break;
            case R.id.b3:
                ingresoValores("3");
                break;
            case R.id.b2:
                ingresoValores("2");
                break;
            case R.id.b1:
                ingresoValores("1");
                break;
            case R.id.b0:
                ingresoValores("0");
                break;
        }

    }//fin soluOperacion

    public void totalOperacion(){
        if(valor1.equals(""))
            valor1="0";
        if(valor2.equals(""))
            valor2="0";
        valor11=Double.parseDouble(valor1);
        valor22=Double.parseDouble(valor2);
        leng=5;
        punt1=0;
        punt2=0;
        valor1="";
        valor2="";
        val=0;
        switch (ope){
            case 1://division
                resultado=valor11/valor22;
                resultado=Math.rint(resultado*100000)/100000;
                eRes.setText(""+resultado);
                ope=0;
                break;
            case 2://producto
                resultado=valor11*valor22;
                resultado=Math.rint(resultado*100000)/100000;
                eRes.setText(""+resultado);
                ope=0;
                break;
            case 3://suma
                resultado=valor11+valor22;
                resultado=Math.rint(resultado*100000)/100000;
                eRes.setText(""+resultado);
                ope=0;
                break;
            case 4://resta
                resultado=valor11-valor22;
                resultado=Math.rint(resultado*100000)/100000;
                eRes.setText(""+resultado);
                ope=0;
                break;
        }
    }

    public void ingresoValores(String b){
        if(val==0 && valor1.length()<=leng){
            valor1+=b;
            eRes.setText(valor1);
            if(valor1.length()==leng){
                val=1;
            }
        }else if(val==1 && valor2.length()<=5 && ope!=0){
            valor2+=b;
            switch (ope){
                case 1:
                    eRes.setText(valor1+"/"+valor2);
                    break;
                case 2:
                    eRes.setText(valor1+"*"+valor2);
                    break;
                case 3:
                    eRes.setText(valor1+"+"+valor2);
                    break;
                case 4:
                    eRes.setText(valor1+"-"+valor2);
                    break;
            }
        }
    }//fin ingresoValores
}//fin MainActivity
