import java.time.LocalDateTime;

public class Modifica {
    private String matricola;
    private int idPlaylistCondivisa;
    private LocalDateTime dataOra;
    private String tipoOperazione;
    private String stato;

    public Modifica(String matricola, int idPlaylistCondivisa, LocalDateTime dataOra, String tipoOperazione, String stato) {
        this.matricola = matricola;
        this.idPlaylistCondivisa = idPlaylistCondivisa;
        this.dataOra = dataOra;
        this.tipoOperazione = tipoOperazione;
        this.stato = stato;
    }

    public String getMatricola() { return matricola; }
    public int getIdPlaylistCondivisa() { return idPlaylistCondivisa; }
    public LocalDateTime getDataOra() { return dataOra; }
    public String getTipoOperazione() { return tipoOperazione; }
    public String getStato() { return stato; }
}