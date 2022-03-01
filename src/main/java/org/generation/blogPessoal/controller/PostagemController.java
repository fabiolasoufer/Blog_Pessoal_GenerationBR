package org.generation.blogPessoal.controller;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
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

/**
 * English:
 * It is the layer responsible for handling all requests made outside the API, 
 * these requests are made through URL's following the HTTP protocol.
 * 
 * Português:
 * É a camada responsável por manipular todas as requisições feitas do lado de fora da API, 
 * essas requisições são feitas através de URL 's seguindo o protocolo HTTP.
 * 
 * @author Fabíola
 */
@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;

	/**
	 * English:
	 * Annotation used to map a GET request. The "findAll" method will be called and will return an 
	 * endpoint with the ability to fetch all posts.
	 *
	 * Português:
	 * Anotação utilizada para mapear uma requisição do tipo GET. O método "findAll" será chamado e 
	 * retornará um endpoint com a capacidade de trazer todas as postagens.
	 * 
	 *@param All
	 *@return
	 *@author Fabíola
	 *
	 */
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	/**
	 * English:
	 *Annotation used to map a GET request, through the "id" attribute.
	 *The "findById" method will be called and will return only a single post object 
	 *(According to the given ID), or a "notFound" if the given id does not exist.
	 *
	 *Português:
	 *Anotação utilizada para mapear uma requisição do tipo GET, através do atributo "id".
	 *O método "findById" será chamado e retornará apenas um único post (De acordo com o ID informado), 
	 *ou um "notFound" se o id informado não existir.
	 *
	 *@param id
	 *@return
	 *@author Fabíola
	 *
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build()); 
	}
	
	/**
	 * English:
	 * Annotation used to map a GET request. The "GetByTitulo" method will be called and will return an endpoint 
	 * with the function of bringing a single Post per title.
	 *
	 * Português:
	 * Anotação utilizada para mapear uma requisição do tipo GET. O método "GetByTitulo" será chamado e retornará 
	 * um endpoint com a função de trazer uma única Postagem por título.
	 * @param titulo
	 * @return
	 * @author Fabíola
	 */
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	/**
	 * English:
	 * Annotation used to map a POST request. 
	 * This method has the function of writing a new Post to the database. 
	 * When using Post, the data goes in the request body and not in the URI.
	 *
	 * Português:
	 * Anotação utilizada para mapear uma requisição do tipo POST. 
	 * Esse método tem a função de gravar uma nova Postagem no banco de dados. 
	 * Quando usamos Post, os dados vão no corpo da requisição e não na URI.
	 * @param postagem
	 * @return
	 * @author Fabíola
	 */
	@PostMapping
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));		
	}
	
	/**
	 * English:
	 * Annotation used to request that a resource be stored at the given URI. 
	 * This method is used to update data from a post.
	 *
	 * Português:
	 * Anotação utilizada para solicitar que um recurso seja guardado na URI fornecida. 
	 * Esse método é utilizado para atualizar dados de uma postagem. 
	 * @param postagem
	 * @return
	 * @author Fabíola
	 */
	@PutMapping
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	/**
	 * English:
	 * Annotation used to map DELETE type requests, through the "id" attribute.
	 * The "deleteById" method will be called and will return an endpoint with 
	 * the function of deleting a post from the database.
	 * 
	 * Português:
	 * Anotação utilizada para mapear solicitações do tipo DELETE, através do atributo "id".
	 * O método "deleteById" será chamado e retornará um endpoint com a função de 
	 * apagar uma postagem do banco de dados.
	 *
	 * @param id
	 * @author Fabíola
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id ) {
		repository.deleteById(id);
	}

}
