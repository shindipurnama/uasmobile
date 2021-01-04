package com.example.coba.ui.pasien_profile;

public class dataObjectProfilePasien {
    String NAMA_PASIEN="", EMAIL_PASIEN="", NO_TELP_PASIEN="", TANGGAL_LAHIR="", GENDER_PASIEN="", ALAMAT_PASIEN="";
    public  dataObjectProfilePasien(String NAMA_PASIEN, String EMAIL_PASIEN, String NO_TELP_PASIEN,
                                    String TANGGAL_LAHIR, String GENDER_PASIEN, String ALAMAT_PASIEN){
        this.NAMA_PASIEN=NAMA_PASIEN;
        this.EMAIL_PASIEN=EMAIL_PASIEN;
        this.NO_TELP_PASIEN=NO_TELP_PASIEN;
        this.TANGGAL_LAHIR=TANGGAL_LAHIR;
        this.GENDER_PASIEN=GENDER_PASIEN;
        this.ALAMAT_PASIEN=ALAMAT_PASIEN;
    }

    public String getNAMA_PASIEN() {
        return NAMA_PASIEN;
    }

    public String getEMAIL_PASIEN() {
        return EMAIL_PASIEN;
    }

    public String getNO_TELP_PASIEN() {
        return NO_TELP_PASIEN;
    }

    public String getTANGGAL_LAHIR() {
        return TANGGAL_LAHIR;
    }

    public String getGENDER_PASIEN() {
        return GENDER_PASIEN;
    }

    public String getALAMAT_PASIEN() {
        return ALAMAT_PASIEN;
    }
}
