package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            String sql = "INSERT INTO Musica (id, Título, Letra, Data_Lancamento, Duracao_segundos, Censura, Categoria) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, musica.getId());
            statement.setString(2, musica.getTitulo());
            statement.setString(3, musica.getLetra());
            statement.setDate(4, new java.sql.Date(musica.getDataLancamento().getTime()));
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
            statement.setDate(3, new java.sql.Date(musica.getDataLancamento().getTime()));
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
                Musica musica = new Musica(id, query, query, null, id, id);
                musica.setId(resultSet.getInt("id"));
                musica.setTitulo(resultSet.getString("Título"));
                musica.setLetra(resultSet.getString("Letra"));
                musica.setDataLancamento(resultSet.getDate("Data_Lancamento"));
                musica.setDuracaoSegundos(resultSet.getInt("Duracao_segundos"));
                musica.setCensura(resultSet.getInt("Censura"));
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
                Musica musica = new Musica(0, sql, sql, null, 0, 0);
                musica.setId(resultSet.getInt("id"));
                musica.setTitulo(resultSet.getString("Título"));
                musica.setLetra(resultSet.getString("Letra"));
                musica.setDataLancamento(resultSet.getDate("Data_Lancamento"));
                musica.setDuracaoSegundos(resultSet.getInt("Duracao_segundos"));
                musica.setCensura(resultSet.getInt("Censura"));
                musicas.add(musica);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicas;
    }
}