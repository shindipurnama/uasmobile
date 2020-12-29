package com.example.coba.ui.dokter_list_antrian;

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
import com.example.coba.ui.pasien_riwayat.dataObject_pasienRiwayat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DokterListAntrianFragment extends Fragment {

    ArrayList<dataObject_dokterListAntrian> list;
    ListView listView;
    private DokterListAntrianViewModel mViewModel;

    public static DokterListAntrianFragment newInstance() {
        return new DokterListAntrianFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dokter_list_antrian, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        listView= (ListView) view.findViewById(R.id.listview_dokterListAntrian);
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DokterListAntrianViewModel.class);
        dokterListAntrian();
    }

    void dokterListAntrian(){
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
                                String NAMA_PASIEN=getData.getString("NAMA_PASIEN");
                                String NO_ANTRIAN=getData.getString("NO_ANTRIAN");
                                String WAKTU_PERIKSA=getData.getString("WAKTU_PERIKSA");
                                String STATUS=getData.getString("STATUS");
                                // line code 79 ini kalau udh bisa nampil NO ANTRIAN Berarti bener.
                                // kalau sudah code  79 di comment trs uncomment line 60,80,82,83
                                Toast.makeText(getActivity().getApplicationContext(), NO_ANTRIAN, Toast.LENGTH_LONG).show();
                                //list.add(new dataObject_dokterListAntrian(NAMA_PASIEN,NO_ANTRIAN,WAKTU_PERIKSA,STATUS));
                            }
                            //Adapter adapter=new Adapter(DokterListAntrianFragment.this, list);
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

    class Adapter extends BaseAdapter {
        Context context;
        LayoutInflater inflater;
        ArrayList<dataObject_dokterListAntrian> model;
        public Adapter(Context context, ArrayList<dataObject_dokterListAntrian>model){
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

        TextView NAMA_PASIEN, NO_ANTRIAN, WAKTU_PERIKSA, STATUS;
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=inflater.inflate(R.layout.list_dokter_list_antrian, parent, false);
            NAMA_PASIEN= view.findViewById(R.id.NAMA_PASIEN_DOKTER_LIST_ANTRIAN);
            NO_ANTRIAN= view.findViewById(R.id.NO_ANTRIAN_DOKTER_LIST_ANTRIAN);
            WAKTU_PERIKSA= view.findViewById(R.id.WAKTU_PERIKSA_DOKTER_LIST_ANTRIAN);
            STATUS= view.findViewById(R.id.STATUS_DOKTER_LIST_ANTRIAN);

            NAMA_PASIEN.setText(model.get(position).getNAMA_PASIEN());
            NO_ANTRIAN.setText(model.get(position).getNO_ANTRIAN());
            WAKTU_PERIKSA.setText(model.get(position).getWAKTU_PERIKSA());
            STATUS.setText(model.get(position).getSTATUS());
            return view;
        }
    }
}