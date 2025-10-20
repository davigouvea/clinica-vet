package br.com.clinica.model;

public class Gato extends Animal {
    private String pelagem;

    public Gato(String nome, int idade, Tutor tutor, String pelagem) {
        super(nome, idade, tutor);
        this.pelagem = pelagem;
    }

    @Override
    public String emitirSom() {
        return "Miau!";
    }
}