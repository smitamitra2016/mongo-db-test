package com.example.beans;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "restaurants")
public class Address {

	private String building;
	private String street;
	private String zipcode;
	private double[] coord;
	private Coordinates coordinates = new Coordinates(0, 0);

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public double[] getCoord() {
		if (coord.length == 2) {
			this.coordinates = new Coordinates(coord[0], coord[1]);
		}
		return coord;
	}

	public void setCoord(double[] coords) {
		this.coord = coords;
	}

}
