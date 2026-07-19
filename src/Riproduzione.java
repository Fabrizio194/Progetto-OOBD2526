import java.time.LocalDateTime;

public class Riproduzione {
    private Utente utente;
    private ElementoMultimediale elemento;
    private LocalDateTime dataOra;

    public Riproduzione(Utente utente, ElementoMultimediale elemento, LocalDateTime dataOra) {
        this.utente = utente;
        this.elemento = elemento;
        this.dataOra = dataOra;
    }

    public Utente getUtente() { return utente; }
    public ElementoMultimediale getElemento() { return elemento; }
    public LocalDateTime getDataOra() { return dataOra; }
}