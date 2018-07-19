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
@Entity(name="cliente")
public class Cliente extends Pessoa {
	@Id @GeneratedValue
	private Integer id;
	
    private String cartaoFidelidade;
    
    private String credito;
    
    public Cliente() {
    	super();
    }

	public String getCartaoFidelidade() {
		return cartaoFidelidade;
	}

	public void setCartaoFidelidade(String cartaoFidelidade) {
		this.cartaoFidelidade = cartaoFidelidade;
	}

	public String getCredito() {
		return credito;
	}

	public void setCredito(String credito) {
		this.credito = credito;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", cartaoFidelidade=" + cartaoFidelidade + ", credito=" + credito + "}";
	}
    
}
