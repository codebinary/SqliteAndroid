package com.ontiveros.james.abogadosapp.addeditlawyer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ontiveros.james.abogadosapp.R;
import com.ontiveros.james.abogadosapp.lawyers.LawyerActivity;

public class AddEditLawyerActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_LAWYER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_lawyer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String lawyerId = getIntent().getStringExtra(LawyerActivity.EXTRA_LAWYER_ID);

        setTitle(lawyerId == null ? "Añadir abogado" : "Editar abogado");

        AddEditLawyerFragment addEditLawyerFragment = (AddEditLawyerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_add_edit_lawyer);

        if(addEditLawyerFragment == null){
            addEditLawyerFragment = AddEditLawyerFragment.newInstance(lawyerId);

        }





    }

}
