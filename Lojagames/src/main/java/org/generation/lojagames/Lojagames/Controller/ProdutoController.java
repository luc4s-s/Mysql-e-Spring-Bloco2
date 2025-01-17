package org.generation.lojagames.Lojagames.Controller;

import java.util.List;

import org.generation.lojagames.Lojagames.Repository.ProdutoRepository;
import org.generation.lojagames.Lojagames.model.ProdutoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
@CrossOrigin(origins = "*" ,allowedHeaders = "*")
@RequestMapping("/produto")

public class ProdutoController {

	
	
	@Autowired
	private ProdutoRepository repository;

	@GetMapping
	public ResponseEntity<List<ProdutoModel>> getAll() {
	 return ResponseEntity.ok(repository.findAll());

	} 

	@GetMapping("/{id}")
	   public ResponseEntity<ProdutoModel> getByIdEntity(@PathVariable long id){
	   return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	
  }

	@GetMapping("/descricaoCategoria/{descricaoCategoria}")
	public ResponseEntity<List<ProdutoModel>>getBydescricaoCategoriaEntity(@PathVariable String descricaoCategoria){
		return ResponseEntity.ok(repository.findAllByDescricaoCategoriaContainingIgnoreCase(descricaoCategoria));
	}

	@PostMapping
	public ResponseEntity<ProdutoModel> post (@RequestBody ProdutoModel produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}

	@PutMapping
	public ResponseEntity<ProdutoModel> put (@RequestBody ProdutoModel produto){
		return ResponseEntity.ok(repository.save(produto));

	}
 
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}
}