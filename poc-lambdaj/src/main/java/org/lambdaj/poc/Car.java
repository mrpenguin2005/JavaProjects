package org.lambdaj.poc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

public class Car {

	private BrandType brand;
	private Integer year;
	private String model;

	public Car(BrandType brand, Integer year, String model) {
		this.brand = brand;
		this.year = year;
		this.model = model;
	}

	public BrandType getBrand() {
		return brand;
	}

	public void setBrand(BrandType brand) {
		this.brand = brand;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "Model : " + model + " Year : " + "Brand : " + brand;
	}

	public static Comparator<Car> getComparator() {
		return new CompareBrandYear();
	}
	
	public static Comparator<Car> getComparator(String... properties) {
		return new CompareProperties(properties);
	}
}

class CompareBrandYear implements Comparator<Car> {

	public int compare(Car o1, Car o2) {
		if (o1.getBrand().equals(o2.getBrand())
				&& o1.getYear().equals(o2.getYear())) {
			return 0;
		}
		return 1;
	}
}

class CompareProperties implements Comparator<Car> {
	private List<String> properties;

	public CompareProperties(String... properties) {
		this.properties = Arrays.asList(properties);
	}

	public int compare(Car o1, Car o2) {
		Boolean isEqual = true;
		try {
			for (String property : properties) {
				if (!PropertyUtils.getSimpleProperty(o1, property).equals(PropertyUtils.getSimpleProperty(o2, property))) {
					isEqual = false;
					break;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (isEqual) {
			return 0;
		}
		return 1;
	}
}
