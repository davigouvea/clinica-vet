package br.com.clinica.model;

public class Cachorro extends Animal {
    private String raca;

    public Cachorro(String nome, int idade, Tutor tutor, String raca) {
        super(nome, idade, tutor);
        this.raca = raca;
    }

    @Override
    public String emitirSom() {
        return "Au au!";
    }
}