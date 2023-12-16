package br.org.fundatec.controller;

import br.org.fundatec.exception.AplicacaoException;
import br.org.fundatec.model.Funcionario;
import br.org.fundatec.model.Restaurante;
import br.org.fundatec.model.Voto;
import br.org.fundatec.repository.FuncionarioRepository;
import br.org.fundatec.repository.RestauranteRepository;
import br.org.fundatec.repository.VotoRepository;

import javax.persistence.PersistenceException;
import java.util.Calendar;
import java.util.List;

public class VotacaoController {

    private FuncionarioRepository funcionarioRepository;
    private RestauranteRepository restauranteRepository;
    private VotoRepository votoRepository;

    public VotacaoController() {
        this.funcionarioRepository = new FuncionarioRepository();
        this.restauranteRepository = new RestauranteRepository();
        this.votoRepository = new VotoRepository();
    }

    public void inserirRestaurante(String nome) throws AplicacaoException {
        try {
            Restaurante restaurante = restauranteRepository.buscarPorNome(nome);

            if (restaurante == null) {
                restaurante = new Restaurante(nome);
                restauranteRepository.inserir(restaurante);
            } else {
                System.out.println("Falha ao inserir Restaurante!");
            }

        } catch (PersistenceException e) {
            throw new AplicacaoException("Falha ao inserir Restaurante");
        }
    }

    public void inserirFuncionario(String nome) throws AplicacaoException {
        try {
            Funcionario funcionario = funcionarioRepository.buscarPorNome(nome);

            if (funcionario == null) {
                funcionario = new Funcionario(nome);
                funcionarioRepository.inserir(funcionario);
            } else {
                System.out.println("Falha ao inserir funcionario!");
            }

        } catch (PersistenceException e) {
            throw new AplicacaoException("Falha ao inserir Funcionario");
        }
    }

    public void votar(String nomeFuncionario, String nomeRestaurante) throws AplicacaoException {
        try {
            Funcionario funcionario = funcionarioRepository.buscarPorNome(nomeFuncionario);
            Restaurante restaurante = restauranteRepository.buscarPorNome(nomeRestaurante);

            if (funcionario == null) {
                throw new AplicacaoException("Funcionário não encontrado.");
            }

            if (restaurante == null) {
                throw new AplicacaoException("Restaurante não encontrado.");
            }

            Voto voto = new Voto(restaurante, Calendar.getInstance());

            List<Voto> votosFuncionario = funcionario.getVotos();
            for (Voto v : votosFuncionario) {
                if (v.getData().get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
                    throw new AplicacaoException("Funcionário já votou hoje.");
                }
            }

            voto.setFuncionario(funcionario);
            votoRepository.inserir(voto);
        } catch (PersistenceException e) {
            throw new AplicacaoException("Falha ao inserir Voto!");
        }
    }


    public List<Funcionario> listarFuncionario() {
        return funcionarioRepository.listarFuncionarios();
    }

    public List<Restaurante> listarRestaurantes() {
        return restauranteRepository.listarRestaurantes();
    }

    public List<Voto> listarVotos(int idRestaurante) throws AplicacaoException {
        return votoRepository.buscar();
    }

}
