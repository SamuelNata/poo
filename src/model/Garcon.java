/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author itamir.filho
 */
@Entity(name="garcon")
public class Garcon extends Pessoa{
	@Id @GeneratedValue
	private Integer id;
	
    private String matricula;
    
    private Double salario;
    
    private String carteiraTrabalho;

    public Garcon() {
    	super();
    }

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getCarteiraTrabalho() {
		return carteiraTrabalho;
	}

	public void setCarteiraTrabalho(String carteiraTrabalho) {
		this.carteiraTrabalho = carteiraTrabalho;
	}
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", matricula=" + matricula + ", salario=" + salario + ", carteiraTrabalho="
				+ carteiraTrabalho + "}";
	}
	
	
}
