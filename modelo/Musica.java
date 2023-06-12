package modelo;

import java.util.Date;
import java.util.List;

public class Musica {
    private int id;
    private String titulo;
    private String letra;
    private Date dataLancamento;
    private int duracaoSegundos;
    private int censura;
    
    public Musica(int id, String titulo, String letra, Date dataLancamento, int duracaoSegundos, int censura) {
      this.id = id;
      this.titulo = titulo;
      this.letra = letra;
      this.dataLancamento = dataLancamento;
      this.duracaoSegundos = duracaoSegundos;
      this.censura = censura;
    }
    
    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getTitulo() {
      return titulo;
    }

    public void setTitulo(String titulo) {
      this.titulo = titulo;
    }

    public String getLetra() {
      return letra;
    }

    public void setLetra(String letra) {
      this.letra = letra;
    }

    public Date getDataLancamento() {
      return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
      this.dataLancamento = dataLancamento;
    }

    public int getDuracaoSegundos() {
      return duracaoSegundos;
    }

    public void setDuracaoSegundos(int duracaoSegundos) {
      this.duracaoSegundos = duracaoSegundos;
    }

    public int getCensura() {
      return censura;
    }

    public void setCensura(int censura) {
      this.censura = censura;
    }

}
