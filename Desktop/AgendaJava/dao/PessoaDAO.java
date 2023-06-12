package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.time.LocalDate;

import java.util.ArrayList;

import modelo.Pessoa;
import modelo.Telefone;
import modelo.TipoTelefone;

public class PessoaDAO {

    private Connection connection;

    public PessoaDAO(Connection connection) {
        this.connection = connection;
    }

    public void createSemTelefone(Pessoa pessoa) {
        try {
            String sql = "INSERT INTO pessoa (nome, cpf, data_nascimento, idade) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, pessoa.getNome());
                pstm.setString(2, pessoa.getCpf());
                pstm.setObject(3, pessoa.getDataNascimento());
                pstm.setInt(4, pessoa.getIdade());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        pessoa.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createComTelefone(Pessoa pessoa) {
        try {
            String sql = "INSERT INTO pessoa (nome, cpf, data_nascimento, idade) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, pessoa.getNome());
                pstm.setString(2, pessoa.getCpf());
                pstm.setObject(3, pessoa.getDataNascimento());
                pstm.setInt(4, pessoa.getIdade());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        pessoa.setId(rst.getInt(1));
                        for (Telefone telefone : pessoa.getTelefones()) {
                            TelefoneDAO tdao = new TelefoneDAO(connection);
                            tdao.create(telefone, pessoa);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Pessoa> retriveAllSemTelefone(){
        
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();

		try {
			String sql = "SELECT id, nome, cpf, data_nascimento, idade FROM pessoa";

			try (PreparedStatement pstm = connection.prepareStatement(sql)) {
				pstm.execute();
                ResultSet rst = pstm.getResultSet();
                while (rst.next()){
                    int id = rst.getInt("id");
                    String nome = rst.getString("nome");
                    String cpf = rst.getString("cpf");
                    LocalDate data = rst.getObject("data_nascimento", LocalDate.class);
                    int idade = rst.getInt("idade");
                    Pessoa p = new Pessoa(id, nome, cpf, data, idade);
                    pessoas.add(p);
                }
			}
			return pessoas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }

    public ArrayList<Pessoa> retrivePessoasComTelefone(){

        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        Pessoa ultima = null;
        try {

            String sql = "SELECT p.id, p.nome, p.cpf, p.data_nascimento, p.idade, t.id, t.tipo, t.codigo_pais, t.codigo_area, t.numero "
                    + "FROM pessoa AS p "
                    + "INNER JOIN telefone AS t ON p.id = t.fk_pessoa";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultima == null || ultima.getId() != rst.getInt(1)) {
                            int p_id = rst.getInt(1);
                            String nome = rst.getString(2);
                            String cpf = rst.getString(3);
                            LocalDate data = rst.getObject(4, LocalDate.class);
                            int idade = rst.getInt(5);
                            Pessoa p = new Pessoa(p_id, nome, cpf, data, idade);
                            pessoas.add(p);
                            ultima = p;
                        }

                        int tel_id = rst.getInt(6);
                        TipoTelefone tipo = TipoTelefone.values()[rst.getInt(7)];
                        int cod_pais = rst.getInt(8);
                        int cod_area = rst.getInt(9);
                        int numero = rst.getInt(10);
                        Telefone t = new Telefone(tel_id, tipo, cod_pais, cod_area, numero);
                        ultima.addTelefone(t);
                    }
                }
                return pessoas;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Pessoa> retriveAllComTelefone(){

        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        Pessoa ultima = null;
        try {

            String sql = "SELECT p.id, p.nome, p.cpf, p.data_nascimento, p.idade, t.id, t.tipo, t.codigo_pais, t.codigo_area, t.numero "
                    + "FROM pessoa AS p "
                    + "LEFT JOIN telefone AS t ON p.id = t.fk_pessoa";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                try (ResultSet rst = pstm.getResultSet()) {
                    while (rst.next()) {
                        if (ultima == null || ultima.getId() != rst.getInt(1)) {
                            int p_id = rst.getInt(1);
                            String nome = rst.getString(2);
                            String cpf = rst.getString(3);
                            LocalDate data = rst.getObject(4, LocalDate.class);
                            int idade = rst.getInt(5);
                            Pessoa p = new Pessoa(p_id, nome, cpf, data, idade);
                            pessoas.add(p);
                            ultima = p;
                        }

                        if (rst.getInt(6) != 0){
                            int tel_id = rst.getInt(6);
                            TipoTelefone tipo = TipoTelefone.values()[rst.getInt(7)];
                            int cod_pais = rst.getInt(8);
                            int cod_area = rst.getInt(9);
                            int numero = rst.getInt(10);
                            Telefone t = new Telefone(tel_id, tipo, cod_pais, cod_area, numero);
                            ultima.addTelefone(t);
                        }
                    }
                }
                return pessoas;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Exemplo de má prática de programação
    public void createInjection(Pessoa pessoa) throws SQLException {

        String sgbd = "mysql";
        String endereco = "localhost";
        String bd = "agenda";
        String usuario = "root";
        String senha = "mysqlroot";

        Connection connection = DriverManager.getConnection(
                "jdbc:" + sgbd + "://" + endereco + "/" + bd + "?useTimezone=true&serverTimezone=UTC", usuario, senha);

        Statement stm = connection.createStatement();
        stm.execute("INSERT INTO Pessoa (nome, cpf, data_nascimento, idade) VALUES ('"
                + pessoa.getNome() + "', '" + pessoa.getCpf() + "', '" + pessoa.getDataNascimento() + "', "
                + pessoa.getIdade() + ")", Statement.RETURN_GENERATED_KEYS);
        ResultSet rst = stm.getGeneratedKeys();
        while (rst.next()) {
            Integer id = rst.getInt(1);
            pessoa.setId(id);
        }
        rst.close();
        connection.close();
    }

    // Exemplo de má prática de programação
    public ArrayList<Pessoa> retrieveInjection(Pessoa pessoa) throws SQLException {

        String sgbd = "mysql";
        String endereco = "localhost";
        String bd = "agenda";
        String usuario = "root";
        String senha = "mysqlroot";

        Connection connection = DriverManager.getConnection(
                "jdbc:" + sgbd + "://" + endereco + "/" + bd + "?useTimezone=true&serverTimezone=UTC", usuario, senha);

        Statement stm = connection.createStatement();
        stm.execute("SELECT nome FROM Pessoa WHERE cpf = '"+pessoa.getCpf()+"'");
        ResultSet rst = stm.getResultSet();
        ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
        while (rst.next()) {
            //int p_id = rst.getInt(1);
            //String nome = rst.getString(2);
            //String cpf = rst.getString(3);
            //LocalDate data = rst.getObject(4, LocalDate.class);
            //int idade = rst.getInt(5);
            String nome = rst.getString(1);
            Pessoa p = new Pessoa(0, nome, "", LocalDate.now());
            pessoas.add(p);
        }
        rst.close();
        connection.close();
        return pessoas;
    }

}
