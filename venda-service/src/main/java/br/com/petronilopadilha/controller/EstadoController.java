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
import br.com.petronilopadilha.model.Estado;
import br.com.petronilopadilha.repository.EstadoRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v2")
public class EstadoController {
	@Autowired
	private EstadoRepository estadoRepository;

	/**
	 * CONSULTA TODOS OS ESTADOS CADASTRADOS
	 * 
	 * @return
	 */
	@GetMapping("/estado")
	public List<Estado> getAllEstados() {
		return estadoRepository.findAll();
	}

	/**
	 * BUSCAR UMA ESTADO PELO ID
	 * 
	 * @param id
	 * @return
	 */

	@GetMapping("/estado/{id}")
	public ResponseEntity<Estado> getEmployeeById(@PathVariable(value = "id") Long estadoId)
			throws ResourceNotFoundException {
		Estado Estado = estadoRepository.findById(estadoId)
				.orElseThrow(() -> new ResourceNotFoundException("STATE not found for this id :: " + estadoId));
		return ResponseEntity.ok().body(Estado);
	}

	/**
	 * CRIA UM NOVO REGISTROS DE ESTADO
	 * 
	 * @param Estado
	 * @return
	 */
	@PostMapping("/estado")
	public Estado createEstado(@Valid @RequestBody Estado Estado) {
		return estadoRepository.save(Estado);
	}

	/**
	 * ATUALIZA/ALTERA UM REGISTROS DE ESTADO
	 * 
	 * @param ID
	 * @return
	 */
	@PutMapping("/estado/{id}")
	public ResponseEntity<Estado> updateEstado(@PathVariable(value = "id") Long estadoId,
			@Valid @RequestBody Estado estadoDetails) throws ResourceNotFoundException {
		Estado estado = estadoRepository.findById(estadoId)
				.orElseThrow(() -> new ResourceNotFoundException("Estado not found for this id :: " + estadoId));

		estado.setEstadoSigla(estadoDetails.getEstadoSigla());
		estado.setRegiaoSigla(estadoDetails.getRegiaoSigla());
		estado.setMunicipio(estadoDetails.getMunicipio());

		final Estado updateEstado = estadoRepository.save(estado);
		return ResponseEntity.ok(updateEstado);
	}

	/**
	 * EXCLUI UM REGISTROS DE ESTADO
	 * 
	 * @param ID
	 * @return
	 */
	@DeleteMapping("/estado/{id}")
	public Map<String, Boolean> deleteEstado(@PathVariable(value = "id") Long estadoId)
			throws ResourceNotFoundException {
		Estado estado = estadoRepository.findById(estadoId)
				.orElseThrow(() -> new ResourceNotFoundException("Estado not found for this id :: " + estadoId));

		estadoRepository.delete(estado);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
