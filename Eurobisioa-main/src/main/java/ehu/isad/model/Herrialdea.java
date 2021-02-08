package ehu.isad.model;

public class Herrialdea {
    private String izena, pBandera,pTv;
    //Setter
    public void setIzena(String izena) {
        this.izena = izena;
    }

    public void setpBandera(String pBandera) {
        this.pBandera = pBandera;
    }

    public void setpTv(String pTv) {
        this.pTv = pTv;
    }

    //Getter
    public String getIzena() {
        return izena;
    }

    public String getpBandera() {
        return pBandera;
    }

    public String getpTv() {
        return pTv;
    }


    //Constructor
    public Herrialdea(String izena, String pBandera, String pTv) {
        this.izena = izena;
        this.pBandera = pBandera;
        this.pTv = pTv;
    }

    @Override
    public String toString() {
        return izena;
    }
}
