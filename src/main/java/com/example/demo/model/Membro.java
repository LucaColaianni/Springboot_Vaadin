package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "membri")
public class Membro {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;
    
    @Column(name = "cognome")
    private String cognome;
    
    @Column(name = "data_iscrizione")
    private LocalDate dataIscrizione;
    
    @Transient
    private LocalDate dataRinnovo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataIscrizione() {
		return dataIscrizione;
	}

	public void setDataIscrizione(LocalDate dataIscrizione) {
		this.dataIscrizione = dataIscrizione;
	}

	public Membro() {
		super();
	}

	public LocalDate getDataRinnovo() {
		return dataRinnovo;
	}

	public void setDataRinnovo(LocalDate dataRinnovo) {
		this.dataRinnovo = dataRinnovo;
	}



    
    
}