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
import java.lang.reflect.Type;
import java.util.ArrayList;
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
		    	System.exit(0);
    		}
    		create(model, gc);
    	}
    }
    
    public static GenericModel create(GenericModel model, GenericController gc) {
    	
    	for(Field field : model.getClass().getDeclaredFields()) {
			if(GenericModel.class.isAssignableFrom(field.getType())) {
				//System.out.println("Field is a generic model");
				try {
					GenericController tempController = cf.getController(field.getType());
					model.set(field, gc.getById(Integer.parseInt(JOptionPane.showInputDialog(field.getName()+":\n"+tempController.listar()))));
					gc.salvar(model);
				} catch (HeadlessException | IllegalAccessException | IllegalArgumentException | InvocationTargetException 
						| NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			} else if(List.class.isAssignableFrom(field.getType())) {
				try {
					Type mySuperclass = field.getType().getGenericSuperclass();
					Type tType = ((ParameterizedType)mySuperclass).getActualTypeArguments()[0];
					GenericController tempController  = cf.getController(tType.getClass());
					//GenericController tempController = (GenericController) Class.forName(tType.getTypeName()+"Controller").newInstance();
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
					gc.salvar(model);
				} catch (HeadlessException | IllegalAccessException | IllegalArgumentException | InvocationTargetException 
						| NoSuchMethodException | SecurityException | InstantiationException e) {
					e.printStackTrace();
				}
			} else {
				//System.out.println("Field is NOT a generic model");
				try {
					model.set(field, JOptionPane.showInputDialog(field.getName()+": "));
					gc.salvar(model);
				} catch (HeadlessException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
    	return null;
    }
    
}
