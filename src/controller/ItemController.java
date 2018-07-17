/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Item;
import repositories.GenericRepository;

/**
 *
 * @author itamir.filho
 */
public class ItemController extends GenericController<Item>{
	
	public ItemController(GenericRepository<Item> repository) {
		super(repository);
	}
}