package modelo;

import java.util.ArrayList;

public class Compositor {
  private String nome;
  private ArrayList<Musica> musicas;
  
  public Compositor(String nome) {
    this.nome = nome;
    this.musicas = new ArrayList<Musica>();
  }
    public void addMusica(Musica musica) {
     this.musicas.add(musica);
    }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
}
