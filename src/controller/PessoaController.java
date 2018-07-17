/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Pessoa;
import repositories.GenericRepository;

/**
 *
 * @author itamir.filho
 */
public class PessoaController extends GenericController<Pessoa>{
	
	public PessoaController(GenericRepository<Pessoa> repository) {
		super(repository);
	}
}