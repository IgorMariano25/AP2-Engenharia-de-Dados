import java.security.KeyStore.PasswordProtection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import dao.AutorDAO;
import dao.CategoriaDAO;
import dao.CompositorDAO;
import dao.ConnectionFactory;
import dao.MusicaDAO;
import dao.PlaylistDAO;
import modelo.Autor;
import modelo.Categoria;
import modelo.Musica;
import modelo.Playlist;
import modelo.Compositor;

public class Principal {
    
    public static void main(String[] args) throws SQLException {

        ConnectionFactory fabricaDeConexao = new ConnectionFactory();
        Connection connection = fabricaDeConexao.recuperaConexao();

        //Criando Autores
        Autor autor1 = new Autor("Igor Mariano");
        Autor autor2 = new Autor("Guilherme Felix");
        Autor autor3 = new Autor("Felipe Castelhano");

        AutorDAO autor_dao = new AutorDAO(connection);
        autor_dao.criarSemMusica(autor1);
        autor_dao.criarSemMusica(autor2);
        autor_dao.criarSemMusica(autor3);

        autor_dao.retornarPeloId(1);
        autor_dao.retornarPeloId(2);
        autor_dao.retornarPeloId(3);

        //Criando Compositores
        Compositor compositor2 = new Compositor("Igor Mariano");
        Compositor compositor3 = new Compositor("Felipe Castelhano");
        Compositor compositor4 = new Compositor("Guilherme Felix");

        Categoria categoria1 = new Categoria("Funk");
        Categoria categoria2 = new Categoria("Rap");
        Categoria categoria3 = new Categoria("Rock");

        CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
        categoriaDAO.criarSemMusica(categoria1);
        categoriaDAO.criarSemMusica(categoria2);
        categoriaDAO.criarSemMusica(categoria3);
        // categoriaDAO.deleteCategoriaSemId(categoria1);

        Musica musica1 = new Musica("Back in black", "Back in black, i hit the sack", new Date(1980, 2,5), 255, 18, categoria3);
        Musica musica2 = new Musica("Tour", "Mas não sabe quanto cash eu fiz", new Date(2015, 7,10), 198, 16,categoria2);
        Musica musica3 = new Musica("Balmain", "Ela olhou eu também", new Date(2022, 12,16), 198, 16,categoria1);
        MusicaDAO musicaDAO = new MusicaDAO(connection);
        musicaDAO.criarMusica(musica1);
        musicaDAO.criarMusica(musica2);
        musicaDAO.criarMusica(musica3);


        CompositorDAO compositor_dao = new CompositorDAO(connection);
      
        compositor_dao.criarSemMusica(compositor2);
        compositor_dao.criarSemMusica(compositor3);
        compositor_dao.criarSemMusica(compositor4);

        compositor_dao.deleteCompositor(compositor2);
        compositor_dao.deleteCompositor(compositor3);

        PlaylistDAO playlistDAO = new PlaylistDAO(connection);
        Playlist playlist1 = new Playlist(new Date(2022, 5, 3),"RapCaviar", true , categoria2);
        playlistDAO.criarComMusica(playlist1, musica2);
        Playlist playlist2 = new Playlist(new Date(2022, 5, 3),"Funk é vida", false, categoria1);
        playlistDAO.criarComMusica(playlist2, musica3);
        Playlist playlist3 = new Playlist(new Date(2022, 5, 3),"Rock vive", true , categoria3);
        playlistDAO.criarComMusica(playlist3, musica1);

        playlistDAO.deletePlaylistComId(playlist3, 3);
        playlistDAO.deletePlaylistSemId(playlist2);
        // playlistDAO.buscarPlaylistPeloTitulo(playlist3);

        System.out.println(autor_dao.lerAutor(autor3).toString());
    }
}

// Pessoa pessoa1 = new Pessoa("Amanda Senra", "00011122200", LocalDate.of(2000,
// 1, 01));
// Telefone telefone10 = new Telefone(TipoTelefone.Celular,55,21,982141939);
// pessoa1.addTelefone(telefone10);

