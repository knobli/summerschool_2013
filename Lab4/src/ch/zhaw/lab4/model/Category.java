package ch.zhaw.lab4.model;

public class Category {
	
	private String name;
	private String pictureUrl;

	public Category() {
		super();
	}

	public Category(String name, String pictureUrl) {
		super();
		this.name = name;
		this.pictureUrl = pictureUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	
}
