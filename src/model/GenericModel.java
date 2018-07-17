package model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public abstract class GenericModel {
	private int id;
	
	public Object get(Field field) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		return this.getClass().getMethod("get"+field.getName()).invoke(this);
	}
	
	public void set(Field field, Object val)  throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Object value;
		if(field.getType()==Integer.class) {
			value = Integer.getInteger((String)val);
		} else if(field.getType()==Boolean.class) {
			value = Boolean.valueOf((String)val);
		} else if(field.getType()==Float.class) {
			value = Float.valueOf((String)val);
		} else if(field.getType()==Double.class) {
			value = Double.valueOf((String)val);
		} else {
			value = val;
		}
		
		String methodName = "set"+field.getName().substring(0, 1).toUpperCase();
		methodName += field.getName().substring(1);
		System.out.println("field class: " +field.getType());
		this.getClass().getMethod(methodName, field.getType()).invoke(this, value);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
