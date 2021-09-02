package com.example.zooft.Animales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.zooft.Incidencia;
import com.example.zooft.Models.Animal;
import com.example.zooft.R;

public class Bisonte extends AppCompatActivity {

    private ImageButton btnHome;
    private ImageButton btnIncidencia;
    private Animal animal = new Animal();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bisonte);

        this.btnHome = (ImageButton) findViewById(R.id.btnHomeMapa);
        this.btnIncidencia = (ImageButton) findViewById(R.id.btnIncidencia);

        Intent intent = getIntent();
        animal = (Animal)intent.getSerializableExtra("animal");

    }

    /*******        BOTONES     *******/

    public void atras(View view){
        finish();
    }

    public void enviarIncidencia(View view){

        Intent i = new Intent(this, Incidencia.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("animal", animal);
        i.putExtras(bundle);

        startActivity(i);

    }
}