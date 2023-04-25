package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Membro;
import com.example.demo.repository.MembroRepository;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;

@Service
public class MembroService {
	
	@Autowired
	private MembroRepository membroRepository;
	
	public Membro calcolaDataRinnovo(Membro membro) {
				
		LocalDate dataRinnovo = membro.getDataIscrizione().plusDays(28);
        membro.setDataRinnovo(dataRinnovo);
        
        // Salviamo il membro nel repository
        return membroRepository.save(membro);
    }
	
  /*  public Optional<Membro> getMembroById(Long id) {
        Optional<Membro> membro = membroRepository.findById(id);
        if (membro.isPresent()) {
            return Optional.of(calcolaDataRinnovo(membro.get()));
        } else {
            return Optional.empty();
        }
    }*/
    
    public MembroService(MembroRepository membroRepository) {
    	this.membroRepository = membroRepository;
    }
    
    public List<Membro> findAllMembri(String filterText){
    	if(filterText == null || filterText.isEmpty()) {
    		return membroRepository.findAll();
    	}else {
    		return membroRepository.search(filterText);
    	}
    }
    
    public long countMembri() {
    	return membroRepository.count();
    }
    
    /*public void deleteMembro(Membro membro) {
    	if(membro == null) {
    		System.err.println("Membro is null");
    		return;
    	}
    	
    	membroRepository.save(membro);
    }*/

	public void saveMembro(Membro membro) {
		 if (membro == null) { 
	            System.err.println("Membro is null. Are you sure you have connected your form to the application?");
	            return;
	        }
	        membroRepository.save(membro);
	    }

	public void deleteContact(Membro membro) {
		membroRepository.delete(membro);
	}
    
    
}
