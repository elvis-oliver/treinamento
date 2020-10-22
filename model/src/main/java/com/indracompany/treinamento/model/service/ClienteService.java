package com.indracompany.treinamento.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indracompany.treinamento.exception.AplicacaoException;
import com.indracompany.treinamento.exception.ExceptionValidacoes;
import com.indracompany.treinamento.model.entity.Cliente;
import com.indracompany.treinamento.model.repository.ClienteRepository;
import com.indracompany.treinamento.util.CpfUtil;

@Service
public class ClienteService extends GenericCrudService<Cliente, Long, ClienteRepository> {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscarClientePorId(Long id) {
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		return cliente.orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ERRO_OBJETO_NAO_ENCONTRADO));
		
	}

	public Cliente buscarClientePorCpf(String cpf) {
		if (!cpfEhValido(cpf)) {
			throw new AplicacaoException(ExceptionValidacoes.ERRO_CPF_INVALIDO);
		}
		return clienteRepository.findByCpf(cpf);
	}

	public Cliente buscarClientePorNome(String nome) {

		Optional<Cliente> cliente = clienteRepository.findByNome(nome);

		return cliente.orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ERRO_NOME_NAO_ENCONTRADO));

	}

	public Cliente buscarClientePorEmail(String email) {

		Optional<Cliente> cliente = clienteRepository.findByEmail(email);

		return cliente.orElseThrow(() -> new AplicacaoException(ExceptionValidacoes.ERRO_EMAIL_NAO_ENCONTRADO));
	}

	private boolean cpfEhValido(String cpf) {
		return CpfUtil.validaCPF(cpf);
	}
	
	@Transactional
	public Cliente inserirCliente(Cliente cliente) {
		
		Cliente novo = clienteRepository.save(cliente);
		return novo;
	}
	
	public Cliente atualizarCliente(Cliente cliente) {
		
		Cliente cli = buscarClientePorId(cliente.getId());
		
		return clienteRepository.save(cli);
	}

	public void deletarCliente(Long id) {
		
		buscarClientePorId(id);
		
		clienteRepository.deleteById(id);
	}
}
