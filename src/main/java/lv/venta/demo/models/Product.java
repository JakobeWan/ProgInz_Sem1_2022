package lv.venta.demo.models;

public class Product {
	
	//id, title, description, quantity, price
	// get and set
	//constructors - both 
	// toString
	
	private int id;
	private String title;
	private String description;
	private int quantity;
	private float price;
	private static int counter = 1000;
	
	
	public int getId() {
		return id;
	}
	public void setId() {
		this.id = counter;
		counter++;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	public Product () {
		
//		setId();
//		setTitle("Default Product title");
//		setDescription("Default product description");
//		setQuantity(1);
//		setPrice(10.0f);
//		
		
		
	}
	

	
	public Product(String title, String description, int quantity, float price) {
	
		setId();
		setTitle(title);
		setDescription(description);
		setQuantity(quantity);
		setPrice(price);
	}
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", description=" + description + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	
	
			
	
	
	
	
	
	

}
