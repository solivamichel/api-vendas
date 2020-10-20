package com.github.solivamichel.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.solivamichel.domain.entity.ItemPedido;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer> {

}
