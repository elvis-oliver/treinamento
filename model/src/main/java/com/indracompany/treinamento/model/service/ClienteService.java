package com.indracompany.treinamento.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.repository.ClienteRepository;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository> {

	private static final boolean EmailEhValido = false;
	private static final boolean NomeEhValido = false;
	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente buscarClientePorCpf(String cpf) {
		if (!cpfEhValido(cpf)) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO);
		}
		return clienteRepository.findByCpf(cpf);
	}

	private boolean cpfEhValido(String cpf) {

		return false;
	}

	public Cliente buscarClientePorNome(String nome) {
		if (!(NomeEhValido)) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_NOME_INVÁLIDO);
		}
		return clienteRepository.findByNome(nome);
	}
	
	
	
	public Cliente buscarClientePorEmail(String email) {
		if (!(EmailEhValido)) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_EMAIL_INVALIDO);
		}
		return clienteRepository.findByNome(email);
	}
	
	
	
	
}
