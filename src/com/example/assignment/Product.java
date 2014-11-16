package com.example.assignment;

import java.io.Serializable;

public class Product implements Serializable {
	private String name, description;
	private double price;
	private int imageID;
	
	public Product(String name, String description, double price, int imageID)
	{
		this.name = name;
		this.description = description;
		this.price = price;
		this.imageID = imageID;
	}
	
	public Product()
	{
		this.name = " ";
		this.description = " ";
		this.price = 0.00;
		this.imageID = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	
	
}
