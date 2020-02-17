package com.scmitltda.cursomc2020;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.scmitltda.cursomc2020.domain.Categoria;
import com.scmitltda.cursomc2020.domain.Cidade;
import com.scmitltda.cursomc2020.domain.Cliente;
import com.scmitltda.cursomc2020.domain.Endereco;
import com.scmitltda.cursomc2020.domain.Estado;
import com.scmitltda.cursomc2020.domain.Pagamento;
import com.scmitltda.cursomc2020.domain.PagamentoComBoleto;
import com.scmitltda.cursomc2020.domain.PagamentoComCartao;
import com.scmitltda.cursomc2020.domain.Pedido;
import com.scmitltda.cursomc2020.domain.Produto;
import com.scmitltda.cursomc2020.domain.enuns.EstadoPagamento;
import com.scmitltda.cursomc2020.domain.enuns.TipoCliente;
import com.scmitltda.cursomc2020.repositories.CategoriaRepository;
import com.scmitltda.cursomc2020.repositories.CidadeRepository;
import com.scmitltda.cursomc2020.repositories.ClienteRepository;
import com.scmitltda.cursomc2020.repositories.EnderecoRepository;
import com.scmitltda.cursomc2020.repositories.EstadoRepository;
import com.scmitltda.cursomc2020.repositories.PagamentoRepository;
import com.scmitltda.cursomc2020.repositories.PedidoRepository;
import com.scmitltda.cursomc2020.repositories.ProdutoRepository;

@SpringBootApplication
public class Cursomc2020Application implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(Cursomc2020Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 500.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado e1 = new Estado(null, "Minas Gerais");
		Estado e2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlandia", e1);
		Cidade cid2 = new Cidade(null, "São Paulo", e2);
		Cidade cid3 = new Cidade(null, "Campinas", e2);
		
		e1.getCidades().addAll(Arrays.asList(cid1));
		e2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "71863289301", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("977654532", "26428943"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "23", "345", "Jardim", "23345080", cli1, cid1);
		Endereco end2 = new Endereco(null, "Av Matos", "233", "455", "Pinheiros", "23234080", cli1, cid2);
		
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, 
				sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
	
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
				
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}

}
