package dao;

import java.sql.Connection;

import modelo.Categoria;

public class CategoriaDAO {

    private Connection connection;
 
    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }
}