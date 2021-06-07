package controller;

import java.util.List;

import org.hibernate.criterion.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import entities.ClientesEntity;

@RestController
@RequestMapping("/api/clientes")
@Api("Api Clientes")
public class ClienteController {
	private ClientesEntity clientes;

	public ClienteController(ClientesEntity clientes) {
		this.clientes = clientes;
	}

	@GetMapping("{id}")
	@ApiOperation("Obter detalhes de um cliente")
	@ApiResponses({ @ApiResponse(code = 200, message = "Cliente encontrado"),
			@ApiResponse(code = 404, message = "Cliente não encontrado para o ID informado") })
	public Cliente getClienteById(@PathVariable @ApiParam("Id do cliente") Integer id) {
		return clientes.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Salva um novo cliente")
	@ApiResponses({ @ApiResponse(code = 201, message = "Cliente salvo com sucesso"),
			@ApiResponse(code = 400, message = "Erro de validação") })
	public Cliente save(@RequestBody @Valid Cliente cliente) {
		return clientes.save(cliente);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		clientes.findById(id).map(cliente -> {
			clientes.delete(cliente);
			return cliente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
		ClientesEntity.findById(id).map(clienteExistente -> {
			cliente.setId(clienteExistente.getId());
			clientes.save(cliente);
			return clienteExistente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}

	@GetMapping
	public List<ClientesEntity> find(ClientesEntity filtro) {
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		Example example = Example.of(filtro, matcher);
		return ClientesEntity.findById(example);
	}
}
