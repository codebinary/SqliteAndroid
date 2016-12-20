package com.ontiveros.james.abogadosapp.lawyers;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.ontiveros.james.abogadosapp.R;

/**
 * Created by james on 20/12/16.
 */

public class LawyersCursorAdapter extends CursorAdapter {

    public LawyersCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    //Implementamos newView para acceder a la instancia del LayoutInflater a través de context
    //y luego invoca inflate() para inflar el layout del item
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(.from(context);
        return inflater.inflate(R.layout.list_item_lawyer, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

    }
}
