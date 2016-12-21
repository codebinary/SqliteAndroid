package com.ontiveros.james.abogadosapp.lawyerdetail;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ontiveros.james.abogadosapp.R;
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

        return root;
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
}
