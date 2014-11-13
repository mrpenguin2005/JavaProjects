package org.lambdaj.poc;

import static ch.lambdaj.Lambda.selectDistinct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ch.lambdaj.Lambda;

public class Teste {
	
	public static void main(String[] args) {
		List<Car> listCar = init();
		
		Lambda.collect(listCar, Lambda.on(Car.class).getBrand());
		
		Collection<Car> result;
//		result = selectDistinct( listCar,"brand");
//		show(result);
		
		result = selectDistinct( listCar,Car.getComparator("brand"));
		show(result);
	}
	
	public static List<Car> init() {
		List<Car> car;
		
		car = new ArrayList<Car>();
		
		car.add(new Car(1,2012,"Gol"));
		car.add(new Car(1,2013,"Gol"));
		car.add(new Car(1,2014,"Gol 1.0"));
		car.add(new Car(1,2014,"Gol 1.6"));
		car.add(new Car(1,2014,"Gol 1.8"));
		//
		car.add(new Car(2,2012,"Palio"));
		car.add(new Car(2,2013,"Punto"));
		car.add(new Car(2,2014,"Punto"));
		
		car.add(new Car(3,2014,"Audi A7"));
		car.add(new Car(3,2014,"Audi A8"));
		
		return car;
	}
	
	public static void show(Collection<Car> list) {
		for (Car car : list) {
			System.err.println(car);
		}
		System.err.println("-----------------");
	}

}
