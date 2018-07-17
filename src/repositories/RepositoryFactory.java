package repositories;

import model.GenericModel;

public class RepositoryFactory {

	public GenericRepository<GenericModel> getRepository(Class modelType){
		try {
			Class repositoryClass = Class.forName("repositories."+modelType.getSimpleName()+"Repository");
			GenericRepository<GenericModel> result = (GenericRepository<GenericModel>) repositoryClass.newInstance();
			return result;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}
}
