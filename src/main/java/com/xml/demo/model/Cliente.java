package com.xml.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data // gera aquele código getters, setters, hashCodeEquals...
@Entity // JPA NOTATION = ENTIDADE
public class Cliente {
	
	@Id // Javax persistence = Primary key (em OO)
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
	private Long id;
	
	@Column(nullable = false) // Nome = NOT NULL
	private String nome;
	
}
