package com.example.coba.ui.dokter_riwayat;

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
import com.example.coba.ui.dokter_list_antrian.dataObject_dokterListAntrian;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DokterRiwayatFragment extends Fragment {

    ArrayList<dataObject_dokterRiwayat> list;
    ListView listView;
    private DokterRiwayatViewModel mViewModel;

    public static DokterRiwayatFragment newInstance() {
        return new DokterRiwayatFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dokter_riwayat, container, false);
    }

    @Override
    public void  onViewCreated(View view, Bundle savedInstanceState) {
        listView= (ListView) view.findViewById(R.id.listview_dokterRiwayat);
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DokterRiwayatViewModel.class);
        dokterRiwayat();
    }


    void dokterRiwayat(){
        list=new ArrayList<>();
        String url="http://192.168.56.1/uasmobile/tampil_riwayat_dokter.php"; //url backend php
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
                                String NAMA_PASIEN=getData.getString("NAMA_PASIEN");
                                String JAM_MASUK=getData.getString("JAM_MASUK");
                                String JAM_KELUAR=getData.getString("JAM_KELUAR");
                                // line code 79 ini kalau udh bisa nampil JAM_KELUAR Berarti bener.
                                // kalau sudah code  79 di comment trs uncomment line 60,80,82,83
                                //Toast.makeText(getActivity().getApplicationContext(), JAM_KELUAR, Toast.LENGTH_LONG).show();
                                list.add(new dataObject_dokterRiwayat(NAMA_PASIEN,JAM_MASUK,JAM_KELUAR));
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
        ArrayList<dataObject_dokterRiwayat> model;
        public Adapter(Context context, ArrayList<dataObject_dokterRiwayat>model){
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

        TextView NAMA_PASIEN, JAM_MASUK, JAM_KELUAR;
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=inflater.inflate(R.layout.list_dokter_riwayat, parent, false);
            NAMA_PASIEN= view.findViewById(R.id.NAMA_PASIEN_DOKTER_RIWAYAT);
            JAM_MASUK= view.findViewById(R.id.JAM_MASUK_DOKTER_RIWAYAT);
            JAM_KELUAR= view.findViewById(R.id.JAM_KELUAR_DOKTER_RIWAYAT);

            NAMA_PASIEN.setText(model.get(position).getNAMA_PASIEN());
            JAM_MASUK.setText(model.get(position).getJAM_MASUK());
            JAM_KELUAR.setText(model.get(position).getJAM_KELUAR());
            return view;
        }
    }

}