package com.github.solivamichel.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.github.solivamichel.domain.entity.Cliente;
import com.github.solivamichel.domain.entity.ItemPedido;
import com.github.solivamichel.domain.entity.Pedido;
import com.github.solivamichel.domain.entity.Produto;
import com.github.solivamichel.domain.repository.Clientes;
import com.github.solivamichel.domain.repository.ItemsPedido;
import com.github.solivamichel.domain.repository.Pedidos;
import com.github.solivamichel.domain.repository.Produtos;
import com.github.solivamichel.exceptioon.RegraNegocioException;
import com.github.solivamichel.rest.dto.ItemPedidoDTO;
import com.github.solivamichel.rest.dto.PedidoDTO;
import com.github.solivamichel.service.PedidoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
	
	private final Pedidos repository;
	
	private final Clientes clientesRepository;
	
	private final Produtos produtosRepository;
	
	private final ItemsPedido itemsPedidoRepository;

	@Override
    @Transactional
    public Pedido salvar( PedidoDTO dto ) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }
	
	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));
                    
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}