package modelo;

import java.util.ArrayList;

public class Autor {
  private String nome;
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Autor: " + nome;
  }
}
