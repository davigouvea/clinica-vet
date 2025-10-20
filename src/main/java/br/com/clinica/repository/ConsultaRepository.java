package br.com.clinica.repository;

import java.util.ArrayList;
import java.util.List;
import br.com.clinica.model.Consulta;


public class ConsultaRepository {

    private List<Consulta> consultas = new ArrayList<>();

    public void salvar(Consulta consulta) {
        consultas.add(consulta);
    }

    public List<Consulta> listarTodos() {
        return new ArrayList<>(consultas);
    }
}