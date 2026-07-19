public abstract class ElementoMultimediale {
    private int id;
    private String titolo;
    private String descrizione;
    private int durata;
    private String matricolaProprietario;

    public ElementoMultimediale(int id, String titolo, String descrizione, int durata, String matricolaProprietario) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.durata = durata;
        this.matricolaProprietario = matricolaProprietario;
    }

    public int getId() { return id; }
    public String getTitolo() { return titolo; }
    public String getDescrizione() { return descrizione; }
    public int getDurata() { return durata; }
    public String getMatricolaProprietario() { return matricolaProprietario; }
}