package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Membro;

public interface MembroRepository extends JpaRepository<Membro, Long> {

	 @Query("SELECT m FROM Membro m "
	 		+ "WHERE LOWER(m.nome)"
	 		+ " LIKE LOWER(CONCAT('%', :filterText, '%'))"
	 		+ " OR LOWER(m.cognome)"
	 		+ " LIKE LOWER(CONCAT('%', :filterText, '%'))")
	  List<Membro> search(@Param("filterText") String filterText);

}