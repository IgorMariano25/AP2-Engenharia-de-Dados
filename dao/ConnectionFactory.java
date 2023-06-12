package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

  public Connection recuperaConexao() {
    try {
      String sgbd = "mysql";
      String endereco = "localhost";
      String bd = "AP2";
      String usuario = "root";
      String senha = "mysqlroot";

      Connection connection = DriverManager.getConnection(
          "jdbc:" + sgbd + "://" + endereco + "/" + bd + "?useTimezone=true&serverTimezone=UTC", usuario, senha);

      return connection;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}