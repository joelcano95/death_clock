package org.joelcano.projectes.cuandovoyamorir;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Auxiliar extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE morts (codi INTEGER PRIMARY KEY AUTOINCREMENT, mort TEXT)";

    public Auxiliar(Context contexto, String nombre, CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
        insert(db);
    }

    public void insert(SQLiteDatabase db){
        db.execSQL("INSERT INTO morts (mort) VALUES ('Atropellat per un Fiat Multipla mentres consultaba el Whats App')");
        db.execSQL("INSERT INTO morts (mort) VALUES ('Assessinat per un camell al que li debies diners.')");
        db.execSQL("INSERT INTO morts (mort) VALUES ('Aplastat per una maquina expenedora de preservatius d una discoteca')");
        db.execSQL("INSERT INTO morts (mort) VALUES ('Mort d aburriment intentant trobar un error en el codi mentres programaba')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS morts");

        db.execSQL(sqlCreate);
    }
}