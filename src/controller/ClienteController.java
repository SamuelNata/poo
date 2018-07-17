/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Cliente;
import repositories.GenericRepository;

/**
 *
 * @author Samuel Natã de França Borges
 */
public class ClienteController extends GenericController<Cliente>{

	public ClienteController(GenericRepository<Cliente> repository) {
		super(repository);
	}

}
