package com.example.coba.ui.pasien_riwayat;

public class dataObject_pasienRiwayat {
    String NAMA_POLI = "", TANGGAL_PERIKSA = "", WAKTU_PERIKSA = "", STATUS="";

    public dataObject_pasienRiwayat(String NAMA_POLI, String TANGGAL_PERIKSA, String WAKTU_PERIKSA, String STATUS) {
        this.NAMA_POLI = NAMA_POLI;
        this.TANGGAL_PERIKSA = TANGGAL_PERIKSA;
        this.WAKTU_PERIKSA = WAKTU_PERIKSA;
        this.STATUS = STATUS;
    }

    public String getNAMA_POLI() {
        return NAMA_POLI;
    }

    public String getTANGGAL_PERIKSA() {
        return TANGGAL_PERIKSA;
    }

    public String getWAKTU_PERIKSA() {
        return WAKTU_PERIKSA;
    }

    public String getSTATUS() {
        return STATUS;
    }
}
