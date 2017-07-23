package com.haz.myapplication;

/**
 * Created by GOBLIN on 11/20/2016.
 */
public class adaptervb {
    private String id;
    private String nama;
    private String ket;
    private String image;

    public adaptervb(String id, String nama,
                     String ket,String image) {
        this.id = id;
        this.nama = nama;
        this.ket = ket;
        this.image = image;
    }

    public String getId() {
        return this.id;
    }

    public String getNama() {
        return this.nama;
    }
    public String getKet() {
        return this.ket;
    }
    public String getImage() {
        return this.image;
    }

}
