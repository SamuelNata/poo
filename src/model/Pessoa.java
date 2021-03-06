/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

import javax.persistence.MappedSuperclass;

/**
 *
 * @author itamir.filho
 */
@MappedSuperclass
public abstract class Pessoa implements GenericModel {
    private String nome;
    
    private String Cpf;
    
    private String endereco;
    
    private String telefone;

    public Pessoa(String nome, String cpf){
        this.Cpf  = cpf;
        this.nome =  nome;
    }

    public Pessoa(){
    	super();
    }

    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return Cpf;
	}

	public void setCpf(String cpf) {
		Cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.Cpf, other.Cpf)) {
            return false;
        }
        return true;
    }
  
    
}
