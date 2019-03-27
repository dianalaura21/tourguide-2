package com.example.julioparedes.mapa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView Titulo;
    TextView Descripcion;
    ImageView imagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Titulo = findViewById(R.id.textView2);
        Descripcion = findViewById(R.id.textView);
        imagen = findViewById(R.id.imageView);

        Intent intent =getIntent();
        int lugar = intent.getIntExtra("lugar",0);

        if(lugar==0){
            Titulo.setText("The Palms Resort Hotel");
            Descripcion.setText("Donde nos encontramos actualmente");
            //imagen.setImageResource(R.drawable.ic_launcher_background);

        }
        //if(lugar==2){
            //Titulo.setText("LUGAR2");
           // Descripcion.setText("okis");
            //imagen.setImageResource(R.drawable.ic_launcher_background);

        //}
        //if(lugar==3){
          //  Titulo.setText("LUGAR3");

            //Descripcion.setText("okis");
            //imagen.setImageResource(R.drawable.ic_launcher_background);

        //}
        //if(lugar==4){
          //  Titulo.setText("LUGAR4");
          //  Descripcion.setText("okis");
          //  imagen.setImageResource(R.drawable.ic_launcher_background);

        //}
        //if(lugar==5){
          //  Titulo.setText("lUGAR5");
          //  Descripcion.setText("okis");
            // imagen.setImageResource(R.drawable.ic_launcher_background);

       //
    }
}
