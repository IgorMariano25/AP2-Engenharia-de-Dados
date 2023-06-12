package modelo;

import java.util.Date;

public class Playlist {
  private int id;
  private Date dataCriacao;
  private String titulo;
  private int visibilidade;
  private String fkCategoria;
  
  public Playlist(int id, Date dataCriacao, String titulo, int visibilidade, String fkCategoria) {
    this.id = id;
    this.dataCriacao = dataCriacao;
    this.titulo = titulo;
    this.visibilidade = visibilidade;
    this.fkCategoria = fkCategoria;
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
  public String getFkCategoria() {
    return fkCategoria;
  }
  public void setFkCategoria(String fkCategoria) {
    this.fkCategoria = fkCategoria;
  }

}
