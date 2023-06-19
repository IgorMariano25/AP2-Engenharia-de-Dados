package modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Playlist {
    private int id;
    private LocalDate dataCriacao;
    private String titulo;
    private boolean visibilidade;
    private Categoria categoria;
    private ArrayList<Musica> musicas;
    public Playlist(int id, LocalDate dataCriacao, String titulo, boolean visibilidade, Categoria categoria) {
      this.id = id;
      this.dataCriacao = dataCriacao;
      this.titulo = titulo;
      this.visibilidade = visibilidade;
      this.categoria = categoria;
      this.musicas = new ArrayList<Musica>();
    }

    public Playlist(LocalDate dataCriacao, String titulo, boolean visibilidade, Categoria categoria) {
      this.dataCriacao = dataCriacao;
      this.titulo = titulo;
      this.visibilidade = visibilidade;
      this.categoria = categoria;
      this.musicas = new ArrayList<Musica>();
    }
    public Playlist(String titulo) {
      this.titulo = titulo;
    }

    public int getId() {
      return id;
    }
    public void setId(int id) {
      this.id = id;
    }
    public LocalDate getDataCriacao() {
      return dataCriacao;
    }
    public void setDataCriacao(LocalDate dataCriacao) {
      this.dataCriacao = dataCriacao;
    }
    public String getTitulo() {
      return titulo;
    }
    public void setTitulo(String titulo) {
      this.titulo = titulo;
    }

    public boolean getVisibilidade() {
      return visibilidade;
    }
    public void setVisibilidade(boolean visibilidade) {
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