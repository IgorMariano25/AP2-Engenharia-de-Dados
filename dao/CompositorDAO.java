package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Compositor;

public class CompositorDAO {

        private Connection connection;
        public CompositorDAO(Connection connection) {
        this.connection = connection;
    }

    public void createSemMusica(Compositor compositor){
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
}