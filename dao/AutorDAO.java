package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Autor;

public class AutorDAO {

    private Connection connection;
 
    public AutorDAO(Connection connection) {
        this.connection = connection;
    }

    public void createSemMusica(Autor autor){
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
}