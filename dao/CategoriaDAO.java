package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Categoria;

public class CategoriaDAO {

    private Connection connection;
 
    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }

    public void criarSemMusica(Categoria categoria){
        try {
            String sql = "INSERT INTO categoria (nome) VALUES (?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, categoria.getNome());
                pstm.execute(); 
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Categoria lerCategoriaSemId(Categoria categoria) {
        try {
            String sql = "SELECT * FROM categoria WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, categoria.getNome());

                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        Categoria lerCategoriaComId = new Categoria(sql);
                        lerCategoriaComId.setNome(rs.getString("nome"));
                        return lerCategoriaComId;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}