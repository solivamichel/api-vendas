package com.github.solivamichel.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.solivamichel.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer> {
	
}
