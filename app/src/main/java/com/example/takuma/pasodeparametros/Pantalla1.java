package com.example.takuma.pasodeparametros;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Pantalla1 extends AppCompatActivity {

    Button enviarInfo;
    EditText textoNombre;
    RadioGroup radioGroup;
    TextView datosRecibidos;
    final int PASOAPANTALLA_2=1;
    String mensaje ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla1);

        enviarInfo = (Button) findViewById(R.id.enviarInfo);
        textoNombre = (EditText) findViewById(R.id.textoNombre);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        datosRecibidos = (TextView)findViewById(R.id.textoNombre);

        enviarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datosCorrectos()){
                    Intent i = new Intent(getApplicationContext(),Pantalla2.class);
                    Bundle b = new Bundle();
                    b.putString("Nombre",textoNombre.getText().toString()); //AÑADE EL PARAMETRO NOMBRE
                    switch (radioGroup.getCheckedRadioButtonId()) {
                        case R.id.rb1:
                            b.putString("Sexo","Hombre");
                            break;
                        case R.id.rb2:
                            b.putString("Sexo","Mujer");
                            break;
                        default:
                            b.putString("Sexo","Indefinido");
                    }
                    i.putExtras(b); // AÑADE EL OBJETO BUNDLE AL INTENT
                    startActivityForResult(i,PASOAPANTALLA_2); //LLAMA A LA SUBACTIVIDAD Y AL INTENT QUE CONTIENE EL BUNDLE
                } else {
                    Toast.makeText(getApplicationContext(), "Datos incorrectos, falta rellenar algún campo", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean datosCorrectos() {
        if (textoNombre.getText().length()<=0) {
            return false;
        } else {
            if (radioGroup.getCheckedRadioButtonId()==-1) {
                return false;
            } else {
                return true;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent i){
        super.onActivityResult(requestCode, resultCode, i);
        switch (requestCode){
            case PASOAPANTALLA_2:
                gestionaSubActivity(resultCode, i);
                break;
        }
    }

    public void gestionaSubActivity(int resutlCode, Intent i) {
        if (resutlCode == RESULT_OK) {
            Bundle b = i.getExtras();
            int edad = b.getInt("Edad");
            //if (edad < 18) mensaje = new String("¡Yogurin!");
            //if ((edad >= 18) && (edad < 25)) mensaje = new String("En la flor de la vida");
            //if (edad > 25) mensaje = new String("Ya van pesando las cosas de la edad");
            //AHORA PASAMOS POR PANTALLA
            datosRecibidos.setText(mensaje.toString());
            desactivaComponentes();
        } else {
            Toast.makeText(getApplicationContext(), "Error en la SubActividad 1", Toast.LENGTH_LONG).show();
        }
    }

    public void desactivaComponentes(){
        textoNombre.setEnabled(false); //DESACTIVA EL CAMPO NOMBRE
        radioGroup.setEnabled(false); //LO MISMO CON EL RG PERO HAY QUE IR UNO A UNO
        for(int j=0;j<radioGroup.getChildCount();j++){
            radioGroup.getChildAt(j).setEnabled(false);
        }
        enviarInfo.setEnabled(false); //POR ULTIMO DESACTIVAMOS EL BOTON;
    }


    public void pasaDePantalla(View v){
        Intent i = new Intent(getApplicationContext(),Pantalla2.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_pantalla1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
