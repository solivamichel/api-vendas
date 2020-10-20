package com.github.solivamichel.service.impl;

import org.springframework.stereotype.Service;

import com.github.solivamichel.domain.repository.Pedidos;
import com.github.solivamichel.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {
	
	private Pedidos repository;

	public PedidoServiceImpl(Pedidos repository) {
		this.repository = repository;
	}
	
}
