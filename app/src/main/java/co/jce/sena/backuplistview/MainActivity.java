package co.jce.sena.backuplistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.jce.sena.database.DataBaseAdapter;

public class MainActivity extends AppCompatActivity {

    //-> Atributos (Especiales)
    private DataBaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-> Inicializamos la BD antes de crear el "Activity"
        dbAdapter = new DataBaseAdapter( this );    //: Conectamos la BD con la aplicación
        dbAdapter .open();                          //: Abrimos la BD y establecemos el modo de escritura.
    }
}
