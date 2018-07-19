/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.GenericModel;
import repositories.GenericRepository;

/**
 *
 * @author Samuel Natã
 */
public abstract class GenericController <T extends GenericModel>{
    
    private GenericRepository<T> repository;
    
    public GenericController(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public void salvar(T it) {
        repository.addItem(it);
    }
    
    public void remover(T it) {
        repository.removeItem(it);
    }
    
    public void atualizar(T item){
        repository.update(item);
    }
    
    public T getById(Integer id) {
    	return (T) repository.getById(id);
    }
    
    public String listar() {
        String lista = "Lista:\n";
        for (T item : repository.listar()) {
            lista += item.getId() + " - " + item + "\n";
        }
        return lista;
    }
}
