package modelo;

public class Autor {
  private String nome;
  private int fkMusica;
  
  public Autor(String nome, int fkMusica) {
    this.nome = nome;
    this.fkMusica = fkMusica;
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
