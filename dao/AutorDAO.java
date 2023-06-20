package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Autor;
import modelo.Musica;
import modelo.Usuario;

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

    public void criarComMusica(Autor autor, Musica musica) {
        try {
            String sql = "INSERT INTO autor_musica (fk_autor, fk_musica) VALUES (?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setInt(1, autor.getId());
                pstm.setInt(2, musica.getId());
                pstm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
      public Autor retornarPeloId(int id) {
        try {
            String sql = "SELECT * FROM autor WHERE id = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, id);

                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        String nome = rs.getString("nome");
                        Autor autor = new Autor(nome);
                        return autor;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

        public void deleteAutor(Autor autor) {
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

    public Autor lerAutor(Autor autor) {
    try {
        String sql = "SELECT * FROM autor WHERE nome = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, autor.getNome());

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    String nomeAutor = rs.getString("nome");
                    Autor lerAutor = new Autor(nomeAutor);
                    return lerAutor;
                }
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return null;
}

public List<Musica> buscarMusicasDoAutor(Autor autor) {
    List<Musica> musicas = new ArrayList<>();

    try {
        String sql = "SELECT id FROM Musica musica " +
                     "INNER JOIN autor_musica autor_musica ON musica.id = autor_musica.Nome " +
                     "WHERE autor_musica.Nome = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, autor.getNome());

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    String titulo = rs.getString("Título");

                    Musica musica = new Musica(titulo);
                    musicas.add(musica);
                }
            }
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return musicas;
}


    public List<Musica> buscarTodosPorAutor(Autor autor) {
        List<Musica> musicas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Musica WHERE autor = ?";
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, autor.getNome());
                ResultSet resultSet = pstm.executeQuery();
                while (resultSet.next()) {
                    Musica musica = new Musica(
                        resultSet.getInt("id"),
                        resultSet.getString("Título"),
                        resultSet.getString("Letra"),
                        resultSet.getDate("Data_Lancamento").toLocalDate(),
                        resultSet.getInt("Duracao_segundos"),
                        resultSet.getInt("Censura"),
                        null
                    );
                    musicas.add(musica);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicas;
    }
}
