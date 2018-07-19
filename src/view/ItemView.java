/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import controller.ClienteController;
import controller.ControllerFactory;
import controller.GenericController;
import model.Cliente;
import model.GenericModel;
import repositories.ClienteRepository;
import repositories.RepositoryFactory;

/**
 *
 * @author itamir.filho
 */
public class ItemView {
	static ControllerFactory cf = new ControllerFactory();
	static RepositoryFactory rf = new RepositoryFactory();
	
    public static void main(String args[]) {
    	GenericController gc = new ClienteController(new ClienteRepository());
    	GenericModel model = new Cliente();
    	
    	String [] typeNames = {"Cliente", "Conta", "Garcon", "Item", "ItemConta", "Mesa",}; 
    	List<String> types = Arrays.asList(typeNames);
    	String typesMenu = 	"Selecione:\n";
    	Integer idx = 1;
    	for(String name : typeNames) {
    		typesMenu += idx.toString() + " - " + name + "\n";
    		idx++;
    	}
    	typesMenu += "0 - Sair\n";

    	String actionMenu = "Selecione:\n";
    	String [] actionNames = {"Adicionar", "Remover", "Listar"};
    	idx = 1;
    	for(String name : actionNames) {
    		actionMenu += idx.toString() + " - " + name + "\n";
    		idx++;
    	}
    	actionMenu += "0 - Voltar\n";
    	
    	Integer option;
		Integer action;
    	
    	while(true) {
    		try {	option = Integer.parseInt(JOptionPane.showInputDialog(typesMenu));	}
    		catch (Exception e) {	option = new Integer(0);	}
    		
    		if(option>=1 && option<=types.size()) {
    			option--;
    			try {
    				Class modelClass = Class.forName("model."+types.get(option));
					gc = cf.getController(modelClass);
	    			model = (GenericModel) modelClass.newInstance();
				} catch (Exception e) {	e.printStackTrace();	}
    		} else {
		    	System.exit(0);
    		}

    		try {	action = Integer.parseInt(JOptionPane.showInputDialog(actionMenu));	}
    		catch (Exception e) {	action = new Integer(0);	}
    		switch(action) {
    			case(1): insert(model, gc); break;
    			case(2): remove(model, gc); break;
    			case(3): listAll(model, gc); break;
    			case(4): update(model, gc); break;
    			default:
    		}
    	}
    }
    
    public static void insert(GenericModel model, GenericController gc) {
    	
    	for(Field field : model.getClass().getDeclaredFields()) {
    		if(field.getName()=="id") {continue;}
			
    		if(GenericModel.class.isAssignableFrom(field.getType())) {
				try {
					GenericController tempController = cf.getController(field.getType());
					String input = JOptionPane.showInputDialog(field.getName()+":\n"+tempController.listar());
					if(input==null) {return;}
					if(!input.equals("")) {
						Integer id = Integer.parseInt(input);
						field.setAccessible(true);
						System.out.println("Selected Item: " + tempController.getById(id));
						field.set(model, tempController.getById(id));
						System.out.println("Seted value: " + field.get(model));
					}
				} catch (Exception e) {	e.printStackTrace();	}
			} else if(	field.getType()==Integer.class ||
					 	field.getType()==Double.class ||
						field.getType()==Boolean.class ||
					 	field.getType()==Float.class ||
					 	field.getType()==String.class ) {
				String input = JOptionPane.showInputDialog(field.getName()+":\n");
				if(input==null) {return;}
				setPrimitiveType(field, model, input);
			}
		}
		gc.salvar(model);
    }
    
    public static void listAll(GenericModel model, GenericController gc) {
    	JOptionPane.showMessageDialog(null, "Lista:\n"+gc.listar());
    }
    
    public static void remove(GenericModel model, GenericController gc) {
		String input = JOptionPane.showInputDialog(null, model.getClass().getSimpleName()+":\n"+gc.listar(), model.getId());
		if(input==null) {return;}
		if(!input.equals("")) {
			Integer id = Integer.parseInt(input);
			gc.remover(gc.getById(id));
		}
    }
    
    public static void update(GenericModel model, GenericController gc) {
    	for(Field field : model.getClass().getDeclaredFields()) {
    		if(field.getName()=="id") {continue;}
			
    		if(GenericModel.class.isAssignableFrom(field.getType())) {
				try {
					GenericController tempController = cf.getController(field.getType());
					String input = JOptionPane.showInputDialog(null, field.getName()+":\n"+tempController.listar(), ((GenericModel)field.get(model)).getId());
					if(input==null) {return;}
					if(!input.equals("")) {
						Integer id = Integer.parseInt(input);
						field.setAccessible(true);
						System.out.println("Selected Item: " + tempController.getById(id));
						field.set(model, tempController.getById(id));
						System.out.println("Seted value: " + field.get(model));
					}
				} catch (Exception e) {	e.printStackTrace();	}
			} else if(	field.getType()==Integer.class ||
					 	field.getType()==Double.class ||
						field.getType()==Boolean.class ||
					 	field.getType()==Float.class ||
					 	field.getType()==String.class ) {
				String input;
				try {
					input = JOptionPane.showInputDialog(null, field.getName()+":\n", field.get(model));
					if(input==null) {return;}
					setPrimitiveType(field, model, input);
				} catch (IllegalArgumentException | IllegalAccessException e) {	e.printStackTrace();	}
			}
		}
    	
    	
    	gc.atualizar(model);
    }
    
    private static void setPrimitiveType(Field field, GenericModel model, String val) {
    	try {
			field.setAccessible(true);
			if(field.getType()==Integer.class) {
				Integer value = Integer.parseInt(val);
				field.set(model, value);
			} else if(field.getType()==Boolean.class) {
				Boolean value = Boolean.parseBoolean(val);
				field.set(model, value);
			} else if(field.getType()==Float.class) {
				Float value = Float.parseFloat(val);
				field.set(model, value);
			} else if(field.getType()==Double.class) {
				Double value = Double.parseDouble(val);
				field.set(model, value);
			} else {
				field.set(model, val);
			}
		} catch (Exception e) {	e.printStackTrace();	}
    }
    
}
