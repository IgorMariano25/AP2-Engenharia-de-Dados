import java.security.KeyStore.PasswordProtection;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        // Criando Autores
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

        // Criando Compositores
        Compositor compositor1 = new Compositor("Igor Mariano");
        Compositor compositor2 = new Compositor("Felipe Castelhano");
        Compositor compositor3 = new Compositor("Guilherme Felix");

        Categoria categoria1 = new Categoria("Funk");
        Categoria categoria2 = new Categoria("Rap");
        Categoria categoria3 = new Categoria("Rock");

        CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
        categoriaDAO.criarSemMusica(categoria1);
        categoriaDAO.criarSemMusica(categoria2);
        categoriaDAO.criarSemMusica(categoria3);
        // categoriaDAO.deleteCategoriaSemId(categoria1);

        Musica musica1 = new Musica("Back in black", "Back in black, i hit the sack", LocalDate.of(1980, 2, 5), 255, 18,
                categoria3);
        Musica musica2 = new Musica("Tour", "Mas não sabe quanto cash eu fiz", LocalDate.of(2015, 7, 10), 198, 16,
                categoria2);
        Musica musica3 = new Musica("Balmain", "Ela olhou eu também", LocalDate.of(2022, 12, 16), 198, 16, categoria1);

        MusicaDAO musicaDAO = new MusicaDAO(connection);
        musicaDAO.criarMusica(musica1);
        musicaDAO.criarMusica(musica2);
        musicaDAO.criarMusica(musica3);

        CompositorDAO compositor_dao = new CompositorDAO(connection);

        compositor_dao.criarSemMusica(compositor1);
        compositor_dao.criarSemMusica(compositor2);
        compositor_dao.criarSemMusica(compositor3);

        // compositor_dao.criarComMusica(compositor3, musica3);

        // compositor_dao.deleteCompositor(compositor1);
        // compositor_dao.deleteCompositor(compositor2);

        PlaylistDAO playlistDAO = new PlaylistDAO(connection);
        Playlist playlist1 = new Playlist(LocalDate.of(2022, 5, 3), "RapCaviar", true, categoria2);
        playlistDAO.criarComMusica(playlist1, musica2);
        Playlist playlist2 = new Playlist(LocalDate.of(2022, 5, 3), "Funk é vida", false, categoria1);
        playlistDAO.criarComMusica(playlist2, musica3);
        Playlist playlist3 = new Playlist(LocalDate.of(2022, 5, 3), "Rock vive", true, categoria3);
        playlistDAO.criarComMusica(playlist3, musica1);

        // playlistDAO.deletePlaylistComId(playlist3, 3);
        // playlistDAO.deletePlaylistSemId(playlist2);

        System.out.println(playlistDAO.buscarPlaylistPeloTitulo(playlist3).toString());

        System.out.println(autor_dao.lerAutor(autor3).toString());
        System.out.println(compositor_dao.lerCompositor(compositor3).toString());
        System.out.println(musicaDAO.OuvirMusica(musica1).toStringLetra());
        System.out.println("\n\n");
        System.out.println(musicaDAO.lerMusica(musica1));

        autor_dao.criarComMusica(autor1, musica1);
        System.out.println(autor_dao.buscarMusicasDoAutor(autor1));
    }
}