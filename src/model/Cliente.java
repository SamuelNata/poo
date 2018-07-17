/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author itamir.filho
 */
public class Cliente extends Pessoa {
    
    private String cartaoFidelidade;
    
    private String credito;
    
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
    
    
    
}
