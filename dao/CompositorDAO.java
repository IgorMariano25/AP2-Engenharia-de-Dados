package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Compositor;
import modelo.Compositor;
import modelo.Compositor;

public class CompositorDAO {

        private Connection connection;
        public CompositorDAO(Connection connection) {
        this.connection = connection;
    }

    public void criarSemMusica(Compositor compositor){
        try {
            String sql = "INSERT INTO compositor (nome) VALUES (?)";
 
            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, compositor.getNome());
                pstm.execute(); 
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // VERIFICAR COM A TALITA

    // public Compositor lerCompositorSemId(Compositor compositor) {
    //     String sql = "SELECT * FROM compositor WHERE nome = ?";

    //     try (PreparedStatement pstm = connection.prepareStatement(sql)) {
    //         pstm.setString(1, compositor.getNome());

    //         try (ResultSet rs = pstm.executeQuery()) {
    //             if (rs.next()) {
    //                 Compositor lerCompositorSemId = new Compositor(rs.getString("nome"));
    //                 return lerCompositorSemId;
    //             }
    //         }
    //     } catch (SQLException e) {
    //         throw new RuntimeException(e);
    //     }

    //     return null;
    // }

    public void updateCompositorSemId(Compositor compositor) {
        try {
            String sql = "UPDATE compositor SET nome = ? WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, compositor.getNovoNome());
                pstm.setString(2, compositor.getNome());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSemId(Compositor compositor) {
        try {
            String sql = "DELETE FROM compositor WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, compositor.getNome());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}