package br.com.clinica.ui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.clinica.exceptions.AgendamentoException;
import br.com.clinica.model.Animal;
import br.com.clinica.model.Tutor;
import br.com.clinica.service.ClinicaService;

public class Main {

    private static ClinicaService service = new ClinicaService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("--- BEM-VINDO AO SISTEMA DE CLÍNICA VETERINÁRIA ---");
        
        while (true) {
            exibirMenu();
            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarTutor();
                        break;
                    case 2:
                        cadastrarAnimal();
                        break;
                    case 3:
                        listarTutores();
                        break;
                    case 4:
                        listarAnimais();
                        break;
                    case 9:
                        System.out.println("Saindo do sistema. Até logo!");
                        return;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Erro: Por favor, digite apenas números.");
                scanner.nextLine();
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Cadastrar Novo Tutor");
        System.out.println("2. Cadastrar Novo Animal");
        System.out.println("3. Listar Todos os Tutores");
        System.out.println("4. Listar Todos os Animais");
        System.out.println("9. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarTutor() {
        try {
            System.out.print("Digite o nome do tutor: ");
            String nome = scanner.nextLine();
            System.out.print("Digite o CPF do tutor: ");
            String cpf = scanner.nextLine();
            System.out.print("Digite o telefone do tutor: ");
            String telefone = scanner.nextLine();

            service.cadastrarTutor(nome, cpf, telefone);
            System.out.println("Tutor cadastrado com sucesso!");

        } catch (AgendamentoException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void cadastrarAnimal() {
        try {
            System.out.print("Digite o CPF do tutor deste animal: ");
            String cpfTutor = scanner.nextLine();
            
            if (service.buscarTutorPorCpf(cpfTutor) == null) {
                System.err.println("Erro: Nenhum tutor encontrado com este CPF. Cadastre o tutor primeiro.");
                return;
            }

            System.out.print("Digite o nome do animal: ");
            String nomeAnimal = scanner.nextLine();
            System.out.print("Digite a idade do animal: ");
            int idade = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Digite o tipo (cachorro ou gato): ");
            String tipo = scanner.nextLine();
            System.out.print("Digite a raça (se cachorro) ou pelagem (se gato): ");
            String racaOuPelagem = scanner.nextLine();

            service.cadastrarAnimal(nomeAnimal, idade, tipo, racaOuPelagem, cpfTutor);
            System.out.println("Animal cadastrado com sucesso!");

        } catch (AgendamentoException e) {
            System.err.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.err.println("Erro: A idade deve ser um número.");
            scanner.nextLine();
        }
    }

    private static void listarTutores() {
        List<Tutor> tutores = service.listarTodosTutores();
        if (tutores.isEmpty()) {
            System.out.println("Nenhum tutor cadastrado.");
            return;
        }

        System.out.println("\n--- LISTA DE TUTORES ---");
        for (Tutor tutor : tutores) {
            System.out.printf("Nome: %s | CPF: %s | Telefone: %s\n",
                    tutor.getNome(), tutor.getCpf(), tutor.getTelefone());
        }
    }

    private static void listarAnimais() {
        List<Animal> animais = service.listarTodosAnimais();
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
            return;
        }

        System.out.println("\n--- LISTA DE ANIMAIS ---");
        for (Animal animal : animais) {
            System.out.printf("Nome: %s | Idade: %d | Tutor: %s (%s) | Som: %s\n",
                    animal.getNome(),
                    animal.getIdade(),
                    animal.getTutor().getNome(),
                    animal.getTutor().getCpf(),
                    animal.emitirSom()
            );
        }
    }
}