public abstract class Playlist {
    private int id;
    private String nome;
    private String matricolaProprietario;

    public Playlist(int id, String nome, String matricolaProprietario) {
        this.id = id;
        this.nome = nome;
        this.matricolaProprietario = matricolaProprietario;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getMatricolaProprietario() { return matricolaProprietario; }
}
 