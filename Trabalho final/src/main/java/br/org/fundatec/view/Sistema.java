package br.org.fundatec.view;

import br.org.fundatec.controller.VotacaoController;
import br.org.fundatec.exception.AplicacaoException;
import br.org.fundatec.model.Funcionario;
import br.org.fundatec.model.Restaurante;
import br.org.fundatec.model.Voto;
import br.org.fundatec.util.TecladoUtil;

import java.util.List;

public class Sistema {

    private static VotacaoController controller = new VotacaoController();
    private static boolean sair = false;

    public static void main(String[] args) {
        while (!sair) {
            menu();
            int opcao = TecladoUtil.lerInteiro("Informa uma Opcao:");
            executaAcao(opcao);
        }
    }


    private static void executaAcao(int opcao) {
        try {
            switch (opcao) {
                case 1:
                    inserirFuncionario();
                    break;
                case 2:
                    inserirRestaurante();
                    break;
                case 3:
                    listarFuncionarios();
                    break;
                case 4:
                    listarRestaurantes();
                    break;
                case 5:
                    inserirVoto();
                    break;
                case 6:
                    listarVotos();
                    break;
                case 7:
                    sair = true;
                    break;
                default:
                    System.out.println("Opcao invalida!!");
                    break;
            }
        } catch (AplicacaoException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void listarVotos() {
        try {
            int idRestaurante = TecladoUtil.lerInteiro("Informe o ID do restaurante:");
            List<Voto> votos = controller.listarVotos(idRestaurante);

            for (Voto voto : votos) {
                System.out.println("ID: " + voto.getId() + ", Data: " + voto.getData() + ", ID Funcionário: " + voto.getFuncionario().getId() + ", ID Restaurante: " + voto.getRestaurante().getId());
            }
        } catch (AplicacaoException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void inserirRestaurante() throws AplicacaoException{
        String nome = TecladoUtil.lerString("Informe o nome do restaurante:");

        controller.inserirRestaurante(nome);

        System.out.println("Restaurante inserido!");
    }

    private static void inserirVoto() throws AplicacaoException {
        String nome = TecladoUtil.lerString("Informe o nome do funcionario:");

        String nomeRestaurante = TecladoUtil.lerString("Informe o nome do restaurante: ");

        controller.votar(nome, nomeRestaurante);

        System.out.println("Voto inserido!");
    }

    private static void inserirFuncionario() throws AplicacaoException {
        String nome = TecladoUtil.lerString("Informe um nome:");

        controller.inserirFuncionario(nome);

        System.out.println("Funcionario inserido!");
    }

    private static void listarFuncionarios() {
        for (Funcionario funcionario : controller.listarFuncionario()) {
            System.out.println("ID: " + funcionario.getId() + ", Nome: " + funcionario.getNome());
        }
    }

    private static void listarRestaurantes() {
        for (Restaurante restaurante : controller.listarRestaurantes()) {
            System.out.println("ID: " + restaurante.getId() + ", Nome: " + restaurante.getNome());
        }
    }




    private static void menu() {
        System.out.println("________________________");
        System.out.println("(1) Cadastrar Funcionario");
        System.out.println("(2) Cadastrar Restaurante");
        System.out.println("(3) Listar Funcionarios");
        System.out.println("(4) Listar Restaurantes");
        System.out.println("(5) Votar");
        System.out.println("(6) Listar Votos");
        System.out.println("(7) Sair");
        System.out.println("________________________");
    }
}
