/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Samuel Natã de França Borges
 */
public abstract class GenericRepository <T>{
    private EntityManager getEM() {
    	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "poo" );
    	return emfactory.createEntityManager( );
    }

    public GenericRepository() {}
    
    public void addItem(T item){ 
//        itens.add(item);
        EntityManager em = getEM();
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
        em.close();
    }
    
    public void removeItem(T item) {
    	EntityManager em = getEM();
        em.getTransaction().begin();
        em.remove(item);
        em.getTransaction().commit();
        em.close();
    }
      
    public List<T> listar(){
    	EntityManager em = getEM();
    	Type tType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    	Class c = (Class) tType;
    	return (List<T>) em.createQuery("SELECT e FROM " + tType.getTypeName().split("[.]")[1].toLowerCase() + " e", c).getResultList();
    }
    
    public Object getById(Integer id) {
    	EntityManager em = getEM();
    	Type tType = ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    	Class c = (Class) tType;
    	return em.find(c, id);
    }
    
    public T update(T item) {
    	EntityManager em = getEM();
    	return em.merge(item);
    }
}
