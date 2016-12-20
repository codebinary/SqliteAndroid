package com.ontiveros.james.abogadosapp.lawyers;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.ontiveros.james.abogadosapp.R;
import com.ontiveros.james.abogadosapp.data.LawyersContract;

import org.w3c.dom.Text;

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
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_lawyer, viewGroup, false);
    }

    //Implementamos para obtener el valor de la columna name y avatarUri. Luego Seteamos en los views del layout
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        //Referencias UI.
        TextView nameText = (TextView) view.findViewById(R.id.tv_name);
        final ImageView avatarImage = (ImageView) view.findViewById(R.id.iv_avatar);

        //Get valores
        String name = cursor.getString(cursor.getColumnIndex(LawyersContract.LawyerEntry.NAME));
        String avatarUri = cursor.getString(cursor.getColumnIndex(LawyersContract.LawyerEntry.AVATAR_URI));

        //Setup
        nameText.setText(name);
        Glide.with(context)
                .load(Uri.parse("file:///android_asset/" + avatarUri))
                .asBitmap()
                .error(R.drawable.ic_account_circle)
                .centerCrop()
                .into(new BitmapImageViewTarget(avatarImage){
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        drawable.setCircular(true);
                        avatarImage.setImageDrawable(drawable);
                    }
                });

    }
}
