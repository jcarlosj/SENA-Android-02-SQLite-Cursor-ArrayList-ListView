package co.jce.sena.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

/**
 * Created by jce on 20/10/15.
 */
public class ContactosDataBaseManager {

    //-> Atributos (Constantes)
    private final static String TB_NOMBRE = "contactos";     //: Nombre de la tabla

    //-> Atributos (Especiales)
    private SQLiteDatabase db;              //: Objeto que usamos para acceder a la BD.
    private ContentValues cvCamposTabla;    //: Contenedor de valores

    //-> Constructor
    public ContactosDataBaseManager( SQLiteDatabase db ) {
        this .db = db;                      //: Asigna la BD al Atributo de la clase.
    }

    //-> Clase embebida que implementa la interfaz "BaseColumns" de Android para mantener los campos
    //   disponibles para otras clases que la requieran.
    public class Columnas implements BaseColumns {

        public final static String ID        = "_id",
                                   IMAGEN    = "recurso",
                                   NOMBRES   = "nombre",
                                   APELLIDOS = "apellido",
                                   TELEFONO  = "telefono";
    }

    //-> Atributo (Constante).
    //   Campos de la tabla definidos como un "Array" de valores constantes a través de una interfaz
    //   para utilizarlos posteriormente en algunos métodos.
    private final static String[] COLUMNAS = {
        Columnas .ID,
        Columnas .IMAGEN,
        Columnas .NOMBRES,
        Columnas .APELLIDOS,
        Columnas .TELEFONO
    };

    //-> Atributo (Constante).
    //   Define la sentencia de creación de la estructura de tabla con SQLite.
    public final static String CREATE_TABLE = "create table if not exists " + TB_NOMBRE + "("
        + Columnas .ID + " integer primary key autoincrement, "
        + Columnas .IMAGEN + " varchar( 255 ) not null, "
        + Columnas .NOMBRES + " varchar( 30 ) not null, "
        + Columnas .APELLIDOS + " varchar( 30 ), "
        + Columnas .TELEFONO + " varchar( 25 )";

    //-> Contenedor de valores
    //   Algunos métodos de Android requieren que los datos sean pasados en objetos de este tipo.
    private ContentValues contenedorValores( String urlImagen, String nombres, String apellidos, String telefono ) {

        //-> Instancia de un contenedor de valores
        cvCamposTabla = new ContentValues();

        //-> Agrega cada uno de los valores al contenedor
        cvCamposTabla .put( Columnas .IMAGEN, urlImagen );
        cvCamposTabla .put( Columnas .NOMBRES, nombres );
        cvCamposTabla .put( Columnas .APELLIDOS, apellidos );
        cvCamposTabla .put( Columnas .TELEFONO, telefono );

        return cvCamposTabla;
    }

    //-> Crea un registro en la BD con los valores que recibe.
    public boolean insertar( String urlImagen, String nombres, String apellidos, String telefono ) {

        // Relacionamos campos con valores en el objeto ContentValues.
        ContentValues valores = contenedorValores( urlImagen, nombres, apellidos, telefono );

        // El método insert devuelve un long que corresponde al valor del _id
        // que se inserta, y -1 si falla la inserción. Si es superior a 0
        // implica que se inserta correctamente, por lo tanto lo que devolvemos
        // es si lo que devuelve el insert es superior o no a 0.
        return this .db .insert( TB_NOMBRE, null, valores ) > 0;
    }

    //-> Obtiene el listado de nombres en un Cursor
    public Cursor getNombres() {

        String[] campos = { Columnas .NOMBRES };
        return db .query( TB_NOMBRE, campos, null, null, null, null, null );
    }

    //-> Obtiene el nombre de la tabla
    public String getNombreTabla() {
        return TB_NOMBRE;
    }

    //-> Obtiene el "Array" con los campos de la tabla
    public String[] getCamposTabla() {
        return COLUMNAS;
    }

}
