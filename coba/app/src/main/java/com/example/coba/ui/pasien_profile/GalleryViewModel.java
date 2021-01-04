package com.example.coba.ui.pasien_profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.coba.R;

import java.util.ArrayList;

public class GalleryViewModel extends ViewModel {

    EditText NAMA_PROFILE_PASIEN, EMAIL_PROFILE_PASIEN, HP_PROFILE_PASIEN, ALAMAT_PROFILE_PASIEN;
    TextView TANGGAL_LAHIR_PASIEN;
    private MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


}