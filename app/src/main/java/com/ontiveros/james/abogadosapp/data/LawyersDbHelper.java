package com.ontiveros.james.abogadosapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by James on 19/12/2016.
 */

/**
 * La clase que nos permitirá comunicar nuestra aplicación con la base de datos se llama
 * SQLiteOpenHelper. Se trata de una clase abstracta que nos provee los mecanismos básicos
 * para la relación entre la aplicación Android y la información.
 * */
public class LawyersDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Lawyers.db";

    public LawyersDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Aquí recien creamos la base de datos, apoyandonos con los daos creados en la clase LawyerEntry
        sqLiteDatabase.execSQL("CREATE TABLE " + LawyersContract.LawyerEntry.TABLE_NAME +
                " (" + LawyersContract.LawyerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                     + LawyersContract.LawyerEntry.ID + " TEXT NOT NULL,"
                     + LawyersContract.LawyerEntry.NAME + " TEXT NOT NULL,"
                     + LawyersContract.LawyerEntry.SPECIALTY + " TEXT NOT NULL,"
                     + LawyersContract.LawyerEntry.PHONE_NUMBER + " TEXT NOT NULL,"
                     + LawyersContract.LawyerEntry.BIO + " TEXT NOT NULL,"
                     + LawyersContract.LawyerEntry.AVATAR_URI + " TEXT,"
                     + "UNIQUE (" + LawyersContract.LawyerEntry.ID + "))");

        //Contenedor de valores
        //ContentValues values = new ContentValues();

        //Pares clave-valor
        /*values.put(LawyersContract.LawyerEntry.ID, "L-001");
        values.put(LawyersContract.LawyerEntry.NAME, "James Ontiveros");
        values.put(LawyersContract.LawyerEntry.SPECIALTY, "Ingeniero de sistemas");
        values.put(LawyersContract.LawyerEntry.PHONE_NUMBER, "300 200 1111");
        values.put(LawyersContract.LawyerEntry.BIO, "James es un progesional con 5 años de trayectoria ...");
        values.put(LawyersContract.LawyerEntry.AVATAR_URI, "james.jpg");*/

        //Insertar datos ficticios para prueba incial
        mockData(sqLiteDatabase);

    }
    //Agregamos los datos a la db
    private void mockData(SQLiteDatabase sqLiteDatabase){
        mockLawyer(sqLiteDatabase, new Lawyer("Carlos Perez", "Abogado penalista",
                "300 200 1111", "Gran profesional con experiencia de 5 años en casos penales.",
                "carlos_perez.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Daniel Samper", "Abogado accidentes de tráfico",
                "300 200 2222", "Gran profesional con experiencia de 5 años en accidentes de tráfico.",
                "daniel_samper.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Lucia Aristizabal", "Abogado de derechos laborales",
                "300 200 3333", "Gran profesional con más de 3 años de experiencia en defensa de los trabajadores.",
                "lucia_aristizabal.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Marina Acosta", "Abogado de familia",
                "300 200 4444", "Gran profesional con experiencia de 5 años en casos de familia.",
                "marina_acosta.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Olga Ortiz", "Abogado de administración pública",
                "300 200 5555", "Gran profesional con experiencia de 5 años en casos en expedientes de urbanismo.",
                "olga_ortiz.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Pamela Briger", "Abogado fiscalista",
                "300 200 6666", "Gran profesional con experiencia de 5 años en casos de derecho financiero",
                "pamela_briger.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Rodrigo Benavidez", "Abogado Mercantilista",
                "300 200 1111", "Gran profesional con experiencia de 5 años en redacción de contratos mercantiles",
                "rodrigo_benavidez.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Tom Bonz", "Abogado penalista",
                "300 200 1111", "Gran profesional con experiencia de 5 años en casos penales.",
                "tom_bonz.jpg"));
    }

    //Creamos este metodo para simplicar el guardado la cual recibe una instancia de Lawyer,
    // se convertirá y luego se inserta
    public long saveLawyer(Lawyer lawyer){
        //con getWritetableDatabase obtener el manejador de la base de datos para operaciones de escritura
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                LawyersContract.LawyerEntry.TABLE_NAME,
                null,
                lawyer.toContentValues());
    }

    //Método que realiza la insersión a la bd, recibiendo dos parámetros
    //la entidad Lawyer y SQLiteDatabase
    public long mockLawyer(SQLiteDatabase db, Lawyer lawyer){
        return db.insert(
                LawyersContract.LawyerEntry.TABLE_NAME,
                null,
                lawyer.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    //Obtenemos todos los abogados
    public Cursor getAllLawyers(){
        return getReadableDatabase().query(
                LawyersContract.LawyerEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    //Obtenemos por ID
    /*Cursor c = db.query(
            LawyerEntry.TABLE_NAME,  // Nombre de la tabla
            null,  // Lista de Columnas a consultar
            null,  // Columnas para la cláusula WHERE
            null,  // Valores a comparar con las columnas del WHERE
            null,  // Agrupar con GROUP BY
            null,  // Condición HAVING para GROUP BY
            null  // Cláusula ORDER BY
    );*/
    public Cursor getLawyerById(String lawyerId){
        Cursor cursor = getReadableDatabase().query(
                LawyersContract.LawyerEntry.TABLE_NAME,
                null,
                LawyersContract.LawyerEntry.ID + " LIKE ?",
                new String[]{lawyerId},
                null,
                null,
                null);
        return cursor;
    }
}
