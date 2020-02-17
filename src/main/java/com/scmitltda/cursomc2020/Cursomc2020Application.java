package com.scmitltda.cursomc2020;

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
import com.scmitltda.cursomc2020.domain.Produto;
import com.scmitltda.cursomc2020.domain.enuns.TipoCliente;
import com.scmitltda.cursomc2020.repositories.CategoriaRepository;
import com.scmitltda.cursomc2020.repositories.CidadeRepository;
import com.scmitltda.cursomc2020.repositories.ClienteRepository;
import com.scmitltda.cursomc2020.repositories.EnderecoRepository;
import com.scmitltda.cursomc2020.repositories.EstadoRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(Cursomc2020Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 500.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1,c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
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
		
		categoriaRepository.saveAll(Arrays.asList(c1, c2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(e1, e2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
	}

}
