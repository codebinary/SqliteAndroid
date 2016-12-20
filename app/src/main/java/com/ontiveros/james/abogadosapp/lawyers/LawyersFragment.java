package com.ontiveros.james.abogadosapp.lawyers;


import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ontiveros.james.abogadosapp.R;
import com.ontiveros.james.abogadosapp.data.LawyersDbHelper;

/**
*Vista para la lista de abogados
 */
public class LawyersFragment extends Fragment {

    private LawyersDbHelper mLawyersDbHelper;
    private ListView mLawyerListView;
    private LawyersCursorAdapter mLawyersAdapter;
    private FloatingActionButton mAddButton;


    public LawyersFragment() {
        // Required empty public constructor
    }

    public static LawyersFragment newInstance() {
        return new LawyersFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lawyers, container, false);

        //Referencias UI
        mLawyerListView = (ListView) root.findViewById(R.id.lawyers_list);
        mLawyersAdapter = new LawyersCursorAdapter(getActivity(), null);
        mAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        //Setup
        mLawyerListView.setAdapter(mLawyersAdapter);

        //Instancia de helper
        mLawyersDbHelper = new LawyersDbHelper(getActivity());

        //Carga de datos
        loadLawyers();

        return root;
    }

    private void loadLawyers() {
        //Cargar datos
        new LawyerLoadTask().execute();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //Crea una tarea asíncrona dentro del fragmento,
    // la cuál reciba como resultado un Cursor. Esto con el fin de no entorpecer
    // el hilo principal con el acceso a la base de datos.
    private class LawyerLoadTask extends AsyncTask<Void, Void, Cursor>{

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mLawyersDbHelper.getAllLawyers();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            if (cursor != null && cursor.getCount() > 0){
                mLawyersAdapter.swapCursor(cursor);
            }else{

            }
        }
    }
}
