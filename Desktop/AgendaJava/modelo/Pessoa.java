package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Pessoa {

    private int id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private int idade;
    private ArrayList<Telefone> telefones;

    public Pessoa(int id, String nome, String cpf, LocalDate dataNascimento, int idade, ArrayList<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.telefones = telefones;
    }

    public Pessoa(int id, String nome, String cpf, LocalDate dataNascimento, ArrayList<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
        this.telefones = telefones;
    }

    public Pessoa(int id, String nome, String cpf, LocalDate dataNascimento, int idade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.telefones = new ArrayList<Telefone>();
    }

    public Pessoa(int id, String nome, String cpf, LocalDate dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
        this.telefones = new ArrayList<Telefone>();
    }

    public Pessoa(String nome, String cpf, LocalDate dataNascimento, ArrayList<Telefone> telefones) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
        this.telefones = telefones;
    }

    public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = calculaIdade(dataNascimento);
        this.telefones = new ArrayList<Telefone>();
    }

    private int calculaIdade(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        Period periodo = Period.between(dataNascimento, hoje);
        return periodo.getYears();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public ArrayList<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(ArrayList<Telefone> telefones) {
        this.telefones = telefones;
    }

    public void addTelefone(Telefone telefone) {
        this.telefones.add(telefone);
    }

    public void removeTelefone(Telefone telefone) {
        this.telefones.remove(telefone);
    }

    @Override
    public String toString() {
        return "{'pessoa':{'id': " + this.id + ", 'nome': '" + this.nome + "', 'idade': '" + this.idade + "'}}";
    }
}