package music.penguin.dto;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GenericDTO<T> {

	public void createDTO(T e) {
		Class<?> entityClass = e.getClass();
		Class<?> dtoClass = this.getClass();

		Method[] allMethods = entityClass.getDeclaredMethods();
		Map<String, Method> getMethods = new HashMap<String, Method>();
		Map<String, Method> setMethods = new HashMap<String, Method>();
		ArrayList<String> ignoreMethods = new ArrayList<String>();
		
		// http://stackoverflow.com/questions/1961350/problem-in-the-getdeclaredmethods-java
		// Check for methods from super class in allMethods (different signatures)
		
		// Get annotated attributes to be ignored
		Field[] dtoFields = dtoClass.getDeclaredFields();
		for (Field f : dtoFields) {
			if (f.getAnnotation(Ignore.class) != null) {
				String propName = f.getName();
				String attName = Character.toUpperCase(propName.charAt(0)) + propName.substring(1);
				ignoreMethods.add(attName);
			}
		}
		
		try {
			for (Method m : allMethods) {
				String attributeName = m.getName().substring(3);
				boolean isSet = m.getName().substring(0, 3).equals("set");
	
				if (!isSet) {
					if (!ignoreMethods.contains(attributeName)) {
						getMethods.put(attributeName,m);
					}
					continue;
				} else {
					Class<?>[] pType  = m.getParameterTypes();
					for (int i = 0; i < pType.length; i++) {
						if ( !isJavaTypeClass(pType[i]) &&  !ignoreMethods.contains(attributeName)) {
							throw new NotJavaTypeException("get"+attributeName+"() : Not a Java Type. Use @Ignore.");
						}
					}
					if (!ignoreMethods.contains(attributeName)) {
						setMethods.put(attributeName, dtoClass.getDeclaredMethod(m.getName(),m.getParameterTypes()));
					}
				}
			}
		} catch(NoSuchMethodException ex) {
			ex.printStackTrace();
		}

		for (Method m : setMethods.values()) {
			Method g = getMethods.get(m.getName().substring(3));
			try {
				Method dtoGetMethod = dtoClass.getDeclaredMethod("get"+m.getName().substring(3));
				if (dtoGetMethod.getAnnotation(Ignore.class) != null) {
					continue;
				}
				m.invoke(this,g.invoke(e));
			} catch(InvocationTargetException ex) {
				ex.printStackTrace();
			} catch(IllegalAccessException ex) {
				ex.printStackTrace();
			} catch(NoSuchMethodException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void createEntity(T entity) {
		Class<?> entityClass = entity.getClass();
		Class<?> dtoClass = this.getClass();

		Method[] allMethods = entityClass.getDeclaredMethods();
		Map<String, Method> getMethods = new HashMap<String, Method>();
		Map<String, Method> setMethods = new HashMap<String, Method>();
		for (Method m : allMethods) {
			String attributeName = m.getName().substring(3);
			boolean isSet = m.getName().substring(0, 3).equals("set");

			try {
				if (!isSet) {
					getMethods.put(attributeName,dtoClass.getDeclaredMethod(m.getName(),m.getParameterTypes()));
					continue;
				} else {
					setMethods.put(attributeName, m);
				}
			} catch(NoSuchMethodException ex) {

			}
		}

		for (Method m : setMethods.values()) {
			Method g = getMethods.get(m.getName().substring(3));
			try {
				m.invoke(entity,g.invoke(this));
			} catch(InvocationTargetException ex) {

			} catch(IllegalAccessException ex) {

			}
		}
	}
	
	private boolean isJavaTypeClass(Class<?> c) {
		if (c.equals(String.class)) {
			return true;
		}
		if (c.equals(Integer.class)) {
			return true;
		}
		if (c.equals(Long.class)) {
			return true;
		}
		if (c.equals(Date.class)) {
			return true;
		}
		if (c.equals(Boolean.class)) {
			return true;
		}
		if (c.equals(Enum.class)) {
			return true;
		}
		return false;
	}
}
