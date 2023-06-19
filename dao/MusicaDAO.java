package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Musica;

public class MusicaDAO {
    private Connection connection;

    public MusicaDAO(Connection connection) {
        this.connection = connection;
    }

    public void criarMusica(Musica musica) {
        try {
            String sql = "INSERT INTO Musica (id, Título, Letra, Data_Lancamento, Duracao_segundos, Censura, fk_categoria) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, musica.getId());
            statement.setString(2, musica.getTitulo());
            statement.setString(3, musica.getLetra());
            statement.setDate(4, Date.valueOf(musica.getDataLancamento()));
            statement.setInt(5, musica.getDuracaoSegundos());
            statement.setInt(6, musica.getCensura());
            statement.setString(7, musica.getCategoria().getNome());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Musica musica) {
        try {
            String sql = "UPDATE Musica SET Título = ?, Letra = ?, Data_Lancamento = ?, " +
                         "Duracao_segundos = ?, Censura = ?, Categoria = ?, WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, musica.getTitulo());
            statement.setString(2, musica.getLetra());
            statement.setDate(3, Date.valueOf(musica.getDataLancamento()));
            statement.setInt(4, musica.getDuracaoSegundos());
            statement.setInt(5, musica.getCensura());
            statement.setInt(6, musica.getId());
            statement.setString(7, musica.getCategoria().getNome());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int id) {
        try {
            String sql = "DELETE FROM Musica WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Musica buscarPorId(int id) {
        try {
            String query = "SELECT * FROM Musica WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Musica musica = new Musica(
                    resultSet.getInt("id"),
                    resultSet.getString("Título"),
                    resultSet.getString("Letra"),
                    resultSet.getDate("Data_Lancamento").toLocalDate(),
                    resultSet.getInt("Duracao_segundos"),
                    resultSet.getInt("Censura"),
                    null
                );
                return musica;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    


    public List<Musica> buscarTodos() {
        List<Musica> musicas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Musica";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicas;
    }
    

    public Musica OuvirMusica(Musica musica) {
        try {
            String query = "SELECT Letra FROM Musica WHERE Título = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, musica.getTitulo());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                musica.setLetra(resultSet.getString("Letra"));
                return musica;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Musica lerMusica(Musica musica) {
        try {
            String query = "SELECT id, titulo, dataLancamento, duracaoSegundos, censura, categoria FROM Musica WHERE Título = ?";
            try (PreparedStatement pstm = connection.prepareStatement(query)) {
                pstm.setString(1, musica.getTitulo());
                ResultSet resultSet = pstm.executeQuery();
                if (resultSet.next()) {
                    musica.setId(resultSet.getInt("id"));
                    musica.setTitulo(resultSet.getString("titulo"));
                    musica.setDataLancamento(resultSet.getDate("dataLancamento").toLocalDate());
                    musica.setDuracaoSegundos(resultSet.getInt("duracaoSegundos"));
                    musica.setCensura(resultSet.getInt("censura"));
                    musica.setCategoria(resultSet.getString("categoria"));
                    return musica;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
}