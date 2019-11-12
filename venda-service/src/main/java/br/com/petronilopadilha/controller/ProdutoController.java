package br.com.petronilopadilha.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.petronilopadilha.exception.ResourceNotFoundException;
import br.com.petronilopadilha.model.Produto;
import br.com.petronilopadilha.repository.ProdutoRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v3")
public class ProdutoController {
	@Autowired	
	private ProdutoRepository produtoRepository;

	/**
	 * CONSULTA TODOS OS PRODUTOS CADASTRADOS
	 * 
	 * @return
	 */
	@GetMapping("/produto")
	public List<Produto> getAllprodutos() {
		return produtoRepository.findAll();
	}

	/**
	 * BUSCAR UMA PRODUTO PELO ID
	 * 
	 * @param id
	 * @return
	 */

	@GetMapping("/produto/{id}")
	public ResponseEntity<Produto> getProdutoById(@PathVariable(value = "id") Long produtoId)
			throws ResourceNotFoundException {
		Produto produto = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + produtoId));
		return ResponseEntity.ok().body(produto);
	}

	/**
	 * CRIA UM NOVO REGISTROS DE PRODUTO
	 * 
	 * @param produto
	 * @return
	 */
	@PostMapping("/produto")
	public Produto createproduto(@Valid @RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}

	/**
	 * ATUALIZA/ALTERA UM REGISTROS DE PRODUTO
	 * 
	 * @param ID
	 * @return
	 */
	@PutMapping("/produto/{id}")
	public ResponseEntity<Produto> updateproduto(@PathVariable(value = "id") Long produtoId,
			@Valid @RequestBody Produto produtoDetails) throws ResourceNotFoundException {
		Produto prod = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + produtoId));

		prod.setDescricao(produtoDetails.getDescricao());
		prod.setDataColeta(produtoDetails.getDataColeta());
		prod.setValorCompra(produtoDetails.getValorCompra());
		prod.setValorVenda(produtoDetails.getValorVenda());
		prod.setUnidadeMedida(produtoDetails.getUnidadeMedida());
		
		final Produto updateproduto = produtoRepository.save(prod);
		return ResponseEntity.ok(updateproduto);
	}

	/**
	 * EXCLUI UM REGISTROS DE PRODUTO
	 * 
	 * @param ID
	 * @return
	 */
	@DeleteMapping("/produto/{id}")
	public Map<String, Boolean> deleteproduto(@PathVariable(value = "id") Long produtoId) throws ResourceNotFoundException {
		Produto prod = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + produtoId));

		produtoRepository.delete(prod);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
