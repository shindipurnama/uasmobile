package com.example.coba.ui.pasien_jadwal_pemeriksaan;

public class dataObject{
    String NAMA_POLI="", NO_ANTRIAN="", TANGGAL_PERIKSA="", WAKTU_PERIKSA="";
    public dataObject(String NAMA_POLI, String NO_ANTRIAN, String TANGGAL_PERIKSA, String WAKTU_PERIKSA)
    {
        this.NAMA_POLI=NAMA_POLI;
        this.NO_ANTRIAN=NO_ANTRIAN;
        this.TANGGAL_PERIKSA=TANGGAL_PERIKSA;
        this.WAKTU_PERIKSA=WAKTU_PERIKSA;
    }

    public String getNAMA_POLI() {
        return NAMA_POLI;
    }

    public String getNO_ANTRIAN() {
        return NO_ANTRIAN;
    }

    public String getTANGGAL_PERIKSA() {
        return TANGGAL_PERIKSA;
    }

    public String getWAKTU_PERIKSA() {
        return WAKTU_PERIKSA;
    }

}
