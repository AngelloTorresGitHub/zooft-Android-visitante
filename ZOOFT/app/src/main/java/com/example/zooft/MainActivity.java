package com.example.zooft;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.zooft.Animales.*;
import com.example.zooft.Models.Animal;
import com.example.zooft.cuadroDialogo.*;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private String errorExcepcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.errorExcepcion = String.valueOf(R.string.mensajeExcepcion);
        ImageButton btnEscaner = (ImageButton) findViewById(R.id.btnEscaner);
        ImageButton btnLocalizar = (ImageButton) findViewById(R.id.btnLocalizar);
        ImageButton btnSalir = (ImageButton) findViewById(R.id.btnSalir);

        checkPermission();
    }



    /*******        BOTONES     *******/
    public void salir(View view){
        ConfirmacionSalirAPP confirmar = new ConfirmacionSalirAPP();
        confirmar.show(getFragmentManager(), "Salir");
    }

    public void aceptar(){
        super.onBackPressed();
        finishAffinity();
        System.exit(0);
    }

    public void scanner(View view){

        new IntentIntegrator(this).initiateScan();
    }

    public void mapa(View view){
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }



    /*******        SCANNER     *******/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        String url = result.getContents();

        System.out.println(url);

        leerServicio(url);

    }

    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)){

            }else {
                ActivityCompat.requestPermissions(this,
                        new String[]{
                                Manifest.permission.CAMERA
                        }, 1);
            }
        }
    }



    /*******        APIWEB     *******/
    public void leerServicio(String url){

        System.out.println("APIWEB -> "+url);
        try {
            new HttpAsyncTask().execute(url);
        }catch (Exception e){
            CuadroException mensaje = new CuadroException(e.getMessage());
            mensaje.show(getFragmentManager(), errorExcepcion);
            System.out.println("ERROR - leerServivio -> "+e.getMessage());
        }
    }

    private String recuperarContenido(String url) {
        HttpClient httpClient = new DefaultHttpClient();
        String resultado = null;
        HttpGet httpGet = new HttpGet(url);
        HttpResponse respuesta = null;
        InputStream stream = null;

        try{
            respuesta = httpClient.execute(httpGet);
            HttpEntity entity = respuesta.getEntity();

            if(entity != null){
                stream = entity.getContent();
                resultado = convertirInputToStream(stream);
            }

        }catch(Exception e){
            CuadroException mensaje = new CuadroException(e.getMessage());
            mensaje.show(getFragmentManager(), errorExcepcion);
            System.out.println("ERROR - recuperarContenido -> "+e.getMessage());
        }finally {
            try{
                if (stream != null){
                    stream.close();
                }
            }catch(Exception e){
                CuadroException mensaje = new CuadroException(e.getMessage());
                mensaje.show(getFragmentManager(), errorExcepcion);
                System.out.println("ERROR - recuperarContenido finally -> "+e.getMessage());
            }
        }
        return resultado;
    }

    private String convertirInputToStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String resultado = "";

        while ((line = bufferedReader.readLine())!=null){
            resultado += line;
        }

        inputStream.close();

        return resultado;
    }

    @SuppressLint("StaticFieldLeak")
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return recuperarContenido(strings[0]);
        }

        @Override
        protected void onPostExecute(String resultado) {
            try {
                JSONObject jsonObject = new JSONObject(resultado);
                Animal animal = convertirJsonAnimal(jsonObject);

                selectAnimal(animal);

            }catch (Exception e){
                CuadroException mensaje = new CuadroException(e.getMessage());
                mensaje.show(getFragmentManager(), errorExcepcion);
                System.out.println("ERROR - HttpAsyncTask -> "+e.getMessage());
            }
        }
    }

    private Animal convertirJsonAnimal(JSONObject jsonObject) throws JSONException {
        Animal animal = new Animal();

        int id;
        String id_zona, nombre;

        id = jsonObject.optInt("id");
        id_zona = jsonObject.optString("id_zona");
        nombre = jsonObject.optString("nombre");

        animal.setId(id);
        animal.setId_zona(id_zona);
        animal.setNombre(nombre);

        return animal;
    }



    /*******        SELECCIONA VENTANA     *******/
    public void selectAnimal(Animal animal) {

        Intent i = new Intent();

        System.out.println("selectAnimal -> "+animal.getId());

        switch (animal.getId()){
            case 0:
                i = new Intent(this, Lobo.class);
                break;
            case 1:
                i = new Intent(this, Lince.class);
                break;
            case 2:
                i = new Intent(this, Panda.class);
                break;
            case 3:
                i = new Intent(this, Bisonte.class);
                break;
            case 4:
                i = new Intent(this, Hormiguero.class);
                break;
            case 5:
                i = new Intent(this, Tapir.class);
                break;
            case 6:
                i = new Intent(this, Tigre.class);
                break;
            case 7:
                i = new Intent(this, Pinguino.class);
                break;
            case 8:
                i = new Intent(this, Tiburon.class);
                break;
            case 9:
                i = new Intent(this, Ibis.class);
                break;
            case 10:
                i = new Intent(this, Loris.class);
                break;
            case 11:
                i = new Intent(this, Guacamayo.class);
                break;
            case 12:
                i = new Intent(this, Nutria.class);
                break;
            case 13:
                i = new Intent(this, Rinoceronte.class);
                break;
            default:
                CuadroException mensaje = new CuadroException(String.valueOf(R.string.animalNoEncontrado));
                mensaje.show(getFragmentManager(), errorExcepcion);
                System.out.println("Animal no encontrado");
                break;
        }

        Cargando();

        Bundle bundle = new Bundle();
        bundle.putSerializable("animal", animal);
        i.putExtras(bundle);

        startActivity(i);

    }



    /*******        BARRA DE PROGRESO     *******/
    public void Cargando(){
        Toast cargando = new Toast(getApplicationContext());

        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(R.layout.cargando_toast, (ViewGroup) findViewById(R.id.layOutCargando));

        cargando.setDuration(Toast.LENGTH_LONG);
        cargando.setView(layout);
        cargando.show();
    }

}