public class Video extends ElementoMultimediale {
    private String risoluzione;
    private String urlSorgente;
    private int frameRate;

    public Video(int id, String titolo, String descrizione, int durata, String matricolaProprietario, String risoluzione, String urlSorgente, int frameRate) {
        super(id, titolo, descrizione, durata, matricolaProprietario);
        this.risoluzione = risoluzione;
        this.urlSorgente = urlSorgente;
        this.frameRate = frameRate;
    }

    public String getRisoluzione() { return risoluzione; }
    public String getUrlSorgente() { return urlSorgente; }
    public int getFrameRate() { return frameRate; }
}