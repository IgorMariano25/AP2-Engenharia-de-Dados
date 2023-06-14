package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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


    public Compositor retornarPeloId(int id) {
        try {
            String sql = "SELECT * FROM compositor WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, id);

                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        int idRetornado = rs.getInt("id");
                        String nome = rs.getString("nome");
                        Compositor compositor = new Compositor(nome, idRetornado);
                        return compositor;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

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

    public void updateCompositorComId(Compositor compositor, int id) {
        try {
            String sql = "UPDATE compositor SET nome = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, compositor.getNovoNome());
                pstm.setInt(2, compositor.getId());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCompositorSemId(Compositor compositor) {
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