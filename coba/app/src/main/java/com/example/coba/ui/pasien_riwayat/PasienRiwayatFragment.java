package com.example.coba.ui.pasien_riwayat;

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
import com.example.coba.ui.pasien_jadwal_pemeriksaan.dataObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PasienRiwayatFragment extends Fragment {

    ArrayList<dataObject_pasienRiwayat>list;
    ListView listView;
    private PasienRiwayatViewModel mViewModel;

    public static PasienRiwayatFragment newInstance() {
        return new PasienRiwayatFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pasien_riwayat, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        listView= (ListView) view.findViewById(R.id.listview_pasienRiwayat);
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PasienRiwayatViewModel.class);
        pasienRiwayat();
    }

    void pasienRiwayat(){
        list=new ArrayList<>();
        String url="http://192.168.56.1/uasmobile/tampil_riwayat_pasien.php"; //url backend php
        StringRequest request=new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            JSONArray jsonArray =jsonObject.getJSONArray("data");
                            for (int i=0;i<jsonArray.length(); i++)
                            {
                                JSONObject getData=jsonArray.getJSONObject(i);
                                String NAMA_POLI=getData.getString("NAMA_POLI");
                                String TANGGAL_PERIKSA=getData.getString("TANGGAL_PERIKSA");
                                String WAKTU_PERIKSA=getData.getString("WAKTU_PERIKSA");
                                String STATUS=getData.getString("STATUS");
                                // line code 78 ini kalau udh bisa nampil TANGGAL_PERIKSA Berarti bener.
                                // kalau sudah code  78 di comment trs uncomment line 60,80,82,83
                                //Toast.makeText(getActivity().getApplicationContext(), TANGGAL_PERIKSA, Toast.LENGTH_LONG).show();
                                list.add(new dataObject_pasienRiwayat(NAMA_POLI,TANGGAL_PERIKSA,WAKTU_PERIKSA,STATUS));
                            }
                            Adapter adapter=new Adapter(getActivity(), list);
                            listView.setAdapter(adapter);
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

    class Adapter extends BaseAdapter {
        Context context;
        LayoutInflater inflater;
        ArrayList<dataObject_pasienRiwayat> model;
        public Adapter(Context context, ArrayList<dataObject_pasienRiwayat>model){
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

        TextView NAMA_POLI, TANGGAL_PERIKSA, WAKTU_PERIKSA, STATUS;
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=inflater.inflate(R.layout.list_pasien_riwayat, parent, false);
            NAMA_POLI= view.findViewById(R.id.NAMA_POLI_PASIEN_RIWAYAT);
            TANGGAL_PERIKSA= view.findViewById(R.id.TANGGAL_PERIKSA_PASIEN_RIWAYAT);
            WAKTU_PERIKSA= view.findViewById(R.id.WAKTU_PERIKSA_PASIEN_RIWAYAT);
            STATUS= view.findViewById(R.id.STATUS_PASIEN_RIWAYAT);

            NAMA_POLI.setText(model.get(position).getNAMA_POLI());
            TANGGAL_PERIKSA.setText(model.get(position).getTANGGAL_PERIKSA());
            WAKTU_PERIKSA.setText(model.get(position).getWAKTU_PERIKSA());
            STATUS.setText(model.get(position).getSTATUS());
            return view;
        }
    }

}