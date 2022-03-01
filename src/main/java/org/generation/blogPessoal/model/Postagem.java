package org.generation.blogPessoal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * English:
 * Class responsible for abstracting objects and tables in our database. 
 * Attributes used for Post: id, title, text, date. This class also has a relationship with the Theme model.
 * 
 * Português:
 * Classe responsável pela abstração de objetos e tabelas em nosso banco de dados. 
 * Atributos utilizados para Postagem: id, título, texto, data. 
 * Essa classe também possui relacionamento com a model Tema.
 * 
 * @author Fabíola
 */
@Entity
@Table(name = "postagem")
public class Postagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(min = 5, max = 100)
	private String titulo;
	
	@NotNull
	@Size(min = 5, max = 500)
	private String texto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new java.sql.Date(System.currentTimeMillis());
	
	/**
	 * English:
	 * Relationship with the Theme model, too many posts for one theme. 
	 * And JsonIgnoreProperties annotation to inhibit recursion.
	 * 
	 * Português:
	 * Relacionamento com a model Tema, muitas postagens para um tema. 
	 * E anotação JsonIgnoreProperties para inibir recursividade. 
	 * 
	 * @author Fabíola
	 */
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public Tema getTema() {
		return tema;
	}
	
	public void setTema(Tema tema) {
		this.tema = tema;
	}

}
