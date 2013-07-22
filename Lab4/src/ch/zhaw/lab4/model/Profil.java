package ch.zhaw.lab4.model;

public class Profil {
	
	private long profilId;
	private String name;
	private String address;
	private String city;
	private String state;
	private String phone;
	
	public Profil(){
		super();
	}
	
	public Profil(String name, String address, String city, String state,
			String phone) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phone = phone;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public long getProfilId() {
		return profilId;
	}

	public void setProfilId(long profilId) {
		this.profilId = profilId;
	}

}
