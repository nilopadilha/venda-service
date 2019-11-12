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
import br.com.petronilopadilha.model.User;
import br.com.petronilopadilha.repository.UserRepository;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired	
	private UserRepository userRepository;

	/**
	 * CONSULTA TODOS OS USUÁRIOS CADASTRADOS
	 * 
	 * @return
	 */
	@GetMapping("/user")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	/**
	 * BUSCAR UMA USUÁRIO PELO ID
	 * 
	 * @param id
	 * @return
	 */

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
			throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		return ResponseEntity.ok().body(user);
	}

	/**
	 * CRIA UM NOVO REGISTROS DE USUÁRIO
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/user")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	/**
	 * ATUALIZA/ALTERA UM REGISTROS DE USUÁRIO
	 * 
	 * @param ID
	 * @return
	 */
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		user.setEmail(userDetails.getEmail());
		user.setLastName(userDetails.getLastName());
		user.setFirstName(user.getFirstName());
		final User updateUser = userRepository.save(user);
		return ResponseEntity.ok(updateUser);
	}

	/**
	 * EXCLUI UM REGISTROS DE USUÁRIO
	 * 
	 * @param ID
	 * @return
	 */
	@DeleteMapping("/user/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
