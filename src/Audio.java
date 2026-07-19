public class Audio extends ElementoMultimediale {
    private String formato;
    private double frequenza;
    private String testoBrano;

    public Audio(int id, String titolo, String descrizione, int durata, String matricolaProprietario, String formato, double frequenza, String testoBrano) {
        super(id, titolo, descrizione, durata, matricolaProprietario);
        this.formato = formato;
        this.frequenza = frequenza;
        this.testoBrano = testoBrano;
    }

    public String getFormato() { return formato; }
    public double getFrequenza() { return frequenza; }
    public String getTestoBrano() { return testoBrano; }
}
