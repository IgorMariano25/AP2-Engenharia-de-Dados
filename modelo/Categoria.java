package modelo;

import java.util.ArrayList;

public class Categoria {
  private String nome;
  private ArrayList<Playlist> playlists;
  
  public Categoria(String nome) {
    this.nome = nome;
    this.playlists = new ArrayList<Playlist>();
  }

  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }

  public void addPlaylist(Playlist playlist) {
    this.playlists.add(playlist);
  }
  @Override
    public String toString(){
      return nome;
  }

    public String toStringNomeCategoria(){
      return "Categoria: " + nome;
  }
}
