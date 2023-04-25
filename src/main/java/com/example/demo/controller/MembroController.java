/*package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Membro;
import com.example.demo.repository.MembroRepository;
import com.example.demo.service.MembroService;

@Controller
@RequestMapping(value= "membri")
public class MembroController {

	@Autowired
	private MembroService membroService;
	
	@Autowired
	private MembroRepository membroRepository;
	
	@GetMapping
	public @ResponseBody List<Membro> findAll() {
	    List<Membro> membri = membroRepository.findAll();
	    membri.forEach(membro -> membroService.calcolaDataRinnovo(membro));
	    return membri;
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Membro> getMembroById(@PathVariable(value = "id") Long id) {
	    Optional<Membro> membro = membroService.getMembroById(id);
	    if (membro.isPresent()) {
	        return ResponseEntity.ok(membro.get());
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	@PostMapping
	public @ResponseBody ResponseEntity<Membro> create(@RequestBody Membro membro) {
		Membro newMembro = membroService.calcolaDataRinnovo(membro);
	    return ResponseEntity.ok(newMembro);
	}
	
	@PutMapping("{id}")
	public @ResponseBody Membro update(@PathVariable("id") Long id, @RequestBody Membro membro ) throws Exception {
		Optional<Membro> dbOptionalMembro = membroRepository.findById(id);
		
		if(dbOptionalMembro.isPresent()) {
			Membro dbMembro = dbOptionalMembro.get();
			dbMembro.setNome(membro.getNome());
			dbMembro.setCognome(membro.getCognome());
			dbMembro.setDataIscrizione(membro.getDataIscrizione());
	        dbMembro = membroService.calcolaDataRinnovo(dbMembro); 
		
			membroRepository.save(dbMembro);
			
			return dbMembro;
		}
		
		throw new Exception();
	}
	
	@DeleteMapping("{id}")
	public  @ResponseBody String delete(@PathVariable("id") Long id) {
		membroRepository.deleteById(id);
		return "Eliminato con successo";
	}
}
*/