package ehu.isad.model;

public class Artista {
    public String getIzenArtistikoa() {
        return izenArtistikoa;
    }

    public void setIzenArtistikoa(String izenArtistikoa) {
        this.izenArtistikoa = izenArtistikoa;
    }

    public int getPartaideak() {
        return partaideak;
    }

    public void setPartaideak(int partaideak) {
        this.partaideak = partaideak;
    }

    private String izenArtistikoa;
    private int partaideak;
    public Artista(String izenArtistikoa, int partaideak) {
        this.izenArtistikoa = izenArtistikoa;
        this.partaideak = partaideak;
    }





}
