package com.example.coba.ui.pasien_riwayat;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coba.R;

public class PasienRiwayatFragment extends Fragment {

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PasienRiwayatViewModel.class);
        // TODO: Use the ViewModel
    }

}