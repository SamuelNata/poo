/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Garcon;
import repositories.GenericRepository;

/**
 *
 * @author Samuel Natã de França Borges
 */
public class GarconController extends GenericController<Garcon>{
	
	public GarconController(GenericRepository<Garcon> repository) {
		super(repository);
	}
}