// Pessoa pessoa2 = new Pessoa("Gabriel Martinez", "00011122211",
// LocalDate.of(2001, 2, 05));
// Telefone telefone20 = new Telefone(TipoTelefone.Celular,55,21,989963144);
// Telefone telefone21 = new Telefone(TipoTelefone.Residencial,55,21,22298312);
// pessoa2.addTelefone(telefone20);
// pessoa2.addTelefone(telefone21);

// Pessoa pessoa3 = new Pessoa("Joao Curvello", "00011122222",
// LocalDate.of(2002, 3, 10));
// Telefone telefone30 = new Telefone(TipoTelefone.Celular,55,21,994378235);
// pessoa3.addTelefone(telefone30);

// Pessoa pessoa4 = new Pessoa("Joao Correia", "00011122233", LocalDate.of(2003,
// 4, 15));
// Telefone telefone40 = new Telefone(TipoTelefone.Celular,55,21,964695794);
// Telefone telefone41 = new Telefone(TipoTelefone.Celular,55,21,96469579);
// pessoa4.addTelefone(telefone40);
// pessoa4.addTelefone(telefone41);

// Pessoa pessoa5 = new Pessoa("Joao Constant", "00011122244",
// LocalDate.of(2004, 5, 20)); //55 21 999309064
// Pessoa pessoa6 = new Pessoa("Matheus Herzog", "00011122255",
// LocalDate.of(2005, 6, 25)); //55 21 960197272 55 21 96525522
// Pessoa pessoa7 = new Pessoa("Thaís Bustamante", "00011122266",
// LocalDate.of(2000, 7, 30)); //55 21 973013773 55 21 24870553
// Pessoa pessoa8 = new Pessoa("Théo Mauricio", "00011122277",
// LocalDate.of(2001, 8, 01)); //55 24 992675080 55 24 92675080
// Pessoa pessoa9 = new Pessoa("Victor Lobianco", "00011122288",
// LocalDate.of(2002, 9, 05));//55 21 992471219

// System.out.println(pessoa1);
// System.out.println(pessoa2);
// System.out.println(pessoa3);
// System.out.println(pessoa4);
// System.out.println(pessoa5);
// System.out.println(pessoa6);
// System.out.println(pessoa7);
// System.out.println(pessoa8);
// System.out.println(pessoa9);

// System.out.println("Acabei de printar os objetos em memoria\n\n\n");

// ConnectionFactory fabricaDeConexao = new ConnectionFactory();
// Connection connection = fabricaDeConexao.recuperaConexao();

// PessoaDAO pdao = new PessoaDAO(connection);

// pdao.createComTelefone(pessoa1);
// pdao.createComTelefone(pessoa2);
// pdao.createComTelefone(pessoa3);
// pdao.createComTelefone(pessoa4);
// pdao.createComTelefone(pessoa5);
// pdao.createComTelefone(pessoa6);
// pdao.createComTelefone(pessoa7);
// pdao.createComTelefone(pessoa8);
// pdao.createComTelefone(pessoa9);

// ArrayList<Pessoa> pessoas = pdao.retriveAllComTelefone();
// //ArrayList<Pessoa> pessoas = pdao.retriveAllSemTelefone();

// System.out.println("Comecei a printar os objetos do BD\n");
// for (Pessoa pessoa : pessoas) {
// System.out.println(pessoa);
// for (Telefone telefone : pessoa.getTelefones()) {
// System.out.println(telefone);
// }
// }

// //Exemplo de Injection
// Pessoa pessoa10 = new Pessoa("%", "%' UNION SELECT cpf FROM Pessoa WHERE nome
// LIKE '%", LocalDate.of(2002, 9, 05));
// ArrayList<Pessoa> pessoas2 = pdao.retrieveInjection(pessoa10);
// for (Pessoa pessoa : pessoas2) {
// System.out.println(pessoa);
// }
// }
// }