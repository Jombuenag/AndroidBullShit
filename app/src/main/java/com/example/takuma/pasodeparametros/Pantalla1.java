package com.example.takuma.pasodeparametros;

import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Pantalla1 extends AppCompatActivity {

    Button enviarInfo;
    TextView textoNombre;
    RadioGroup radioGroup;
    TextView datosRecibidos;
    Switch carnetConducir;
    RatingBar ratingBar;
    SeekBar seekBar;

    final int PASOAPANTALLA_2=1;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setTitle("PasoDeParametros");
        setContentView(R.layout.activity_pantalla1);


        textoNombre = (EditText) findViewById(R.id.textoNombre);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        carnetConducir = (Switch)findViewById(R.id.switchConducir);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        enviarInfo = (Button) findViewById(R.id.enviarInfo);

        enviarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datosCorrectos()){
                    Intent i = new Intent(getApplicationContext(),Pantalla2.class);
                    Bundle b = new Bundle();
                    b.putString("Nombre", textoNombre.getText().toString()); //AÑADE EL PARAMETRO NOMBRE
                    b.putString("Resultado", String.valueOf(ratingBar.getRating()));//AÑADE LA PUNTUACION
                    b.putString("SeekResultado", String.valueOf(seekBar.getProgress()));
                    switch (radioGroup.getCheckedRadioButtonId()) {
                        case R.id.rbHombre:
                            b.putString("Sexo","Hombre");
                            break;
                        case R.id.rbMujer:
                            b.putString("Sexo","Mujer");
                            break;
                        default:
                            b.putString("Sexo","Indefinido");
                    }
                    if(carnetConducir.isEnabled()){
                            b.putString("Permiso","tienes el carnet");
                    }else{
                            b.putString("Permiso","no tienes el carnet");
                    }

                    i.putExtras(b); // AÑADE EL OBJETO BUNDLE AL INTENT
                    startActivityForResult(i, PASOAPANTALLA_2); //LLAMA A LA SUBACTIVIDAD Y AL INTENT QUE CONTIENE EL BUNDLE

                    NotificationCompat.Builder mensaje = new NotificationCompat.Builder(Pantalla1.this);
                        if(radioGroup.getCheckedRadioButtonId() == R.id.rbHombre) {
                            mensaje.setContentTitle("Hombre");
                            mensaje.setContentText("Has elegido chico ");
                            mensaje.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.vaultboy));
                            mensaje.setSmallIcon(R.drawable.vaultboy);
                        }else {
                            mensaje.setContentTitle("Mujer");
                            mensaje.setContentText("Has elegido chica");
                            mensaje.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.chibicat));
                            mensaje.setSmallIcon(R.drawable.chibicat);
                        }
                        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(1, mensaje.build()); //ENVIA LA NOTIFICACIÓN CON ID:1
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PASOAPANTALLA_2:
                gestionaSubActivity(resultCode, data);
                break;
        }
    }

    public void gestionaSubActivity(int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String mensaje="";
            Bundle b = data.getExtras();
            int edad = b.getInt("Edad");
            if (edad < 18) mensaje = new String("¡Yogurin!");
            if ((edad >= 18) && (edad < 25)) mensaje = new String("En la flor de la vida");
            if (edad > 25) mensaje = new String("Ya van pesando las cosas de la edad");
            //AHORA PASAMOS POR PANTALLA LOS DATOS DE LA ACTIVITY 2

            datosRecibidos.setText(mensaje.toString());
            //desactivaComponentes();
        } else {
            Toast.makeText(getApplicationContext(), "Error en la SubActividad 1", Toast.LENGTH_LONG).show();
        }
    }

    public void desactivaComponentes(){
        textoNombre.setEnabled(false); //DESACTIVA EL CAMPO NOMBRE
        radioGroup.setEnabled(false); //LO MISMO CON EL RG PERO HAY QUE IR UNO A UNO
        //ME GUSTA USAR LA LETRA J PARA SEPARARLA DE LA I, LA CUAL USARAEMOS PARA EL INTENT
        for(int j=0;j<radioGroup.getChildCount();j++){
            radioGroup.getChildAt(j).setEnabled(false);
        }
        enviarInfo.setEnabled(false); //POR ULTIMO DESACTIVAMOS EL BOTON;
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
