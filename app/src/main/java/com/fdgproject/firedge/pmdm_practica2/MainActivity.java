package com.fdgproject.firedge.pmdm_practica2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class MainActivity extends Activity {

    private String enlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Uri data = intent.getData();
        enlace = data.getPath();
        if(intent.getType().equals("text/plain")){
            File f=new File(enlace);
            try {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String linea;
                StringBuilder texto = new StringBuilder("");
                while ((linea = br.readLine()) != null) {
                    texto.append(linea);
                    texto.append('\n');
                }
                br.close();
                EditText tv = (EditText) findViewById(R.id.et_texto);
                tv.setText(texto.toString());
            }catch(IOException e){
                Log.v("ARCHIVO", "excepcion: " + e.toString());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void btGuardar(View v){
        File f=new File(enlace);
        EditText et = (EditText) findViewById(R.id.et_texto);
        try {
            FileWriter fw = new FileWriter(f, false);
            fw.write(et.getText().toString());
            fw.flush();
            fw.close();
        } catch(IOException e){
            Log.v("ARCHIVO", "excepcion: " + e.toString());
        }
        setResult(0);
        finish();
    }
}
