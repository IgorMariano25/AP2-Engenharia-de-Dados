package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Usuario;

public class UsuarioDAO {

    private Connection connection;
 
    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    } 

    public void criarUsuario(Usuario usuario){
        try {
            String sql = "INSERT INTO usuario (nome) VALUES (?)";
 
            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                pstm.setString(3, usuario.getCpf());
                pstm.setString(1, usuario.getNome());
                pstm.execute(); 
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario lerUsuario(Usuario usuario, int cpf) {
        try {
            String sql = "SELECT * FROM usuario WHERE cpf = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(3, cpf);

                try (ResultSet rs = pstm.executeQuery()) {
                    if (rs.next()) {
                        Usuario lerUsuario = new usuario(sql);
                        lerUsuario.setCpf(rs.getInt("cpf"));
                        lerUsuario.setNome(rs.getString("nome"));
                        return lerUsuario;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void updateUsuario(Usuario usuario, int cpf) {
        try {
            String sql = "UPDATE usuario SET nome = ? WHERE cpf = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(3, usuario.getCpf())
                pstm.setString(1, usuario.getNome());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUsuarioSemCPF(Usuario usuario) {
        try {
            String sql = "DELETE FROM usuario WHERE nome = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, usuario.getNome());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUsuarioComCPF(Usuario usuario, int cpf) {
        try {
            String sql = "DELETE FROM usuario WHERE nome = ? WHERE cpf = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(3, usuario.getCpf())
                pstm.setString(1, usuario.getNome());
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}