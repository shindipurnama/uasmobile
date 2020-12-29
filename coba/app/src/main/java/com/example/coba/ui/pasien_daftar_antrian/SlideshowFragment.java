package com.example.coba.ui.pasien_daftar_antrian;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coba.R;
import com.example.coba.register;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    TextView TANGGAL_PERIKSA;
    Button bt_TANGGAL_PERIKSA;
    Button buttonDaftarAntrian;
    //Declaring an Spinner
    private Spinner spinner;
    private Spinner spinner2;

    //An ArrayList for Spinner Items
    private ArrayList<String> poli;

    //JSON Array
    private JSONArray result;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_daftar_pasien, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TANGGAL_PERIKSA = (TextView) view.findViewById(R.id.TANGGAL_PERIKSA);

        //Initializing the ArrayList
        poli = new ArrayList<String>();

        //Initializing Spinner
        spinner = (Spinner) view.findViewById(R.id.NAMA_POLI);
        spinner2 = (Spinner) view.findViewById(R.id.waktu);

        String[] arraySpinner = new String[] {
                "09:00", "15:00"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);

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



        //This method will fetch the data from the URL
        getData();

        /**
         * Kita menggunakan format tanggal dd-MM-yyyy
         * jadi nanti tanggal nya akan diformat menjadi
         * misalnya 01-12-2017
         */
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


        bt_TANGGAL_PERIKSA = (Button) view.findViewById(R.id.buttonTanggal);
        bt_TANGGAL_PERIKSA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        buttonDaftarAntrian = (Button) view.findViewById(R.id.buttonDaftarAntrian);
        buttonDaftarAntrian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SlideshowFragment();
            }
        });
    }

    private void getData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest(config.DATA_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(config.JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getPoli(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getPoli(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                poli.add(json.getString(config.TAG_USERNAME));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
        spinner.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, poli));
    }

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                TANGGAL_PERIKSA.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    void SlideshowFragment(){
        String url="";
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
                                builder.setMessage("You're already register");
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
                form.put("NAMA_POLI", spinner.getAdapter().toString());
                form.put("TANGGAL_PERIKSA", TANGGAL_PERIKSA.getText().toString());
                form.put("JAM_PERIKSA", spinner2.getAdapter().toString());
                return form;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(respon);
    }

}