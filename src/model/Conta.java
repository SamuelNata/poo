/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author itamir.filho
 */
public class Conta extends GenericModel{
    
    private List<ItemConta> itens;
    
    private Garcon garcon;
    
    private Cliente cliente;
    
    private Mesa mesa;

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
   
}
