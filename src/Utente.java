import java.util.Objects;

public class Utente {
    private String matricola;
    private String nomeUtente;
    private String email;
    private String username;
    private String password;

    public Utente(String matricola, String nomeUtente, String email, String username, String password) {
        this.matricola = matricola;
        this.nomeUtente = nomeUtente;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getMatricola() { return matricola; }
    public String getNomeUtente() { return nomeUtente; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return Objects.equals(matricola, utente.matricola);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricola);
    }
}
