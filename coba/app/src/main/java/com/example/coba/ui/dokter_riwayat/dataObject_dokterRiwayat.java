package com.example.coba.ui.dokter_riwayat;

public class dataObject_dokterRiwayat{
    String NAMA_PASIEN = "", JAM_MASUK = "", JAM_KELUAR = "";

    public dataObject_dokterRiwayat(String NAMA_PASIEN, String JAM_MASUK, String JAM_KELUAR) {
        this.NAMA_PASIEN = NAMA_PASIEN;
        this.JAM_MASUK = JAM_MASUK;
        this.JAM_KELUAR = JAM_KELUAR;
    }

    public String getNAMA_PASIEN() {
        return NAMA_PASIEN;
    }

    public String getJAM_MASUK() {
        return JAM_MASUK;
    }

    public String getJAM_KELUAR() {
        return JAM_KELUAR;
    }
}
