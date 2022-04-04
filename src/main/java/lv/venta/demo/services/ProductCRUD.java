package lv.venta.demo.services;

import java.util.ArrayList;

import lv.venta.demo.models.Product;

public interface ProductCRUD {
	
	//CRUD
	//C - create
	public abstract boolean createNewProduct (Product product);
	
	
	//R - read or retrieve all products
	
	public abstract ArrayList<Product> readAllProducts ();
	
	
	//R - read or retrieve one product

	public abstract Product readProductById (int id) throws Exception;
	
	//U - update product
	public abstract boolean updateProductById (int id, Product product);
	
	
	//D - delete product
	public abstract boolean deleteProductById (int id);


}
