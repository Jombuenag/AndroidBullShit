package com.example.takuma.pasodeparametros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Pantalla2 extends AppCompatActivity {
    String nombre; //AQUI GUARDAMOS LOS DATOS DE PANTALLA1
    String sexo; //AQUI GUARDAMOS LOS DATOS DE PANTALLA1

    String mensaje;
    Button enviarDatos;
    TextView nombrePantalla2;
    String rating = "";
    String carnet = "";
    String seekRating = "";

    final int PASOPANTALLA_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        //ENLAZAMOS EL TEXTVIEW DEL XML CON EL OBJETO TEXTVIEW
        enviarDatos = (Button) findViewById(R.id.botonEnviar);
        nombrePantalla2 = (TextView) findViewById(R.id.nombrePantalla2);
        //rating = (String)findViewById(R.id.rating);

        //VAMOS A PILLAR LOS DATOS QUE NOS DA EL BUNDLE DE LA ACTIVITY 1
        Bundle b = getIntent().getExtras();
        if (b != null) {
            nombre = b.getString("Nombre");
            rating = b.getString("Resultado");
            seekRating = b.getString("SeekResultado");
            carnet = b.getString("Permiso");

            //rating.setText(b.getString("Resultado"));
            sexo = b.getString("Sexo");
            if(sexo.compareTo("Hombre") == 0) {
                mensaje = ("Lord " + nombre + " ¿Esto es lo que nos has contado? " +
                        "\n" + "y nos has puesto una puntuación de  : " + rating + " estrellas " +
                        "\n" + "o " + seekRating + "/100. Menos mal que " + carnet);
            } else {
                mensaje = ("Lady " + nombre + " ¿Esto es lo que nos has contado? " +
                        "\n" + "y nos has puesto una puntuación de  : " + rating + " estrellas " +
                        "\n" + "o " + seekRating + "/100. Menos mal que " + carnet);
            }
            nombrePantalla2.setText(mensaje.toString());
        } else {
            nombrePantalla2.setText("No he recibido ningún dato");
        }
        //VAMOS A PONERLE UN LISTENER AL BOTON DE VOLVER
        enviarDatos.setOnClickListener(new View.OnClickListener() {
            Intent i = new Intent(getApplicationContext(),Pantalla1.class);
            @Override
            public void onClick(View v) {
                startActivityForResult(i, PASOPANTALLA_1);
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

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
