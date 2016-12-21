package com.ontiveros.james.abogadosapp.addeditlawyer;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ontiveros.james.abogadosapp.R;
import com.ontiveros.james.abogadosapp.data.LawyersDbHelper;

public class AddEditLawyerFragment extends Fragment {

    private static final String ARG_LAWYER_ID = "arg_lawyer_id";

    private String mLawyerId;
    private LawyersDbHelper mLawyersDbHelper;

    private FloatingActionButton mSaveButton;
    private TextInputEditText mNameField;
    private TextInputEditText mPhoneNumberField;
    private TextInputEditText mSpecialtyField;
    private TextInputEditText mBioField;
    private TextInputLayout mNameLabel;
    private TextInputLayout mPhoneNumberLabel;
    private TextInputLayout mSpecialtyLabel;
    private TextInputLayout mBioLabel;

    public AddEditLawyerFragment(){

    }

    public static AddEditLawyerFragment newInstance(String lawyerId) {
        AddEditLawyerFragment fragment = new AddEditLawyerFragment();
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

        View root = inflater.inflate(R.layout.fragment_add_edit_lawyer, container, false);

        //Referencias UI
        mSaveButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mNameField = root.findViewById(R.id.)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_edit_lawyer, container, false);
    }

}
