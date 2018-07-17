/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.ItemConta;
import repositories.GenericRepository;

/**
 *
 * @author Samuel Natã de França Borges
 */
public class ItemContaController extends GenericController<ItemConta>{
	
	public ItemContaController(GenericRepository<ItemConta> repository) {
		super(repository);
	}
}
