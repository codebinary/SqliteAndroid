package com.ontiveros.james.abogadosapp.data;

import android.provider.BaseColumns;

/**
 * Created by James on 19/12/2016.
 */

//Esquema de la base de datos para abogados
public class LawyersContract {

    //Claee que guarda el nombe de las columnas de la tabla
    //Se implementó la interfaz BaseColumns con el fin de agregar una columna extra que se
    //recomienda tenga toda tabla
    public static abstract class LawyerEntry implements BaseColumns{
        public static final String TABLE_NAME = "lawyer";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String SPECIALTY = "specialty";
        public static final String PHONE_NUMBER = "phoneNumber";
        public static final String AVATAR_URI = "avatarUri";
        public static final String BIO = "bio";
    }
}
