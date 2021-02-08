package ehu.isad.model;

public class Bozkaketa {
    private String bozkatuaIzanDa;
    private String bozkatuDu;
    private int urtea;
    private int puntuak;

    public String getBozkatuaIzanDa() {
        return bozkatuaIzanDa;
    }

    public void setBozkatuaIzanDa(String bozkatuaIzanDa) {
        this.bozkatuaIzanDa = bozkatuaIzanDa;
    }

    public String getBozkatuDu() {
        return bozkatuDu;
    }

    public void setBozkatuDu(String bozkatuDu) {
        this.bozkatuDu = bozkatuDu;
    }

    public int getUrtea() {
        return urtea;
    }

    public void setUrtea(int urtea) {
        this.urtea = urtea;
    }

    public int getPuntuak() {
        return puntuak;
    }

    public void setPuntuak(int puntuak) {
        this.puntuak = puntuak;
    }


    public Bozkaketa(String bozkatuaIzanDa, String bozkatuDu, int urtea, int puntuak) {
        this.bozkatuaIzanDa = bozkatuaIzanDa;
        this.bozkatuDu = bozkatuDu;
        this.urtea = urtea;
        this.puntuak = puntuak;
    }



}
