package br.com.clinica.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.clinica.model.Tutor;


public class TutorRepository {

    private Map<String, Tutor> tutores = new HashMap<>();

    public void salvar(Tutor tutor) {
        tutores.put(tutor.getCpf(), tutor);
    }

    public Tutor buscarPorCpf(String cpf) {
        return tutores.get(cpf);
    }

    public List<Tutor> listarTodos() {
        return new ArrayList<>(tutores.values());
    }
}