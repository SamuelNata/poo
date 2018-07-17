package controller;

import java.lang.reflect.InvocationTargetException;

import model.GenericModel;
import repositories.RepositoryFactory;
import repositories.GenericRepository;

public class ControllerFactory {

	public GenericController<GenericModel> getController(Class modelType){
		try {
			RepositoryFactory fr = new RepositoryFactory (); 
			GenericRepository<GenericModel> rep = fr.getRepository(modelType);
			
			//Class repositoryClass = Class.forName("repositories."+modelType.getSimpleName()+"Repository");
			Class controllerClass = Class.forName("controller."+modelType.getSimpleName()+"Controller");
			
			GenericController<GenericModel> result = (GenericController<GenericModel>) controllerClass.getDeclaredConstructor(GenericRepository.class).newInstance(rep);
			return result;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}
	
}
