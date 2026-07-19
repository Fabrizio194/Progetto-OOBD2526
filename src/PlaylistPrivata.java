public class PlaylistPrivata extends Playlist {
    private String pinAccesso;
    private String note;

    public PlaylistPrivata(int id, String nome, String matricolaProprietario, String pinAccesso, String note) {
        super(id, nome, matricolaProprietario);
        this.pinAccesso = pinAccesso;
        this.note = note;
    }

    public String getPinAccesso() { return pinAccesso; }
    public String getNote() { return note; }
}
