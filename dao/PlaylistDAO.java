package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
}
