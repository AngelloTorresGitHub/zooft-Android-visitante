package com.example.zooft.ApiWeb;

public class ObtenerEmpleados {

   /* private DatabaseReference ReferenceDB = FirebaseDatabase.getInstance().getReference();

    public Empleados returnEmpleado(String zona, int id_departamento){
        Empleados empleado = new Empleados();
        Empleados empleSelect = new Empleados();
        List<Empleados> listEmpleados = new ArrayList<>();

        this.ReferenceDB.child("empleados").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    for (DataSnapshot ds: snapshot.getChildren()) {
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
                }

                //Empleados empleSelect = new Empleados();

                for (int i=0; i<listEmpleados.size(); i++){

                    if ((listEmpleados.get(i).getZona().equals(zona)) && (listEmpleados.get(i).getId_departamento() == id_departamento)){
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return empleSelect;
    }
*/
   /* public Empleados seleccionarEmpleado(String zona, int id_departamento) {
        Empleados emplaSelect = new Empleados();

        for (int i=0; i<listEmpleados.size(); i++){

            if ((listEmpleados.get(i).getZona().equals(zona)) && (listEmpleados.get(i).getId_departamento() == id_departamento)){
                emplaSelect.setId(listEmpleados.get(i).getId());
                emplaSelect.setDni(listEmpleados.get(i).getDni());
                emplaSelect.setNombre(listEmpleados.get(i).getNombre());
                emplaSelect.setApellido(listEmpleados.get(i).getApellido());
                emplaSelect.setEmail(listEmpleados.get(i).getEmail());
                emplaSelect.setZona(listEmpleados.get(i).getZona());
                emplaSelect.setId_departamento(listEmpleados.get(i).getId_departamento());
                emplaSelect.setDepartamento(listEmpleados.get(i).getDepartamento());
                emplaSelect.setId_coordinador(listEmpleados.get(i).getId_coordinador());
                emplaSelect.setNomCoordinador(listEmpleados.get(i).getNomCoordinador());
                emplaSelect.setApeCoordinador(listEmpleados.get(i).getApeCoordinador());
                emplaSelect.setEmailCoordinador(listEmpleados.get(i).getEmailCoordinador());

                i = listEmpleados.size();
            }
        }

        return emplaSelect;
    }*/

    /*private Incidencia incidencia = new Incidencia();
    private List<Empleados> listEmpleadosEnviar = new ArrayList<>();

    public void leerServicio(String url){

        try {
            new HttpAsyncTask().execute(url);
        }catch (Exception e){
            incidencia.excepcionEmpleado(e.getMessage());
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
            incidencia.excepcionEmpleado(e.getMessage());
            System.out.println("ERROR - recuperarContenido -> "+e.getMessage());
        }finally {
            try{
                if (stream != null){
                    stream.close();
                }
            }catch(Exception e){
                incidencia.excepcionEmpleado(e.getMessage());
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
                JSONArray jsonArray = new JSONArray(resultado);
                listEmpleadosEnviar = convertirJsonEmpleado(jsonArray);


            }catch (Exception e){
                incidencia.excepcionEmpleado(e.getMessage());
                System.out.println("ERROR - HttpAsyncTask -> "+e.getMessage());
            }
        }
    }

    private List<Empleados> convertirJsonEmpleado(JSONArray jsonArray) throws JSONException {

        List<Empleados> listEmpleados = new ArrayList<>();

        for (int i=0; i<jsonArray.length(); i++){
            Empleados empleado = new Empleados();

            int id, id_departamento, id_coordinador;
            String dni, nombre, apellido, email, zona, departamento, nomCoordinador, apeCoordinador, emailCoordinador;

            id = jsonArray.getJSONObject(i).optInt("id");
            dni = jsonArray.getJSONObject(i).optString("dni");
            nombre = jsonArray.getJSONObject(i).optString("nombre");
            apellido = jsonArray.getJSONObject(i).optString("apellido");
            email = jsonArray.getJSONObject(i).optString("email");
            zona = jsonArray.getJSONObject(i).optString("zona");
            id_departamento = jsonArray.getJSONObject(i).optInt("id_departamento");
            departamento = jsonArray.getJSONObject(i).optString("departamento");
            id_coordinador = jsonArray.getJSONObject(i).optInt("id_coordinador");
            nomCoordinador = jsonArray.getJSONObject(i).optString("nomCoordinador");
            apeCoordinador = jsonArray.getJSONObject(i).optString("apeCoordinador");
            emailCoordinador = jsonArray.getJSONObject(i).optString("emailCoordinador");

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

        return listEmpleados;
    }

    public List<Empleados> empleadosEnviar(){
        return this.listEmpleadosEnviar;
    }*/

}
