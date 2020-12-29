package com.example.coba.ui.pasien_jadwal_pemeriksaan;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coba.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JadwalPemeriksaanFragment extends Fragment {

    ArrayList<dataObject>list;
    ListView listView;
    private JadwalPemeriksaanViewModel mViewModel;

    public static JadwalPemeriksaanFragment newInstance() {
        return new JadwalPemeriksaanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_jadwal_pemeriksaan, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        listView= (ListView) view.findViewById(R.id.listview_jadwalPemeriksaan);
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(JadwalPemeriksaanViewModel.class);
        jadwalPemeriksaan();
    }

    void jadwalPemeriksaan(){
        //list=new ArrayList<>();
        String url=""; //url backend php
        StringRequest request=new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            JSONArray jsonArray =jsonObject.getJSONArray("data");
                            for (int i=0;1<jsonArray.length(); i++)
                            {
                                JSONObject getData=jsonArray.getJSONObject(i);
                                String NAMA_POLI=getData.getString("NAMA_POLI");
                                String NO_ANTRIAN=getData.getString("NO_ANTRIAN");
                                String TANGGAL_PERIKSA=getData.getString("TANGGAL_PERIKSA");
                                String WAKTU_PERIKSA=getData.getString("WAKTU_PERIKSA");
                                // line code 78 ini kalau udh bisa nampil NO ANTRIAN Berarti bener.
                                // kalau sudah code  78 di comment trs uncomment line 59,79,81,82
                                Toast.makeText(getActivity().getApplicationContext(), NO_ANTRIAN, Toast.LENGTH_LONG).show();
                                //list.add(new dataObject(NAMA_POLI,NO_ANTRIAN,TANGGAL_PERIKSA,WAKTU_PERIKSA));
                            }
                            //Adapter adapter=new Adapter(JadwalPemeriksaanFragment.this, list);
                            //listView.setAdapter(adapter);
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
        );
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(request);
    }

    class Adapter extends BaseAdapter{
        Context context;
        LayoutInflater inflater;
        ArrayList<dataObject> model;
        public Adapter(Context context, ArrayList<dataObject>model){
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

        TextView  NAMA_POLI, NO_ANTRIAN, TANGGAL_PERIKSA, WAKTU_PERIKSA;
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=inflater.inflate(R.layout.list_jadwal_pemeriksaan, parent, false);
            NAMA_POLI= view.findViewById(R.id.NAMA_POLI);
            TANGGAL_PERIKSA= view.findViewById(R.id.TANGGAL_PERIKSA);
            NO_ANTRIAN= view.findViewById(R.id.NO_ANTRIAN);
            WAKTU_PERIKSA= view.findViewById(R.id.WAKTU_PERIKSA);

            NAMA_POLI.setText(model.get(position).getNAMA_POLI());
            NO_ANTRIAN.setText(model.get(position).getNO_ANTRIAN());
            TANGGAL_PERIKSA.setText(model.get(position).getTANGGAL_PERIKSA());
            WAKTU_PERIKSA.setText(model.get(position).getWAKTU_PERIKSA());
            return view;
        }
    }
}