package ehu.isad.model;

public class Ordezkaritza {
    public String getAbezlaria() {
        return abezlaria;
    }

    public void setAbezlaria(String abezlaria) {
        this.abezlaria = abezlaria;
    }

    public String getHerrialdea() {
        return herrialdea;
    }

    public void setHerrialdea(String herrialdea) {
        this.herrialdea = herrialdea;
    }

    public int getUrtea() {
        return urtea;
    }

    public void setUrtea(int urtea) {
        this.urtea = urtea;
    }

    public String getAbeztia() {
        return abeztia;
    }

    public void setAbeztia(String abeztia) {
        this.abeztia = abeztia;
    }

    public int getPuntuak() {
        return puntuak;
    }

    public void setPuntuak(int puntuak) {
        this.puntuak = puntuak;
    }

    private String abezlaria;
    private String herrialdea;
    private int urtea;
    private String abeztia;
    private int puntuak;

    public Ordezkaritza(String abezlaria, String herrialdea, int urtea, String abeztia, int puntuak) {
        this.abezlaria = abezlaria;
        this.herrialdea = herrialdea;
        this.urtea = urtea;
        this.abeztia = abeztia;
        this.puntuak = puntuak;
    }

}