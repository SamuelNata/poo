/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samuel Natã de França Borges
 */
public abstract class GenericRepository <T>{
    
    private final List<T> itens;

    public GenericRepository() {
        itens = new ArrayList<T>();
    }
    
    public void addItem(T item){ 
        itens.add(item);
    }
    
    public void removeItem(T item) {
        itens.remove(item);
    }
      
    public List<T> listar(){
        return itens;
    }
}
