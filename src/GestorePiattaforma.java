import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorePiattaforma {
    
    private String urlDatabase;
    private String utenteDatabase;
    private String passwordDatabase;

    public GestorePiattaforma(String urlDatabase, String utenteDatabase, String passwordDatabase) {
        this.urlDatabase = urlDatabase;
        this.utenteDatabase = utenteDatabase;
        this.passwordDatabase = passwordDatabase;
    }

    private Connection ottieniConnessione() throws SQLException {
        return DriverManager.getConnection(urlDatabase, utenteDatabase, passwordDatabase);
    }

    

    public boolean registraUtente(String matricola, String nomeUtente, String email, String username, String password) {
        String query = "INSERT INTO Utente (Matricola, NomeUtente, Email, Username, Password) VALUES (?, ?, ?, ?, ?)";
        try (Connection connessione = ottieniConnessione(); 
             PreparedStatement stmt = connessione.prepareStatement(query)) {
            
            stmt.setString(1, matricola);
            stmt.setString(2, nomeUtente);
            stmt.setString(3, email);
            stmt.setString(4, username);
            stmt.setString(5, password);
            
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Utente verificaLogin(String matricola, String password) {
        String query = "SELECT * FROM Utente WHERE Matricola = ? AND Password = ?";
        try (Connection connessione = ottieniConnessione(); 
             PreparedStatement stmt = connessione.prepareStatement(query)) {
            
            stmt.setString(1, matricola);
            stmt.setString(2, password);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Utente(
                        rs.getString("Matricola"),
                        rs.getString("NomeUtente"),
                        rs.getString("Email"),
                        rs.getString("Username"),
                        rs.getString("Password")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    

    public Audio caricaAudio(String matricola, String titolo, String descrizione, int durata, String formato, double frequenza, String testoBrano) {
        String query = "INSERT INTO Audio (Titolo, Descrizione, Durata, Formato, Frequenza, TestoBrano, Matricola) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connessione = ottieniConnessione(); 
             PreparedStatement stmt = connessione.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, titolo);
            stmt.setString(2, descrizione);
            stmt.setInt(3, durata);
            stmt.setString(4, formato);
            stmt.setDouble(5, frequenza);
            stmt.setString(6, testoBrano);
            stmt.setString(7, matricola);
            
            stmt.executeUpdate();
            
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return new Audio(keys.getInt(1), titolo, descrizione, durata, matricola, formato, frequenza, testoBrano);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Video caricaVideo(String matricola, String titolo, String descrizione, int durata, String risoluzione, String urlSorgente, int frameRate) {
        String query = "INSERT INTO Video (Titolo, Descrizione, Durata, Risoluzione, UrlSorgente, FrameRate, Matricola) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connessione = ottieniConnessione(); 
             PreparedStatement stmt = connessione.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, titolo);
            stmt.setString(2, descrizione);
            stmt.setInt(3, durata);
            stmt.setString(4, risoluzione);
            stmt.setString(5, urlSorgente);
            stmt.setInt(6, frameRate);
            stmt.setString(7, matricola);
            
            stmt.executeUpdate();
            
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return new Video(keys.getInt(1), titolo, descrizione, durata, matricola, risoluzione, urlSorgente, frameRate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    

    public PlaylistPrivata creaPlaylistPrivata(String matricola, String nome, String pin, String note) {
        String query = "INSERT INTO PlaylistPrivata (Nome, PinAccesso, Note, Matricola) VALUES (?, ?, ?, ?)";
        try (Connection connessione = ottieniConnessione(); 
             PreparedStatement stmt = connessione.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, nome);
            stmt.setString(2, pin);
            stmt.setString(3, note);
            stmt.setString(4, matricola);
            stmt.executeUpdate();
            
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) return new PlaylistPrivata(keys.getInt(1), nome, matricola, pin, note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PlaylistPubblica creaPlaylistPubblica(String matricola, String nome, String tag) {
        String query = "INSERT INTO PlaylistPubblica (Nome, TagRicerca, Matricola) VALUES (?, ?, ?)";
        try (Connection connessione = ottieniConnessione(); 
             PreparedStatement stmt = connessione.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, nome);
            stmt.setString(2, tag);
            stmt.setString(3, matricola);
            stmt.executeUpdate();
            
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) return new PlaylistPubblica(keys.getInt(1), nome, matricola, 0, tag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PlaylistCondivisa creaPlaylistCondivisa(String matricola, String nome) {
        String query = "INSERT INTO PlaylistCondivisa (Nome, Matricola) VALUES (?, ?)";
        try (Connection connessione = ottieniConnessione(); 
             PreparedStatement stmt = connessione.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, nome);
            stmt.setString(2, matricola);
            stmt.executeUpdate();
            
            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) return new PlaylistCondivisa(keys.getInt(1), nome, matricola);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    

    public boolean concediAccesso(String matricolaProprietario, String matricolaAutorizzato, int idPlaylistCondivisa) {
        String query = "CALL sp_ConcediAccesso(?, ?, ?)";
        try (Connection connessione = ottieniConnessione(); 
             PreparedStatement stmt = connessione.prepareStatement(query)) {
            
            stmt.setString(1, matricolaProprietario);
            stmt.setString(2, matricolaAutorizzato);
            stmt.setInt(3, idPlaylistCondivisa);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            System.err.println("Errore concessione accesso: " + e.getMessage());
            return false;
        }
    }

    public boolean aggiungiElementoAPlaylist(Utente operatore, Playlist playlist, ElementoMultimediale elemento, int ordine) {
        String tabella = determinareTabellaContiene(playlist, elemento);
        if (tabella == null) return false;

        String colonnaPlaylist = "IdPlaylistPrivata";
        if (playlist instanceof PlaylistPubblica) colonnaPlaylist = "IdPlaylistPubblica";
        else if (playlist instanceof PlaylistCondivisa) colonnaPlaylist = "IdPlaylistCondivisa";

        String colonnaElemento = (elemento instanceof Audio) ? "IdAudio" : "IdVideo";
        String query = "INSERT INTO " + tabella + " (" + colonnaPlaylist + ", " + colonnaElemento + ", Ordine) VALUES (?, ?, ?)";

        try (Connection connessione = ottieniConnessione(); 
             PreparedStatement stmt = connessione.prepareStatement(query)) {
            
            stmt.setInt(1, playlist.getId());
            stmt.setInt(2, elemento.getId());
            stmt.setInt(3, ordine);
            stmt.executeUpdate();

            
            if (playlist instanceof PlaylistCondivisa) {
            		registraModifica(operatore.getMatricola(), playlist.getId(), "aggiunta_elemento", "completata");
            }
            return true;

        } catch (SQLException e) {
            System.err.println("Errore inserimento in playlist: " + e.getMessage());
            return false;
        }
    }

    private String determinareTabellaContiene(Playlist p, ElementoMultimediale e) {
        if (p instanceof PlaylistPrivata) {
            return (e instanceof Audio) ? "Contiene_Privata_Audio" : "Contiene_Privata_Video";
        } else if (p instanceof PlaylistPubblica) {
            return (e instanceof Audio) ? "Contiene_Pubblica_Audio" : "Contiene_Pubblica_Video";
        } else if (p instanceof PlaylistCondivisa) {
            return (e instanceof Audio) ? "Contiene_Condivisa_Audio" : "Contiene_Condivisa_Video";
        }
        return null;
    }

    private void registraModifica(String matricola, int idPlaylist, String tipoOperazione, String stato) {
        String query = "CALL sp_RegistraModifica(?, ?, ?, ?)";
        try (Connection connessione = ottieniConnessione(); 
             PreparedStatement stmt = connessione.prepareStatement(query)) {
            
            stmt.setString(1, matricola);
            stmt.setInt(2, idPlaylist);
            stmt.setString(3, tipoOperazione);
            stmt.setString(4, stato);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    public boolean avviaRiproduzione(String matricolaUtente, ElementoMultimediale elemento) {
        String query = (elemento instanceof Audio) 
            ? "INSERT INTO Riproduzione_Audio (Matricola, IdAudio) VALUES (?, ?)"
            : "INSERT INTO Riproduzione_Video (Matricola, IdVideo) VALUES (?, ?)";

        try (Connection connessione = ottieniConnessione(); 
             PreparedStatement stmt = connessione.prepareStatement(query)) {
            
            stmt.setString(1, matricolaUtente);
            stmt.setInt(2, elemento.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
