/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.HeadlessException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JOptionPane;

import controller.ClienteController;
import controller.ContaController;
import controller.GarconController;
import controller.GenericController;
import controller.ItemController;
import model.Cliente;
import model.Conta;
import model.Garcon;
import model.GenericModel;
import repositories.ClienteRepository;
import repositories.ContaRepository;
import repositories.GarconRepository;
import repositories.ItemRepository;

/**
 *
 * @author itamir.filho
 */
public class ItemView {
    
    private ItemController itemController;
    
    private ItemRepository itemRepository;
    
    public ItemView() {
        itemRepository = new ItemRepository();
        itemController = new ItemController(itemRepository);
    }

    public ItemController getItemController() {
        return itemController;
    }

    public void setItemController(ItemController itemController) {
        this.itemController = itemController;
    }

    public ItemRepository getItemRepository() {
        return itemRepository;
    }

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
    public static void main(String args[]) {
    	GenericController gc = new ClienteController(new ClienteRepository());
    	GenericModel model;
    	String menu = 	"Selecione:\n" +
    					"1 - Cliente\n" +
    					"2 - Conta\n" +
    					"3 - Garçon\n" +
    					"4 - Item\n" +
    					"5 - ItemConta\n" +
    					"6 - Mesa\n" +
    					"7 - Pessoa\n" +
    					"0 - Sair";
    	Integer option;
    	while(true) {
    		try {
    			String s = JOptionPane.showInputDialog(menu);
    			option = Integer.parseInt(s);
    		}
    		catch (Exception e) {
    			option = new Integer(0);
			}
    		
    		model = new Cliente();
    		
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
	    			break;
	    		case 5:
//	    			"2 - Conta\n" +
//					"3 - Garçon\n" +
//					"4 - Item\n" +
//					"5 - ItemConta\n" +
//					"6 - Mesa\n" +
//					"7 - Pessoa";
	    			break;
	    		case 6:
	    			break;
	    		case 7:
	    			break;
	    		default:
	    			System.exit(0);
    		};
    		
    		for(Field field : model.getClass().getDeclaredFields()) {
				if(GenericModel.class.isAssignableFrom(field.getType())) {
					System.out.println("Field is a generic model");
					try {
						model.set(field, gc.getById(Integer.getInteger(JOptionPane.showInputDialog(field.getName()+":\n"))));
					} catch (HeadlessException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Field is NOT a generic model");
					try {
						model.set(field, JOptionPane.showInputDialog(field.getName()+": "));
					} catch (HeadlessException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException | SecurityException e) {
						e.printStackTrace();
					}
				}
			}
    	}
    }
    
    
    
}
