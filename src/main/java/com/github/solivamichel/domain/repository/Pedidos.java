package com.github.solivamichel.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.solivamichel.domain.entity.Cliente;
import com.github.solivamichel.domain.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer>{
	
	List<Pedido> findByCliente( Cliente cliente );
}
