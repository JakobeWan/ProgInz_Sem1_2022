package lv.venta.demo.services.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.demo.models.Product;
import lv.venta.demo.repos.IProductRepo;
import lv.venta.demo.services.ProductCRUD;

@Service
public class ProductCRUDImpl implements ProductCRUD {
	
	
	@Autowired
	private IProductRepo productRepo;
	
//	
//	private ArrayList<Product> allProducts = new ArrayList<>
//	(Arrays.asList(new Product("abols","garsigs",10,0.99f ),
//			new Product("pear","green",5,0.35f ),
//			new Product("plum","juicy",3,0.09f )));
//	

	@Override
    public boolean createNewProduct(Product product) {
		
		
		if (productRepo.existsByTitleAndDescription(product.getTitle(),product.getDescription()))
			
		{
			return false;
		}
		else {
			productRepo.save(product);
			return true;
		}
            
//            boolean isFound = false;
//            for(Product pr: allProducts)
//            {
//                    if(pr.getTitle().equals(product.getTitle()) && 
//                                    pr.getDescription().equals(product.getDescription()))
//                                    {
//                                            isFound = true;
//                                            break;
//                                    }
//            }
//            if(!isFound)
//            {
//                    Product newProduct = new Product(product.getTitle(),
//                            product.getDescription(), product.getQuantity(),
//                            product.getPrice());
//            
//                    allProducts.add(newProduct);
//                    return true;
//            }
//            else
//            {
//                    return false;
//            }
    }


	@Override
	public ArrayList<Product> readAllProducts() {
		// TODO Auto-generated method stub
//		return allProducts;
		
		return(ArrayList<Product>) productRepo.findAll();
	}

	@Override
	public Product readProductById(int id) throws Exception {
		// throws Exception arī jāpievieno interfacam 
		
		if(productRepo.existsById(id))
		{
			
			Product prod = productRepo.findById(id).get();
			return prod;
			
		}
		
//		for (Product temp: allProducts)
//		{
//			if (temp.getId()==id)
//			{
//				
//				
//				return temp;
//
//				
//			}
//		}
		throw new Exception("Produkts neeksistē");
	}

	@Override
	public boolean updateProductById(int id, Product product) {
		// TODO Auto-generated method stub
		
		
		if(productRepo.existsById(id))
		{
			
			Product prod = productRepo.findById(id).get();
			prod.setTitle(product.getTitle());
			prod.setDescription(product.getDescription());
			prod.setPrice(product.getPrice());
			prod.setQuantity(product.getQuantity());
			productRepo.save(prod);
	
			return true;
			
		}
		
		
//		for (Product temp: allProducts)
//		{
//			if (temp.getId()==id)
//			{
//				temp.setTitle(product.getTitle());
//				temp.setDescription(product.getDescription());
//				temp.setPrice(product.getPrice());
//				temp.setQuantity(product.getQuantity());
//				
//				return true;
//				
//			}
//		}
		return false;
	}

	@Override
	public boolean deleteProductById(int id) {
		// TODO Auto-generated method stub
		
		

		if(productRepo.existsById(id))
		{
			productRepo.deleteById(id);
			return true;
		}
		
//		for (Product temp: allProducts)
//		{
//			if (temp.getId()==id)
//			{
//				
//				allProducts.remove (temp);
//		return true;
//	}
//		}
			return false;
		
}
}
