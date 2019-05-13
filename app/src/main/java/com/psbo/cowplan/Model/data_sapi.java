package com.psbo.cowplan.Model;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class data_sapi {

    //Deklarasi Variable
    private String nama_sapi;
    private String tanggal_birahi;
    private String tanggal_melahirkan;
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama_sapi() {
        return nama_sapi;
    }

    public void setNama_sapi(String nama_sapi) {
        this.nama_sapi = nama_sapi;
    }

    public String getTanggal_birahi() {
        return tanggal_birahi;
    }

    public void setTanggal_birahi(String tanggal_birahi) {
        this.tanggal_birahi = tanggal_birahi;
    }

    public String getTanggal_melahirkan() {
        return tanggal_melahirkan;
    }

    public void setTanggal_melahirkan(String tanggal_melahirkan) {
        this.tanggal_melahirkan = tanggal_melahirkan;
    }

    //Membuat Konstuktor kosong untuk membaca data snapshot
    public data_sapi(){
    }

    //Konstruktor dengan beberapa parameter, untuk mendapatkan Input Data dari User
    public data_sapi(String nama_sapi, String tanggal_birahi, String tanggal_melahirkan) {
        this.nama_sapi = nama_sapi;
        this.tanggal_birahi = tanggal_birahi;
        this.tanggal_melahirkan = tanggal_melahirkan;
    }
}
