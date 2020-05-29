package lk.ac.kln.fct.learn.reflection.main;

import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import lk.ac.kln.fct.learn.reflection.core.Shape;
import lk.ac.kln.fct.learn.reflection.shapes.Rectangle;
import lk.ac.kln.fct.learn.reflection.shapes.Square;
import lk.ac.kln.fct.learn.reflection.util.Dimension;

public class Main {
	private final static String COLOR = "Color";
	private final static String GET = "get";
	private final static String SET = "set";
	
	public static void main(String[] args) {
		Dimension dim1 = new Dimension(5,10);
		Dimension dim2 = new Dimension(15,15);
		
		Shape shape1 = createShape(Rectangle.class, dim1);
		Shape shape2 = createShape(Square.class, dim2);
		
		Color red = new Color(255,0,0);
		Color green = new Color(0, 255, 0);
		
		System.out.println(shape1.getName()+" : "+shape1.area() +" : "+ setColorAndGetColorName(shape1,COLOR,red));
		System.out.println(shape2.getName()+" : "+shape2.area()+" : "+ setColorAndGetColorName(shape2,COLOR,green));
	}
	
	//Class<? extends Shape> means: An unknown class which extends Shape.
	public static Shape createShape(Class<? extends Shape> clazz, Dimension dimension) {
		try {
			Constructor<? extends Shape> constructor = clazz.getDeclaredConstructor(Dimension.class);
	        constructor.setAccessible(true);
			return constructor.newInstance(dimension);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid class or invalid Type", e);
		}
	}
	
	public static String setColorAndGetColorName(Shape shape, String name, Color value) {
		String getterName = GET + name;
		String setterName = SET + name;
		String fieldName = name.toLowerCase();
		Class<?> clazz = shape.getClass();
		try {
			Method setter = clazz.getDeclaredMethod(setterName, clazz.getDeclaredField(fieldName).getType()); 
			setter.invoke(shape, value);
			
			Method getter = clazz.getDeclaredMethod(getterName); 	        			
			return getter.invoke(shape).toString();
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid class or invalid Type", e);
		}
	}
}
