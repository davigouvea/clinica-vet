package br.com.clinica.repository;

import java,utio.ArrayList;
import java.util.List;
import br.com.clinica.model.Animal;

public class AnimalRepository {

    private List<Animal> animais = new ArrayList<>();

    public void salvar(Animal animal) {
        animais.add(animal);
    }

    public List<Animal> listarTodos() {
        return new ArrayList<>(animais);
    }
    
}
