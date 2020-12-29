package com.example.coba.ui.dokter_list_antrian;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coba.R;

public class DokterListAntrianFragment extends Fragment {

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DokterListAntrianViewModel.class);
        // TODO: Use the ViewModel
    }

}