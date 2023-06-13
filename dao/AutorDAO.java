package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Autor;

public class AutorDAO {

    private Connection connection;
 
    public AutorDAO(Connection connection) {
        this.connection = connection;
    }

    public void criarSemMusica(Autor autor){
        try {
            String sql = "INSERT INTO autor (nome) VALUES (?)";
 
            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(1, autor.getNome());
                pstm.execute(); 
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
      public Autor lerAutoresComId(Autor autor, int id) {
        try {
            String sql = "SELECT * FROM autor WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, id);

                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        Autor leituraAutorComId = new Autor(sql);
                        leituraAutorComId.setId(rs.getInt("id"));
                        leituraAutorComId.setNome(rs.getString("nome"));
                        return leituraAutorComId;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Autor lerAutoresSemId(Autor autor) {
        String sql = "SELECT * FROM autor WHERE nome = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, autor.getNome());

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    Autor leituraAutorSemId = new Autor(rs.getString("nome"));
                    return leituraAutorSemId;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void updateAutorComId(Autor autor, int id) {
        try {
            String sql = "UPDATE autor SET nome = ? WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, autor.getNome());
                pstm.setInt(2, autor.getId());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAutorSemId(Autor autor) {
        try {
            String sql = "UPDATE autor SET nome = ? WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, autor.getNovoNome());
                pstm.setString(2, autor.getNome());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAutorComId(Autor autor, int id) {
        try {
            String sql = "DELETE FROM autor WHERE id = ? AND nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, autor.getId());
                pstm.setString(2, autor.getNome());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public void deleteAutorSemId(Autor autor) {
        try {
            String sql = "DELETE FROM autor WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, autor.getNome());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // public List<Autor> getAll() {
    //     try {
    //         String sql = "SELECT * FROM autor";

    //         try (PreparedStatement pstm = connection.prepareStatement(sql)) {
    //             try (ResultSet rs = pstm.executeQuery()) {
    //                 List<Autor> autores = new ArrayList<>();

    //                 while (rs.next()) {
    //                     Autor autor = new Autor(sql);
    //                     autor.setId(rs.getInt("id"));
    //                     autor.setNome(rs.getString("nome"));
    //                     autores.add(autor);
    //                 }

    //                 return autores;
    //             }
    //         }
    //     } catch (SQLException e) {
    //         throw new RuntimeException(e);
    //     }
    // }
}
