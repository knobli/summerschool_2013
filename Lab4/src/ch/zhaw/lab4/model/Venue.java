package ch.zhaw.lab4.model;

public class Venue {
	
	private String name;
	private String address;
	private String city;
	private String state;
	private String venueUrl;
	private Category category;
	
	public Venue() {
		super();
	}

	public Venue(String name, String address, String city, String state, String venueUrl, Category category) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.venueUrl = venueUrl;
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getVenueUrl() {
		return venueUrl;
	}

	public void setVenueUrl(String venueUrl) {
		this.venueUrl = venueUrl;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	
	

}
