package com.example.coba.ui.dokter_profile;

public class dataObjectProfileDokter {
    String NAMA_DOKTER="", EMAIL_DOKTER="", NO_TELP_DOKTER="",  GENDER_DOKTER="", ALAMAT_DOKTER="";
    public  dataObjectProfileDokter(String NAMA_DOKTER, String EMAIL_DOKTER, String NO_TELP_DOKTER,
                                     String GENDER_DOKTER, String ALAMAT_DOKTER){
        this.NAMA_DOKTER=NAMA_DOKTER;
        this.EMAIL_DOKTER=EMAIL_DOKTER;
        this.NO_TELP_DOKTER=NO_TELP_DOKTER;
        this.GENDER_DOKTER=GENDER_DOKTER;
        this.ALAMAT_DOKTER=ALAMAT_DOKTER;
    }

    public String getNAMA_DOKTER() {
        return NAMA_DOKTER;
    }

    public String getEMAIL_DOKTER() {
        return EMAIL_DOKTER;
    }

    public String getNO_TELP_DOKTER() {
        return NO_TELP_DOKTER;
    }

    public String getGENDER_DOKTER() {
        return GENDER_DOKTER;
    }

    public String getALAMAT_DOKTER() {
        return ALAMAT_DOKTER;
    }
}
