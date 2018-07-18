/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.GenericModel;
import model.Item;

/**
 *
 * @author Samuel Natã de França Borges
 */
public abstract class GenericRepository <T>{
    
    private final List<T> itens = null;

    public GenericRepository() {
        //itens = new ArrayList<T>();
        
        Type tType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        String SQL = "CREATE TABLE " + tType.getTypeName().split("[.]")[1] + " (";
        System.out.println("Class: "+((Class)tType));
        System.out.println(((Class)tType).getFields().length);
        for(Field f : ((Class)tType).getFields()) {
        	System.out.println("field: "+f.getName());
        	if(f.getType().getClass().getSimpleName().toLowerCase()=="id" ) {
        		SQL += " id           SERIAL         PRIMARY KEY,";
        	} else if(f.getType()==Integer.class ) {
        		SQL += " " + f.getName() + "           INTEGER,";
        	} else if(f.getType()==Double.class || f.getType()==Float.class) {
         		SQL += " " + f.getName() + "           REAL,";
         	} else if(f.getType()==String.class) {
         		SQL += " " + f.getName() + "           VARCHAR(250),";
         	} else if(GenericModel.class.isAssignableFrom(f.getType())) {
         		SQL += " " + f.getName() + "_id           VARCHAR(250),";
         	}
        	
        	SQL = SQL.substring(0, SQL.length()-1) + ");";
        }
            
        try ( Connection conn = new ConnectionFactory().getConnection() ){
        	Statement stmt = conn.createStatement();
        	System.out.println("Execute: " + SQL);
            stmt.executeUpdate(SQL);            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void addItem(T item){ 
//        itens.add(item);
        
        Type tType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        String SQL = "INSERT INTO " + tType.getTypeName();
        String fieldNames = " (";
        String questMarks = " (";
        for(Field f : tType.getClass().getFields()) {
        	if(GenericModel.class.isAssignableFrom(f.getType())) {
        		fieldNames += f.getName() + "_id, ";
        	} else {
        		fieldNames += f.getName() + ", ";
        	}
        	questMarks += "?, ";
        }
        fieldNames = fieldNames.substring(0, fieldNames.length()-1) + ") ";
        questMarks = questMarks.substring(0, questMarks.length()-1) + ") ";
        SQL += fieldNames + " VALUES " + questMarks + ";";
        
        try ( Connection conn = new ConnectionFactory().getConnection() ){
        	PreparedStatement stmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        	
        	int idx = 1;
        	for(Field f : tType.getClass().getFields()) {
        		if(GenericModel.class.isAssignableFrom(f.getType())) {
        			stmt.setInt(idx++, ((GenericModel)f.get(null)).getId() );
        		} else {
        			String type = f.getType().getSimpleName();
        			type = type.equals("Integer") ? "Int" : type;
	            	stmt.getClass().getMethod("set"+type, Integer.class, f.getType()).invoke(stmt, idx++, f.get(null));
    			}
            }
        	System.out.println("Execute: " + SQL);
            int resultado = stmt.executeUpdate();
            if (resultado == 0) {
                throw new SQLException("Falha ao criar um item.");
            }
            
//            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    item.setId(generatedKeys.getInt(1));
//                }
//                else {
//                    throw new SQLException("Creating user failed, no ID obtained.");
//                }
//            }
            System.out.println("Item inserido com sucesso!");
        } catch (SQLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void removeItem(T item) {
        itens.remove(item);
    }
      
    public List<T> listar(){
    	 Type tType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
         String SQL = "SELECT * FROM " + tType.getTypeName() + ";";
         List<T> itens = new ArrayList<T>();
         
         System.out.println("Execute: " + SQL);
         try ( Connection conn = new ConnectionFactory().getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(SQL) ) {
           
        	 while (rs.next()) {
        		 T item;
				try {
					item = (T)tType.getClass().newInstance();
	        		 for(Field f : item.getClass().getFields()) {
	        			 String type = f.getType().getSimpleName();
	        			 type = type.equals("Integer") ? "Int" : type;
	        			 Object param = rs.getClass().getMethod("get"+type, String.class).invoke(rs, f.getName());
	        			 tType.getClass().getMethod("set"+f.getType().getSimpleName(), f.getType()).invoke(item, param);
	        		 }
	        		 itens.add((T)item);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
           }
           
         } catch (SQLException ex) {
        	 System.out.println(ex.getMessage());
         }
         
         
         
         
         return itens;
    }
    
    ///////////
    
    
    
//    public void removeItem(Item item){ 
//        String SQL = "DELETE FROM item WHERE id = ?";
//        
//        try ( Connection conn = db.conectar(); 
//              PreparedStatement stmt = conn.prepareStatement(SQL); ){
//            
//            stmt.setInt(1, item.getId());
//            
//            stmt.executeUpdate();
//            
//            System.out.println("Item removido com sucesso!");
//            
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }  
      
}
