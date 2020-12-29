package com.example.coba.ui.dokter_list_antrian;

public class dataObject_dokterListAntrian {
    String NAMA_PASIEN = "", NO_ANTRIAN = "", WAKTU_PERIKSA = "", STATUS = "";

    public dataObject_dokterListAntrian(String NAMA_PASIEN, String NO_ANTRIAN, String WAKTU_PERIKSA, String STATUS) {
        this.NAMA_PASIEN = NAMA_PASIEN;
        this.NO_ANTRIAN = NO_ANTRIAN;
        this.WAKTU_PERIKSA = WAKTU_PERIKSA;
        this.STATUS = STATUS;
    }

    public String getNAMA_PASIEN() {
        return NAMA_PASIEN;
    }

    public String getNO_ANTRIAN() {
        return NO_ANTRIAN;
    }

    public String getWAKTU_PERIKSA() {
        return WAKTU_PERIKSA;
    }

    public String getSTATUS() {
        return STATUS;
    }
}
