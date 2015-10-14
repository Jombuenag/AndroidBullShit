package com.example.takuma.pasodeparametros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pantalla2 extends AppCompatActivity {
    String nombre; //AQUI GUARDAMOS LOS DATOS DE PANTALLA1
    String sexo; //AQUI GUARDAMOS LOS DATOS DE PANTALLA1

    String mensaje;
    Button finalizar;
    EditText edad;
    EditText nombrePantalla2;
    final int PASOPANTALLA_1=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        finalizar = (Button) findViewById(R.id.botonFinalizar);
        edad = (EditText) findViewById(R.id.textoEdad);
        nombrePantalla2 = (EditText) findViewById(R.id.nombrePantalla2);

        //VAMOS A PILLAR LOS DATOS QUE NOS DA EL BUNDLE DE LA ACTIVITY 1
        Bundle b = getIntent().getExtras();
        if (b != null) {
            nombre = b.getString("Nombre");
            sexo = b.getString("Sexo");
            if(sexo.compareTo("Hombre") == 0) {
                mensaje = nombre;
            } else {
                mensaje = nombre;
            }
            nombrePantalla2.setText(mensaje.toString());
        } else {
            nombrePantalla2.setText("No he recibido ningún dato");
        }
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Pantalla1.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pantalla2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
