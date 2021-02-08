package ehu.isad.model;

public class MezuaModel {
    String nori;
    String mezua;
    String nor;

    public String getNor() {
        return nor;
    }

    public void setNor(String nor) {
        this.nor = nor;
    }

    public String getNori() {
        return nori;
    }

    public void setNori(String nori) {
        this.nori = nori;
    }

    public String getMezua() {
        return mezua;
    }

    public void setMezua(String mezua) {
        this.mezua = mezua;
    }

    public MezuaModel(String nor, String nori, String mezua) {
        this.nor = nor;
        this.nori = nori;
        this.mezua = mezua;
    }


}
