package com.example.jonathan.formulario_sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombre,apellido,edad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = (EditText) findViewById(R.id.txtnombre);
        apellido = (EditText) findViewById(R.id.txtapellidos);
        edad = (EditText) findViewById(R.id.txtedad);
    }


    public void guardar_datos( View view){
        String nom = nombre.getText().toString();
        String ape = apellido.getText().toString();
        Integer ed = Integer.parseInt(edad.getText().toString());
        BaseHelper baseHelper = new BaseHelper(this,"Persona",null,1);
        SQLiteDatabase db = baseHelper.getWritableDatabase();
       if(nom!=""&&ape!=""&&ed>0&&ed<150){
           if(db!=null){
               ContentValues registro_nuevo = new ContentValues();
               registro_nuevo.put("Nombre",nom);
               registro_nuevo.put("Apellido",ape);
               registro_nuevo.put("Edad",ed);
               long i = db.insert("Persona",null,registro_nuevo);
               if(i>0){
                 Toast.makeText(this,"Registro ingresado",Toast.LENGTH_SHORT).show();
                 db.close();
               }else{
               Toast.makeText(this,"No se Registro",Toast.LENGTH_SHORT).show();
               }
           }
       }else{
           Toast.makeText(this,"Lo campos estan vacios",Toast.LENGTH_SHORT).show();
       }
    }
}
