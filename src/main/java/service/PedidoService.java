package service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import dto.ItemPedidoDTO;
import dto.PedidoDTO;
import entities.ClientesEntity;
import entities.ItemPedidoEntity;
import entities.PedidoEntity;
import entities.ProdutoEntity;
import entities.StatusPedido;
import exception.RegraNegocioException;

public class PedidoService {
	private final PedidoEntity repository = new PedidoEntity();
    private final ClientesEntity clientesRepository = new ClientesEntity();
    private final ProdutoEntity produtosRepository = new ProdutoEntity();
    private final ItemPedidoEntity itemsPedidoRepository = new ItemPedidoEntity();

   // @Override
    @Transactional
    public PedidoEntity salvar( PedidoDTO dto ) {
        Integer idCliente = dto.getCliente();
        ClientesEntity cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        PedidoEntity pedido = new PedidoEntity();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedidoEntity> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    public Optional<PedidoEntity> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus( Integer id, StatusPedido statusPedido ) {
        repository
                .findById(id)
                .map( pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException() );
    }

    private List<ItemPedidoEntity> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
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
