package modelo;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Musica {
    private int id;
    private String titulo;
    private String letra;
    private LocalDate dataLancamento;
    private int duracaoSegundos;
    private int censura;
    private Categoria categoria;
    private ArrayList<Compositor> compositores;
    private ArrayList<Autor> autores;
    
    public Musica(int id, String titulo, String letra, LocalDate dataLancamento, int duracaoSegundos, int censura, Categoria categoria) {
      this.id = id;
      this.titulo = titulo;
      this.letra = letra;
      this.dataLancamento = dataLancamento;
      this.duracaoSegundos = duracaoSegundos;
      this.censura = censura;
      this.categoria = categoria;
      this.compositores = new ArrayList<Compositor>();
      this.autores = new ArrayList<Autor>();
    }

    public Musica(int id, String titulo, String letra, LocalDate dataLancamento, int duracaoSegundos, int censura) {
      this.id = id;
      this.titulo = titulo;
      this.letra = letra;
      this.dataLancamento = dataLancamento;
      this.duracaoSegundos = duracaoSegundos;
      this.censura = censura;
      this.compositores = new ArrayList<Compositor>();
      this.autores = new ArrayList<Autor>();
    }

    public Musica(String titulo, String letra, LocalDate dataLancamento, int duracaoSegundos, int censura) {
      this.titulo = titulo;
      this.letra = letra;
      this.dataLancamento = dataLancamento;
      this.duracaoSegundos = duracaoSegundos;
      this.censura = censura;
      this.compositores = new ArrayList<Compositor>();
      this.autores = new ArrayList<Autor>();
    }
    
    public Musica(String titulo, String letra, LocalDate dataLancamento, int duracaoSegundos, int censura, Categoria categoria) {
      this.titulo = titulo;
      this.letra = letra;
      this.dataLancamento = dataLancamento;
      this.duracaoSegundos = duracaoSegundos;
      this.censura = censura;
      this.categoria = categoria;
      this.compositores = new ArrayList<Compositor>();
      this.autores = new ArrayList<Autor>();
    }

    public void addCompositor(Compositor compositor) {
      this.compositores.add(compositor);
    }

    public void addAutores(Autor autor) {
     this.autores.add(autor);
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

    public LocalDate getDataLancamento() {
      return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
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

    public Categoria getCategoria() {
      return categoria;
    }

    public void setCategoria(Categoria categoria) {
      this.categoria = categoria;
    }

    public void setCategoria(String string) {
    }
  @Override
  public String toString() {
    return "Letra: " + letra;
  }
}