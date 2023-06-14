package dao;
import java.sql.Connection;

import modelo.Playlist;

public class PlaylistDAO {
        private Connection connection;
 
    public PlaylistDAO(Connection connection) {
        this.connection = connection;
    }
}
