package com.ontiveros.james.abogadosapp.lawyerdetail;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ontiveros.james.abogadosapp.R;
import com.ontiveros.james.abogadosapp.data.Lawyer;
import com.ontiveros.james.abogadosapp.data.LawyersDbHelper;

/*
* Vista para el detalle del abogado
* */
public class LawyerDetailFragment extends Fragment {
    private static final String ARG_LAWYER_ID = "arg_lawyer_id";

    private String mLawyerId;

    private CollapsingToolbarLayout mCollapsingView;
    private ImageView mAvatar;
    private TextView mPhoneNumber;
    private TextView mSpecialty;
    private TextView mBio;

    private LawyersDbHelper mLawyersDbHelper;

    public LawyerDetailFragment() {
        // Required empty public constructor
    }

    public static LawyerDetailFragment newInstance(String lawyerId) {
        LawyerDetailFragment fragment = new LawyerDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LAWYER_ID, lawyerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mLawyerId = getArguments().getString(ARG_LAWYER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lawyer_detail, container, false);
        mCollapsingView = (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);
        mAvatar = (ImageView) getActivity().findViewById(R.id.iv_avatar);
        mPhoneNumber = (TextView) getActivity().findViewById(R.id.tv_phone_number);
        mSpecialty = (TextView) getActivity().findViewById(R.id.tv_specialty);
        mBio = (TextView) getActivity().findViewById(R.id.tv_bio);

        mLawyersDbHelper = new LawyersDbHelper(getActivity());

        //Cargamos el detalle de abogado con el método getLawyerById() con una tarea asíncrona.
        //Llámala en onCreateView a través de un método loadLawyer();
        loadLawyer();

        return root;
    }

    private void loadLawyer() {
        new GetLawyerByIdTask().execute();
    }

    /*
    * Se requiere onActivityResult() ya que cuando se intente eliminar o editar
    * el abogado se estará pendiente de cambios en la base de datos, así que podremos
    * reportar a LawyersFragment la necesidad de actualizar la lista.
    * */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Acciones
    }

    private void showLawyer(Lawyer lawyer){
        mCollapsingView.setTitle(lawyer.getName());
        Glide.with(this)
                .load(Uri.parse("file:///android_asset/" + lawyer.getAvatarUri()))
                .centerCrop()
                .into(mAvatar);
        mPhoneNumber.setText(lawyer.getPhoneNumber());
        mSpecialty.setText(lawyer.getSpecialty());
        mBio.setText(lawyer.getBio());
    }

    public void showLoadError(){
        Toast.makeText(getActivity(), "Error al cargar la imagen", Toast.LENGTH_SHORT).show();
    }


    private class GetLawyerByIdTask extends AsyncTask<Void, Void, Cursor>{
        @Override
        protected Cursor doInBackground(Void... voids) {
            return mLawyersDbHelper.getLawyerById(mLawyerId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            if(cursor != null && cursor.moveToLast()){
                showLawyer(new Lawyer(cursor));
            }else{
                showLoadError();
            }
        }
    }
}
