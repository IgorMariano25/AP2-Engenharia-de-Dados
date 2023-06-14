package modelo;

import java.util.Date;

public class Usuario {
  private int id;
  private String nome;
  private Date dataNascimento;
  private String cpf;
  private int numeroCartao;
  private ArrayList<Playlist> playlists;
  
  public Usuario(int id, String nome, Date dataNascimento, String cpf, int numeroCartao) {
    this.id = id;
    this.nome = nome;
    this.dataNascimento = dataNascimento;
    this.cpf = cpf;
    this.numeroCartao = numeroCartao;
    this.playlists = new ArrayList<Playlist>();
  }
  
  public Usuario(String nome, Date dataNascimento, String cpf, int numeroCartao) {
    this.nome = nome;
    this.dataNascimento = dataNascimento;
    this.cpf = cpf;
    this.numeroCartao = numeroCartao;
    this.playlists = new ArrayList<Playlist>();
  }
  
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public Date getDataNascimento() {
    return dataNascimento;
  }
  public void setDataNascimento(Date dataNascimento) {
    this.dataNascimento = dataNascimento;
  }
  public String getCpf() {
    return cpf;
  }
  public void setCpf(String cpf) {
    this.cpf = cpf;
  }
  public int getNumeroCartao() {
    return numeroCartao;
  }
  public void setNumeroCartao(int numeroCartao) {
    this.numeroCartao = numeroCartao;
  }
  public int getFkPlaylist() {
    return fkPlaylist;
  }
  public void setFkPlaylist(int fkPlaylist) {
    this.fkPlaylist = fkPlaylist;
  }
  public String getUsuarioCol() {
    return usuarioCol;
  }
  public void setUsuarioCol(String usuarioCol) {
    this.usuarioCol = usuarioCol;
  }

}
