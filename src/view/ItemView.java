/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.HeadlessException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import controller.ClienteController;
import controller.ContaController;
import controller.ControllerFactory;
import controller.GarconController;
import controller.GenericController;
import controller.ItemContaController;
import controller.ItemController;
import controller.MesaController;
import controller.PessoaController;
import model.Cliente;
import model.Conta;
import model.Garcon;
import model.GenericModel;
import model.Item;
import model.ItemConta;
import model.Mesa;
import model.Pessoa;
import repositories.ClienteRepository;
import repositories.ContaRepository;
import repositories.GarconRepository;
import repositories.ItemContaRepository;
import repositories.ItemRepository;
import repositories.MesaRepository;
import repositories.PessoaRepository;
import repositories.RepositoryFactory;

/**
 *
 * @author itamir.filho
 */
public class ItemView {
    public static void main(String args[]) {
    	ControllerFactory cf = new ControllerFactory();
    	RepositoryFactory rf = new RepositoryFactory();
    	
    	GenericController gc = new ClienteController(new ClienteRepository());
    	GenericModel model = new Cliente();
    	String menu = 	"Selecione:\n" +
    							"1 - Cliente\n" +
    							"2 - Conta\n" +
    							"3 - Garçon\n" +
    							"4 - Item\n" +
    							"5 - ItemConta\n" +
    							"6 - Mesa\n" +
    							"7 - Pessoa\n" +
    							"0 - Sair";
    	
    	String [] typeNames = {"Cliente", "Conta", "Garcon", "Item", "ItemConta", "Mesa", "Pessoa"}; 
    	List<String> types = Arrays.asList(typeNames);
    	Integer option;
    	
    	while(true) {
    		try {
    			String s = JOptionPane.showInputDialog(menu);
    			option = Integer.parseInt(s);
    		}
    		catch (Exception e) {
    			option = new Integer(0);
			}
    		
    		if(option>=1 && option<=types.size()) {
    			option--;
    			try {
    				Class modelClass = Class.forName("model."+types.get(option));
					gc = cf.getController(modelClass);
	    			model = (GenericModel) modelClass.newInstance();
				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException 
						| IllegalArgumentException | SecurityException e) {
					e.printStackTrace();
				}
    		} else {
	    		switch(option) {
		    		case 1:
		    			gc = new ClienteController(new ClienteRepository());
		    			model = new Cliente();
		    			break;
		    		case 2:
		    			gc = new ContaController(new ContaRepository());
		    			model = new Conta();
		    			break;
		    		case 3:
		    			gc = new GarconController(new GarconRepository());
		    			model = new Garcon();
		    			break;
		    		case 4:
		    			gc = new ItemController(new ItemRepository());
		    			model = new Item();
		    			break;
		    		case 5:
		    			gc = new ItemContaController(new ItemContaRepository());
		    			model = new ItemConta();
		    			break;
		    		case 6:
		    			gc = new MesaController(new MesaRepository());
		    			model = new Mesa();
		    			break;
		    		case 7:
			    		gc = new PessoaController(new PessoaRepository());
		    			model = new Pessoa();
		    			break;
		    		default:
		    			System.exit(0);
	    		};
    		}
    		create(model, gc);
    	}
    }
    
    public static GenericModel create(GenericModel model, GenericController gc) {
    	
    	for(Field field : model.getClass().getDeclaredFields()) {
			if(GenericModel.class.isAssignableFrom(field.getType())) {
				//System.out.println("Field is a generic model");
				try {
					GenericController tempController = (GenericController) Class.forName(field.getType()+"Controller").newInstance();
					model.set(field, gc.getById(Integer.parseInt(JOptionPane.showInputDialog(field.getName()+":\n"+tempController.listar()))));
				} catch (HeadlessException | IllegalAccessException | IllegalArgumentException | InvocationTargetException 
						| NoSuchMethodException | SecurityException | InstantiationException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			} else if(List.class.isAssignableFrom(field.getType())) {
				try {
					//Class<T> insideClass = (Class<T>) ((field.getType())getClass().getGenericSuperclass()).getActualTypeArguments()[0];
					GenericController tempController = (GenericController) Class.forName(field.getType()+"Controller").newInstance();
					Integer id=-1;
					ArrayList<GenericModel> list = new ArrayList<GenericModel>();
					GenericModel localModel = (GenericModel) field.getType().newInstance();
					do {
						localModel = gc.getById(Integer.parseInt(JOptionPane.showInputDialog(field.getName()+":\n 0 para encerrar.\n"+tempController.listar())));
						if(id>0) {
							list.add(localModel);
						}
					}while(id!=0);
					model.set(field, list);
				} catch (HeadlessException | IllegalAccessException | IllegalArgumentException | InvocationTargetException 
						| NoSuchMethodException | SecurityException | InstantiationException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				//System.out.println("Field is NOT a generic model");
				try {
					model.set(field, JOptionPane.showInputDialog(field.getName()+": "));
				} catch (HeadlessException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
    	return null;
    }
    
}
