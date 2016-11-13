package org.joelcano.projectes.cuandovoyamorir;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        intent = getIntent();

        String titol;
        int sexe = intent.getIntExtra("sexe", 0);
        if (sexe == 1)
            titol = "Don ";
        else
            titol = "Doña ";
        titol += intent.getStringExtra("nom");
        String paragraf = getResources().getString(R.string.para1);
        paragraf += " " + intent.getStringExtra("data");
        paragraf += "\n " + getResources().getString(R.string.para2);
        paragraf += intent.getStringExtra("lloc") +"\n" ;
        paragraf += intent.getStringExtra("mort");

        TextView tv = (TextView) findViewById(R.id.tvTitol);
        tv.setText(titol);
        TextView tv2 = (TextView) findViewById(R.id.tvParagraf);
        tv2.setText(paragraf);

    }

}
