public class PlaylistPubblica extends Playlist {
    private int numVisualizzazioni;
    private String tagRicerca;

    public PlaylistPubblica(int id, String nome, String matricolaProprietario, int numVisualizzazioni, String tagRicerca) {
        super(id, nome, matricolaProprietario);
        this.numVisualizzazioni = numVisualizzazioni;
        this.tagRicerca = tagRicerca;
    }

    public int getNumVisualizzazioni() { return numVisualizzazioni; }
    public String getTagRicerca() { return tagRicerca; }
}
