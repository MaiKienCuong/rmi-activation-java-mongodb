package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String street;
	private String city;
	private String state;
	private String postalCost;

	public Address() {
		super();
	}

	public Address(String street, String city, String state, String postalCost) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCost = postalCost;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCost() {
		return postalCost;
	}

	public void setPostalCost(String postalCost) {
		this.postalCost = postalCost;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", postalCost=" + postalCost + "]";
	}

}
