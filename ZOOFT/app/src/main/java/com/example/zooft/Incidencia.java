package com.example.zooft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zooft.Models.Animal;
import com.example.zooft.Models.Empleado;
import com.example.zooft.Models.NewIncidencia;
import com.example.zooft.Models.SubTipo;
import com.example.zooft.cuadroDialogo.Adaptador;
import com.example.zooft.cuadroDialogo.CuadroException;
import com.example.zooft.cuadroDialogo.CuadroIncidencia;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Incidencia extends AppCompatActivity {

    private RecyclerView recyclerViewTipos;
    private RecyclerView.Adapter adapter;

    private String visitNum;
    private Animal animal;
    private String errorExcepcion;

    private DatabaseReference ReferenceDB = FirebaseDatabase.getInstance().getReference();

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidencia);

        this.errorExcepcion = String.valueOf(R.string.mensajeExcepcion);

        this.recyclerViewTipos = (RecyclerView) findViewById(R.id.recyclerViewTipos);
        this.recyclerViewTipos.setHasFixedSize(true);
        this.recyclerViewTipos.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        this.animal = (Animal)intent.getSerializableExtra("animal");

        this.visitNum = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        leerServicio("https://zooft-10490-default-rtdb.firebaseio.com/subTipos.json");
    }

    /*******        API WEB     *******/
    public void leerServicio(String url){

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

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return recuperarContenido(strings[0]);
        }

        @Override
        protected void onPostExecute(String resultado) {
            try {
                JSONArray jsonArray = new JSONArray(resultado);
                List<SubTipo> listSubTipo = convertirJsonTipo(jsonArray);

                mostrarRecycler(listSubTipo);

            }catch (Exception e){
                CuadroException mensaje = new CuadroException(e.getMessage());
                mensaje.show(getFragmentManager(), errorExcepcion);
                System.out.println("ERROR - HttpAsyncTask -> "+e.getMessage());
            }
        }
    }

    private List<SubTipo> convertirJsonTipo(JSONArray jsonArray) throws JSONException {
        List<SubTipo> listSubTipo = new ArrayList<>();

        for (int i=0; i<jsonArray.length(); i++){
            SubTipo subTipo = new SubTipo();
            int id, id_departamento;
            String descripcion, nivel, departamento;

            id = jsonArray.getJSONObject(i).optInt("id");
            descripcion = jsonArray.getJSONObject(i).optString("descripcion");
            nivel = jsonArray.getJSONObject(i).optString("nivel");
            id_departamento = jsonArray.getJSONObject(i).optInt("id_departamento");
            departamento = jsonArray.getJSONObject(i).optString("departamento");

            subTipo.setId(id);
            subTipo.setDescripcion(descripcion);
            subTipo.setNivel(nivel);
            subTipo.setId_departamento(id_departamento);
            subTipo.setDepartamento(departamento);

            listSubTipo.add(subTipo);
        }

        return listSubTipo;
    }



    /*******        CREACIÓN DEL RECYCLERVIEW     *******/
    private void mostrarRecycler(List<SubTipo> listSubTipo) {
        try {
            this.adapter = new Adaptador(listSubTipo);
            this.recyclerViewTipos.setAdapter(this.adapter);

        }catch (Exception e){
            CuadroException mensaje = new CuadroException(e.getMessage());
            mensaje.show(getFragmentManager(), errorExcepcion);
            System.out.println("ERROR - mostrarRecycler -> "+e.getMessage());
        }
    }



    /*******        BOTONES     *******/
    public void IncidenciaCancelar(View view){
        finish();
    }



    /*******        CREACIÓN DE INCIDENCA       *******/
    public void nuevaIncidencia(SubTipo subTipoSelect, Empleado empleSelect){

        String fecha, estado = "Generado", observacion = "Ninguna", id;
        int id_estado = 1;

        fecha = new Date().toString();

        id = this.visitNum+"-"+fecha;

        NewIncidencia newIncidencia = new NewIncidencia();

        newIncidencia.setId(id);
        newIncidencia.setId_subTipo(subTipoSelect.getId());
        newIncidencia.setSub_tipo(subTipoSelect.getDescripcion());
        newIncidencia.setNivel(subTipoSelect.getNivel());
        newIncidencia.setId_departamento(subTipoSelect.getId_departamento());
        newIncidencia.setDepartamento(subTipoSelect.getDepartamento());
        newIncidencia.setId_animal(animal.getId());
        newIncidencia.setAnimal(animal.getNombre());
        newIncidencia.setId_zona(animal.getId_zona());
        newIncidencia.setId_estado(id_estado);
        newIncidencia.setEstado(estado);
        newIncidencia.setVisitNum(this.visitNum);
        newIncidencia.setId_empleado(empleSelect.getId());
        newIncidencia.setNomEmple(empleSelect.getNombre());
        newIncidencia.setApeEmple(empleSelect.getApellido());
        newIncidencia.setEmailEmple(empleSelect.getEmail());
        newIncidencia.setId_coordinador(empleSelect.getId_coordinador());
        newIncidencia.setNomCoordinador(empleSelect.getNomCoordinador());
        newIncidencia.setApeCoordinador(empleSelect.getApeCoordinador());
        newIncidencia.setEmailCorrdinador(empleSelect.getEmailCoordinador());
        newIncidencia.setObservacion(observacion);
        newIncidencia.setFecha_creacion(fecha);
        newIncidencia.setFecha_actualizacion(fecha);

        this.ReferenceDB.child("incidencias").child(id).setValue(newIncidencia);

        incidenciaGeneradaToast();

        finish();
    }



    /*******        LLAMADA A CUADRO DE ACEPTAR LA INCIDENCIA       *******/
    public void llamarCuadroIncidencia(SubTipo subTipo){
        CuadroIncidencia nuevaIncidencia = new CuadroIncidencia(subTipo);
        nuevaIncidencia.show(getFragmentManager(), String.valueOf(R.string.confirmacionIncidencia));
    }



    /*******        LLAMADA AL TOAST PARA INDICAR QUE LA INCIDENCIA SE HA GENERADO       *******/
    public void incidenciaGeneradaToast(){
        Toast cargando = new Toast(getApplicationContext());

        LayoutInflater inflater = getLayoutInflater();

        View layout = inflater.inflate(R.layout.incidencia_toast, (ViewGroup) findViewById(R.id.layOutIncidenciaGenerada));

        cargando.setDuration(Toast.LENGTH_LONG);
        cargando.setView(layout);
        cargando.show();
    }



    /*******        LLAMADA A CUADRO EXCEPCION EMPLEADOS       *******/
    public void excepcionEmpleado(String error){
        CuadroException mensaje = new CuadroException(error);
        mensaje.show(getFragmentManager(), errorExcepcion);
    }



    /*******        OBTIENE EMPLEADOS      *******/
    public void returnEmpleado(SubTipo subTipo){
        Empleado empleSelect = new Empleado();
        List<Empleado> listEmpleados = new ArrayList<>();

        this.ReferenceDB.child("empleados").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    for (DataSnapshot ds: snapshot.getChildren()) {

                        Empleado empleado = new Empleado();

                        int id, id_departamento, id_coordinador;
                        String dni, nombre, apellido, email, zona, departamento, nomCoordinador, apeCoordinador, emailCoordinador;

                        id = Integer.parseInt(ds.child("id").getValue().toString());
                        dni = ds.child("dni").getValue().toString();
                        nombre = ds.child("nombre").getValue().toString();
                        apellido = ds.child("apellido").getValue().toString();
                        email = ds.child("email").getValue().toString();
                        zona = ds.child("zona").getValue().toString();
                        id_departamento = Integer.parseInt(ds.child("id_departamento").getValue().toString());
                        departamento = ds.child("departamento").getValue().toString();
                        id_coordinador = Integer.parseInt(ds.child("id_coordinador").getValue().toString());
                        nomCoordinador = ds.child("nomCoordinador").getValue().toString();
                        apeCoordinador = ds.child("apeCoordinador").getValue().toString();
                        emailCoordinador = ds.child("emailCoordinador").getValue().toString();

                        empleado.setId(id);
                        empleado.setDni(dni);
                        empleado.setNombre(nombre);
                        empleado.setApellido(apellido);
                        empleado.setEmail(email);
                        empleado.setZona(zona);
                        empleado.setId_departamento(id_departamento);
                        empleado.setDepartamento(departamento);
                        empleado.setId_coordinador(id_coordinador);
                        empleado.setNomCoordinador(nomCoordinador);
                        empleado.setApeCoordinador(apeCoordinador);
                        empleado.setEmailCoordinador(emailCoordinador);

                        listEmpleados.add(empleado);
                    }

                    for (int i=0; i<listEmpleados.size(); i++){

                        if ((listEmpleados.get(i).getZona().equals(animal.getId_zona())) && (listEmpleados.get(i).getId_departamento() == subTipo.getId_departamento())){

                            empleSelect.setId(listEmpleados.get(i).getId());
                            empleSelect.setDni(listEmpleados.get(i).getDni());
                            empleSelect.setNombre(listEmpleados.get(i).getNombre());
                            empleSelect.setApellido(listEmpleados.get(i).getApellido());
                            empleSelect.setEmail(listEmpleados.get(i).getEmail());
                            empleSelect.setZona(listEmpleados.get(i).getZona());
                            empleSelect.setId_departamento(listEmpleados.get(i).getId_departamento());
                            empleSelect.setDepartamento(listEmpleados.get(i).getDepartamento());
                            empleSelect.setId_coordinador(listEmpleados.get(i).getId_coordinador());
                            empleSelect.setNomCoordinador(listEmpleados.get(i).getNomCoordinador());
                            empleSelect.setApeCoordinador(listEmpleados.get(i).getApeCoordinador());
                            empleSelect.setEmailCoordinador(listEmpleados.get(i).getEmailCoordinador());

                            i = listEmpleados.size();
                        }
                    }
                }

                nuevaIncidencia(subTipo, empleSelect);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}