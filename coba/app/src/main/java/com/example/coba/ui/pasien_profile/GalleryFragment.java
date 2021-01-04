package com.example.coba.ui.pasien_profile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coba.R;
import com.example.coba.ui.pasien_jadwal_pemeriksaan.JadwalPemeriksaanFragment;
import com.example.coba.ui.pasien_jadwal_pemeriksaan.dataObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    DatePickerDialog dataPickerDialog;
    SimpleDateFormat dateFormat;
    TextView TANGGAL_LAHIR_PASIEN;
    Button buttonTanggaLahir;
    Button buttonSimpanProfilePasien;
    ArrayList<dataObjectProfilePasien>list;
    EditText editText;
    EditText NAMA_PROFILE_PASIEN, EMAIL_PROFILE_PASIEN, HP_PROFILE_PASIEN, ALAMAT_PROFILE_PASIEN;

    private Spinner spinner;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile_pasien, container, false);
        Adapter adapter = new Adapter(getActivity(),list);
        //editText.setAdapter(adapter);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        NAMA_PROFILE_PASIEN = (EditText) view.findViewById(R.id.NAMA_PROFILE_PASIEN);
        EMAIL_PROFILE_PASIEN = (EditText) view.findViewById(R.id.EMAIL_PROFILE_PASIEN);
        HP_PROFILE_PASIEN = (EditText) view.findViewById(R.id.HP_PROFILE_PASIEN);
        ALAMAT_PROFILE_PASIEN = (EditText) view.findViewById(R.id.ALAMAT_PROFILE_PASIEN);
        TANGGAL_LAHIR_PASIEN = (TextView) view.findViewById(R.id.TANGGAL_PROFILE_PASIEN);

        //Initializing Spinner
        spinner = (Spinner) view.findViewById(R.id.GENDER_PROFILE_PASIEN);

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


        /**
         * Kita menggunakan format tanggal dd-MM-yyyy
         * jadi nanti tanggal nya akan diformat menjadi
         * misalnya 01-12-2017
         */
        dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


        buttonTanggaLahir = (Button) view.findViewById(R.id.buttonTanggalLahir);
        buttonTanggaLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        buttonSimpanProfilePasien = (Button) view.findViewById(R.id.buttonSimpanProfilePasien);
        buttonSimpanProfilePasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GalleryFragment();
            }
        });
    }

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        dataPickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

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
                TANGGAL_LAHIR_PASIEN.setText(dateFormat.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        dataPickerDialog.show();
    }

    void GalleryFragment(){
        String url="http://192.168.56.1/uasmobile/update_pasien.php";
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
                form.put("NAMA_PASIEN", NAMA_PROFILE_PASIEN.getText().toString());
                form.put("TANGGAL_LAHIR", TANGGAL_LAHIR_PASIEN.getText().toString());
                form.put("GENDER_PASIEN",spinner.getSelectedItem().toString());
                form.put("ALAMAT_PASIEN", ALAMAT_PROFILE_PASIEN.getText().toString());
                form.put("EMAIL_PASIEN", EMAIL_PROFILE_PASIEN.getText().toString());
                form.put("NO_TELP_PASIEN", HP_PROFILE_PASIEN.getText().toString());
                return form;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(respon);
    }

    class Adapter extends BaseAdapter {
        Context context;
        LayoutInflater inflater;
        ArrayList<dataObjectProfilePasien> model;
        public Adapter(Context context, ArrayList<dataObjectProfilePasien>model){
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
            View view=inflater.inflate(R.layout.fragment_profile_pasien, parent, false);
            NAMA_PROFILE_PASIEN= view.findViewById(R.id.NAMA_PROFILE_PASIEN);
            EMAIL_PROFILE_PASIEN= view.findViewById(R.id.EMAIL_PROFILE_PASIEN);
            HP_PROFILE_PASIEN= view.findViewById(R.id.HP_PROFILE_PASIEN);
            ALAMAT_PROFILE_PASIEN= view.findViewById(R.id.ALAMAT_PROFILE_PASIEN);
            TANGGAL_LAHIR_PASIEN= view.findViewById(R.id.TANGGAL_PROFILE_PASIEN);


            NAMA_PROFILE_PASIEN.setText(model.get(position).getNAMA_PASIEN());
            EMAIL_PROFILE_PASIEN.setText(model.get(position).getEMAIL_PASIEN());
            HP_PROFILE_PASIEN.setText(model.get(position).getNO_TELP_PASIEN());
            ALAMAT_PROFILE_PASIEN.setText(model.get(position).getALAMAT_PASIEN());
            TANGGAL_LAHIR_PASIEN.setText(model.get(position).getTANGGAL_LAHIR());
            return view;
        }
    }
}