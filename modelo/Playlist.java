package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Playlist {
  private int id;
  private Date dataCriacao;
  private String titulo;
  private int visibilidade;
  private Categoria categoria;
  private ArrayList<Musica> musicas;
  
  public Playlist(int id, Date dataCriacao, String titulo, int visibilidade, Categoria categoria) {
    this.id = id;
    this.dataCriacao = dataCriacao;
    this.titulo = titulo;
    this.visibilidade = visibilidade;
    this.categoria = categoria;
  }

    public Playlist(Date dataCriacao, String titulo, int visibilidade, Categoria categoria) {
    this.dataCriacao = dataCriacao;
    this.titulo = titulo;
    this.visibilidade = visibilidade;
    this.categoria = categoria;
  }
  
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }
  public Date getDataCriacao() {
    return dataCriacao;
  }
  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }
  public String getTitulo() {
    return titulo;
  }
  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }
  public int getVisibilidade() {
    return visibilidade;
  }
  public void setVisibilidade(int visibilidade) {
    this.visibilidade = visibilidade;
  }

    public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public void addMusica(Musica musica) {
     this.musicas.add(musica);
    }
}