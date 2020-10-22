package com.github.solivamichel.service;

import com.github.solivamichel.domain.entity.Pedido;
import com.github.solivamichel.rest.dto.PedidoDTO;

public interface PedidoService {
	
	Pedido salvar(PedidoDTO dto);
}
