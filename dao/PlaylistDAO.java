package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import modelo.Musica;
import modelo.Playlist;

public class PlaylistDAO {
    private Connection connection;

    public PlaylistDAO(Connection connection) {
        this.connection = connection;
    }

    public void criarComMusica(Playlist playlist, Musica musica) {
        try {
            String sql = "INSERT INTO Playlist (Data_criacao, titulo, visibilidade, fk_categoria) " +
                    "VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setDate(1, Date.valueOf(playlist.getDataCriacao()));
                pstm.setString(2, playlist.getTitulo());
                pstm.setBoolean(3, playlist.getVisibilidade());
                pstm.setString(4, playlist.getCategoria().getNome());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Playlist lerPlaylistSemId(Playlist playlist) {
        try {
            String sql = "SELECT * FROM playlist WHERE titulo = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(4, playlist.getTitulo());

                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        Playlist lerPlaylistSemId = new Playlist(null, rs.getString("titulo"), false, null);
                        return lerPlaylistSemId;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Playlist lerPlaylistComId(Playlist playlist, int id) {
        try {
            String sql = "SELECT * FROM playlist WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, playlist.getId());

                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        Playlist lerPlaylistSemId = new Playlist(rs.getInt(id), null, null, false, null);
                        return lerPlaylistSemId;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updatePlaylistComId(Playlist playlist, int id) {
        try {
            String sql = "UPDATE playlist SET titulo = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, playlist.getId());
                pstm.setString(2, playlist.getTitulo());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePlaylistSemId(Playlist playlist) {
        try {
            String sql = "UPDATE playlist SET titulo = ? WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(2, playlist.getTitulo());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePlaylistComId(Playlist playlist, int id) {
        try {
            String sql = "DELETE FROM playlist WHERE id = ? AND titulo = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, playlist.getId());
                pstm.setString(2, playlist.getTitulo());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePlaylistSemId(Playlist playlist) {
        try {
            String sql = "DELETE FROM playlist WHERE titulo = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, playlist.getTitulo());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Playlist buscarPlaylistPeloTitulo(Playlist playlist){
        try {
            String sql = "SELECT * FROM playlist WHERE titulo = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, playlist.getTitulo());


                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        String tituloPlaylist = rs.getString("titulo");
                        Playlist lerPlaylist = new Playlist(tituloPlaylist);
                        return lerPlaylist;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Musica> OuvirPlaylist(List<Musica> playlist) {
        try {
            String query = "SELECT Letra FROM Musica WHERE Título = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            
            for (Musica musica : playlist) {
                statement.setString(1, musica.getTitulo());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    musica.setLetra(resultSet.getString("Letra"));
                }
            }
            
            return playlist;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}