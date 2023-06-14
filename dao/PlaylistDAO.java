package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Musica;
import modelo.Playlist;

public class PlaylistDAO {
        private Connection connection;
 
    public PlaylistDAO(Connection connection) {
        this.connection = connection;
    }

        public void criarComMusica(Playlist playlist, Musica musica){
        try {
            String sql = "INSERT INTO Playlist (dataCriacao, visbilidade, titulo) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setDate(2, (Date) playlist.getDataCriacao());
                pstm.setBoolean(3, playlist.getVisibilidade());
                pstm.setString(4, playlist.getTitulo());
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
}
