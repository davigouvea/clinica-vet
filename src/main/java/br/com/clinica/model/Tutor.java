package br.com.clinica.model;

public class Tutor {
    private String nome;
    private String cpf;
    private String telefone;

    public Tutor(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
}