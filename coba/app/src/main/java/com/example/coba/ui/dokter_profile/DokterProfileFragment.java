package com.example.coba.ui.dokter_profile;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coba.R;
import com.example.coba.ui.dokter_profile.dataObjectProfileDokter;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DokterProfileFragment extends Fragment {

    private DokterProfileViewModel mViewModel;
    DatePickerDialog dataPickerDialog;
    SimpleDateFormat dateFormat;
    Button buttonSimpanProfileDokter;
    EditText NAMA_PROFILE_DOKTER, EMAIL_PROFILE_DOKTER, HP_PROFILE_DOKTER, ALAMAT_PROFILE_DOKTER;
    private Spinner spinner;

    public static DokterProfileFragment newInstance() {
        return new DokterProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dokter_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        NAMA_PROFILE_DOKTER = (EditText) view.findViewById(R.id.NAMA_PROFILE_DOKTER);
        EMAIL_PROFILE_DOKTER = (EditText) view.findViewById(R.id.EMAIL_PROFILE_DOKTER);
        HP_PROFILE_DOKTER = (EditText) view.findViewById(R.id.HP_PROFILE_DOKTER);
        ALAMAT_PROFILE_DOKTER = (EditText) view.findViewById(R.id.ALAMAT_PROFILE_DOKTER);

        //Initializing Spinner
        spinner = (Spinner) view.findViewById(R.id.GENDER_PROFILE_DOKTER);

        String[] arraySpinner = new String[] {
                "Laki - laki", "Perempuan"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Adding an Item Selected Listener to our Spinner
        //As we have implemented the class Spinner.OnItemSelectedListener to this class iteself we are passing this to setOnItemSelectedListener

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos,long id)
            {
                //Called when item is selected, use position of item to find it from list of items
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0)
            {
                //Called when no item is selected
            }
        });

        buttonSimpanProfileDokter = (Button) view.findViewById(R.id.buttonSimpanProfileDokter);
        buttonSimpanProfileDokter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DokterProfileFragment();
            }
        });
    }

    void DokterProfileFragment(){
        String url="http://192.168.56.1/uasmobile/update_DOKTER.php";
        StringRequest respon=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("status");
                            if (status.equals("oke")){
                                AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                                builder.setTitle("Success");
                                builder.setMessage("Data Pribadi Anda Telah Diperbarui");
                                builder.setPositiveButton("Oke",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                AlertDialog dialog= builder.create();
                                dialog.show();
                            }else {
                                AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
                                builder.setTitle("Error");
                                builder.setMessage("Data gagal update");
                                builder.setPositiveButton("Oke",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                AlertDialog dialog= builder.create();
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> form=new HashMap<>();
                form.put("NAMA_DOKTER", NAMA_PROFILE_DOKTER.getText().toString());
                form.put("GENDER_DOKTER",spinner.getSelectedItem().toString());
                form.put("ALAMAT_DOKTER", ALAMAT_PROFILE_DOKTER.getText().toString());
                form.put("EMAIL_DOKTER", EMAIL_PROFILE_DOKTER.getText().toString());
                form.put("NO_TELP_DOKTER", HP_PROFILE_DOKTER.getText().toString());
                return form;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(respon);
    }

    class Adapter extends BaseAdapter {
        Context context;
        LayoutInflater inflater;
        ArrayList<dataObjectProfileDokter> model;
        public Adapter(Context context, ArrayList<dataObjectProfileDokter>model){
            inflater=LayoutInflater.from(context);
            this.context=context;
            this.model=model;
        }

        @Override
        public int getCount() {
            return model.size();
        }

        @Override
        public Object getItem(int position) {
            return model.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=inflater.inflate(R.layout.fragment_dokter_profile, parent, false);
            NAMA_PROFILE_DOKTER= view.findViewById(R.id.NAMA_PROFILE_DOKTER);
            EMAIL_PROFILE_DOKTER= view.findViewById(R.id.EMAIL_PROFILE_DOKTER);
            HP_PROFILE_DOKTER= view.findViewById(R.id.HP_PROFILE_DOKTER);
            ALAMAT_PROFILE_DOKTER= view.findViewById(R.id.ALAMAT_PROFILE_DOKTER);


            NAMA_PROFILE_DOKTER.setText(model.get(position).getNAMA_DOKTER());
            EMAIL_PROFILE_DOKTER.setText(model.get(position).getEMAIL_DOKTER());
            HP_PROFILE_DOKTER.setText(model.get(position).getNO_TELP_DOKTER());
            ALAMAT_PROFILE_DOKTER.setText(model.get(position).getALAMAT_DOKTER());
            return view;
        }
    }

}