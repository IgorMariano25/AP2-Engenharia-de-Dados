package modelo;

import java.util.ArrayList;

public class Autor {
  private String nome;
  private String novoNome;
  private int id;
  private ArrayList<Musica> musicas;
  
  public Autor(String nome) {
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
