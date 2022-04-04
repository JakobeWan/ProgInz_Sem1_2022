package lv.venta.demo.services.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import lv.venta.demo.models.Product;
import lv.venta.demo.services.ProductCRUD;

@Service
public class ProductCRUDImpl implements ProductCRUD {
	
	private ArrayList<Product> allProducts = new ArrayList<>
	(Arrays.asList(new Product("abols","garsigs",10,0.99f ),
			new Product("pear","green",5,0.35f ),
			new Product("plum","juicy",3,0.09f )));
	

	@Override
    public boolean createNewProduct(Product product) {
            
            boolean isFound = false;
            for(Product pr: allProducts)
            {
                    if(pr.getTitle().equals(product.getTitle()) && 
                                    pr.getDescription().equals(product.getDescription()))
                                    {
                                            isFound = true;
                                            break;
                                    }
            }
            if(!isFound)
            {
                    Product newProduct = new Product(product.getTitle(),
                            product.getDescription(), product.getQuantity(),
                            product.getPrice());
            
                    allProducts.add(newProduct);
                    return true;
            }
            else
            {
                    return false;
            }
    }


	@Override
	public ArrayList<Product> readAllProducts() {
		// TODO Auto-generated method stub
		return allProducts;
	}

	@Override
	public Product readProductById(int id) throws Exception {
		// throws Exception arī jāpievieno interfacam 
		for (Product temp: allProducts)
		{
			if (temp.getId()==id)
			{
				
				
				return temp;

				
			}
		}
		throw new Exception("Produkts neeksistē");
	}

	@Override
	public boolean updateProductById(int id, Product product) {
		// TODO Auto-generated method stub
		for (Product temp: allProducts)
		{
			if (temp.getId()==id)
			{
				temp.setTitle(product.getTitle());
				temp.setDescription(product.getDescription());
				temp.setPrice(product.getPrice());
				temp.setQuantity(product.getQuantity());
				
				return true;
				
			}
		}
		return false;
	}

	@Override
	public boolean deleteProductById(int id) {
		// TODO Auto-generated method stub
		
		
		for (Product temp: allProducts)
		{
			if (temp.getId()==id)
			{
				
				allProducts.remove (temp);
		return true;
	}
		}
			return false;
		
}
}
