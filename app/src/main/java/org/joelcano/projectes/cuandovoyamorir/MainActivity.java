package org.joelcano.projectes.cuandovoyamorir;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String mort = "Condemnat a pena de mort per robar al mercadona";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnStart = (Button) findViewById(R.id.btCalcular);
        btnStart.setOnClickListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.llenguatges, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        EditText etNom = (EditText) findViewById(R.id.etNom);
        String nom = etNom.getText().toString();
        EditText etDia = (EditText) findViewById(R.id.etDia);
        String dia = etDia.getText().toString();
        EditText etMes = (EditText) findViewById(R.id.etMes);
        String mes = etMes.getText().toString();
        EditText etAny = (EditText) findViewById(R.id.etAny);
        String any = etAny.getText().toString();
        RadioGroup rbSexe = (RadioGroup)findViewById(R.id.rbSexe);
        int sexe = rbSexe.getCheckedRadioButtonId();
        EditText etLloc = (EditText) findViewById(R.id.etLloc);
        String lloc = etLloc.getText().toString();
        Checkable cbAlcohol = (Checkable) findViewById(R.id.cbAlcohol);
        boolean alcohol = cbAlcohol.isChecked();
        Checkable cbTabac = (Checkable) findViewById(R.id.cbTabac);
        boolean tabac = cbTabac.isChecked();
        Checkable cbVideojocs = (Checkable) findViewById(R.id.cbVideojocs);
        boolean videojocs = cbVideojocs.isChecked();

        Calendar dataActual = Calendar.getInstance();
        dataActual.add(Calendar.YEAR, 1);

        //Calculem l'edat actual
        int edat = dataActual.get(Calendar.YEAR) - Integer.valueOf(any);
        int vidaRestant=0;
        //calculem el temps de vida restant en funcio del sexe
        if (sexe == 1)
            vidaRestant = 80 - edat;
        else if (sexe == 2)
            vidaRestant = 90 - edat;

        //restem anys de vida per a cada vici
        if (alcohol)
            vidaRestant -= 10;
        if (tabac)
            vidaRestant -= 10;
        if (videojocs)
            vidaRestant -= 10;

        Random random = new Random();

        Auxiliar usdbh =new Auxiliar(this, "DBMuertes", null, 1);

        SQLiteDatabase db = usdbh.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT mort FROM morts WHERE codi ="+random.nextInt(3)+" ", null);
        Log.i("asdf", "SELECT mort FROM morts WHERE codi ="+random.nextInt(3) );
        if (c.moveToFirst()) {
            do {
                mort = c.getString(0);
            } while(c.moveToNext());
        }
        Calendar dataMort = Calendar.getInstance();
        dataMort.add(Calendar.YEAR, vidaRestant);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("data", String.valueOf(dataMort.get(Calendar.YEAR)));
        intent.putExtra("mort", mort);
        intent.putExtra("lloc", lloc);
        intent.putExtra("nom", nom);
        intent.putExtra("sexe", sexe);
        startActivity(intent);
    }
}
