/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Conta;
import repositories.GenericRepository;

/**
 *
 * @author itamir.filho
 */
public class ContaController extends GenericController<Conta>{
	
	public ContaController(GenericRepository<Conta> repository) {
		super(repository);
	}
}