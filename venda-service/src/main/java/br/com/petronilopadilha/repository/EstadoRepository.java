package br.com.petronilopadilha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petronilopadilha.model.Estado;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
