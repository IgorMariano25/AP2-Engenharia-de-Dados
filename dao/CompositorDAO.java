package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Autor;
import modelo.Compositor;
import modelo.Musica;

public class CompositorDAO {

    private Connection connection;

    public CompositorDAO(Connection connection) {
        this.connection = connection;
    }

    public void criarSemMusica(Compositor compositor) {
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

        public void criarComMusica(Compositor compositor, Musica musica) {
        try {
            String sql = "INSERT INTO autor_musica (fk_autor, fk_musica) VALUES (?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setInt(1, compositor.getId());
                pstm.setInt(2, musica.getId());
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
                        String nome = rs.getString("nome");
                        Compositor compositor = new Compositor(nome);
                        return compositor;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void deleteCompositor(Compositor compositor) {
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

    public Compositor lerCompositor(Compositor compositor) {
        try {
            String sql = "SELECT * FROM compositor WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, compositor.getNome());

                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        String nomeCompositor = rs.getString("nome");
                        Compositor lerCompositor = new Compositor(nomeCompositor);
                        
                        return lerCompositor;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}