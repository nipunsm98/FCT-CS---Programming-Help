package lk.ac.kln.fct.learn.reflection.main;

import java.lang.reflect.InvocationTargetException;
import lk.ac.kln.fct.learn.reflection.shapes.Rectangle;
import java.lang.reflect.Field;

public class Main {
	public static void main(String[] args) {
		Rectangle rectangle = null;				
		try {
			Class<?> clazz = Rectangle.class;
			rectangle = (Rectangle) clazz.getConstructor().newInstance();
			Field field = rectangle.getClass().getSuperclass().getDeclaredField("name");
			field.setAccessible(true);
			field.set(rectangle, "Rectangle");
		} catch (Exception e) {			
			e.printStackTrace();
		}
		System.out.println("Shape name: "+ rectangle.getName());
	}
}
