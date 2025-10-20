package br.com.clinica.service;

import java.util.List;

import br.com.clinica.exceptions.AgendamentoException;
import br.com.clinica.model.Animal;
import br.com.clinica.model.Consulta;
import br.com.clinica.model.Tutor;
import br.com.clinica.repository.AnimalRepository;
import br.com.clinica.repository.ConsultaRepository;
import br.com.clinica.repository.TutorRepository;

public class ClinicaService {

    private TutorRepository tutorRepository;
    private AnimalRepository animalRepository;
    private ConsultaRepository consultaRepository;

    public ClinicaService() {
        this.tutorRepository = new TutorRepository();
        this.animalRepository = new AnimalRepository();
        this.consultaRepository = new ConsultaRepository();
    }

    //Métodos de Negócio Tutores
    public void cadastrarTutor(String nome, String cpf, String telefone) throws AgendamentoException {
        if (tutorRepository.buscarPorCpf(cpf) != null) {
            throw new AgendamentoException("Erro: Já existe um tutor cadastrado com este CPF.");
        }

        Tutor novoTutor = new Tutor(nome, cpf, telefone);
        tutorRepository.salvar(novoTutor);
    }

    public Tutor buscarTutorPorCpf(String cpf) {
        return tutorRepository.buscarPorCpf(cpf);
    }

    public List<Tutor> listarTodosTutores() {
        return tutorRepository.listarTodos();
    }

    // Métodos de Negócio Animais
    public void cadastrarAnimal(String nomeAnimal, int idade, String tipoAnimal, String racaOuPelagem, String cpfTutor) throws AgendamentoException {
        Tutor tutor = tutorRepository.buscarPorCpf(cpfTutor);
        
        // Regra de Negócio
        if (tutor == null) {
            throw new AgendamentoException("Erro: O tutor com CPF " + cpfTutor + " não foi encontrado.");
        }

        Animal novoAnimal;
        if ("cachorro".equalsIgnoreCase(tipoAnimal)) {
            novoAnimal = new Cachorro(nomeAnimal, idade, tutor, racaOuPelagem);
        } else if ("gato".equalsIgnoreCase(tipoAnimal)) {
            novoAnimal = new Gato(nomeAnimal, idade, tutor, racaOuPelagem);
        } else {
            throw new AgendamentoException("Erro: Tipo de animal inválido. Use 'cachorro' ou 'gato'.");
        }

        //Aplicação de polimorfismo
        animalRepository.salvar(novoAnimal);
    }

    public List<Animal> listarTodosAnimais() {
        return animalRepository.listarTodos();
    }

    //Métodos de Negócio Consultas

    public void agendarConsulta(Animal animal, String descricao) {
        Consulta novaConsulta = new Consulta(animal, descricao);
        consultaRepository.salvar(novaConsulta);
    }

    public List<Consulta> listarTodasConsultas() {
        return consultaRepository.listarTodos();
    }
}