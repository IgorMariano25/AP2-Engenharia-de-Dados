package modelo;

import java.util.ArrayList;

public class Compositor {
  private String nome;
  private int id;
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

    public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
  return "Compositor: " + nome;
  }
}