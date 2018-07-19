/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author itamir.filho
 */
@Entity(name="conta")
public class Conta implements GenericModel {
	@Id @GeneratedValue
	private Integer id;
	
	@OneToMany(cascade = CascadeType.ALL)
    private List<ItemConta> itens;
    
    @ManyToOne
    private Garcon garcon;
    
    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    private Mesa mesa;

    public Conta() {
    	super();
    }
    
    public List<ItemConta> getItens() {
        return itens;
    }

    public void setItens(List<ItemConta> itens) {
        this.itens = itens;
    }

    public Garcon getGarcon() {
        return garcon;
    }

    public void setGarcon(Garcon garcon) {
        this.garcon = garcon;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
   
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
