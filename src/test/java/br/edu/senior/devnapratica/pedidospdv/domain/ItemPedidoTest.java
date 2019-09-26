package br.edu.senior.devnapratica.pedidospdv.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class ItemPedidoTest {

	@Test
	public void deveCalcularCorretamenteValorTotalDoItem() {
		Produto produto = new Produto();
		produto.setValor(12.0);
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(4.0);
		
		// "0.0": Compara a precisão decimal exata.
		assertEquals(48.0, itemPedido.getValor(), 0.0);
	}
	
	@Test
	public void deveCalcularCorretamenteValorTotalDoItemComCasasDecimais() {
		Produto produto = new Produto();
		produto.setValor(12.54323);
		
		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(2.45234);
		
		// Aceita variação de 0.001
		// Valor calculado: 30.760264658199997
		// Valores aceitos pelo teste: 30.7591 >= X <= 30.761
		assertEquals(30.76, itemPedido.getValor(), 0.001);
	}

}
