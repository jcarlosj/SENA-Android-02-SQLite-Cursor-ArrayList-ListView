package co.jce.sena.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jce on 20/10/15.
 */
public class DataBaseAdapter {

    //-> Atributos (Comunes)
    final static int BD_VERSION = 1;
    final static String BD_NOMBRE = "Agenda";

    //-> Atributos (Especiales)
    private DataBaseOpenHelper dbHelper;
    private SQLiteDatabase db;

    //-> Constructor
    public DataBaseAdapter( Context context ) {
        this .dbHelper = new DataBaseOpenHelper( context );     //: Crea la BD.
    }

    //-> Métodos
    public void open() {
        this .db = dbHelper .getWritableDatabase();             //: Devuelve la BD establecida en modo escritura.
    }

    public void close() {
        this .db .close();                                      //: Cierra la BD
    }

    //-> Clase Embebida manejadora de la
    private class DataBaseOpenHelper extends SQLiteOpenHelper {

        public DataBaseOpenHelper( Context context ) {
            super( context, BD_NOMBRE, null, BD_VERSION );      //: Pasa los argumentos a la clase padre
                                                                //: "SLQiteOpenHelper" para realizar la
                                                                //: conexión.
            }

        @Override
        public void onCreate( SQLiteDatabase db ) {

        }

        @Override
        public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {

        }
    }

}
