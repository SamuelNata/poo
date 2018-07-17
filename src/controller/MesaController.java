/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Mesa;
import repositories.GenericRepository;

/**
 *
 * @author itamir.filho
 */
public class MesaController extends GenericController<Mesa>{
	
	public MesaController(GenericRepository<Mesa> repository) {
		super(repository);
	}
}