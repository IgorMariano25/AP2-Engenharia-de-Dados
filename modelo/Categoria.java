package modelo;

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
  public int getFkMusica() {
    return fkMusica;
  }
  public void setFkMusica(int fkMusica) {
    this.fkMusica = fkMusica;
  }

}
