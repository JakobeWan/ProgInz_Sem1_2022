package lv.venta.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.joran.conditional.ElseAction;
import lv.venta.demo.models.Product;
import lv.venta.demo.services.ProductCRUD;

@Controller

public class MyFirstController {
	
	@Autowired
	private ProductCRUD productCRUDService; //te liek tikai interface, lai sasaitītu ar impl lieto autowired
			
	
	@GetMapping("/home")// url - localhost:8080/home
	public String getHomePage ()
	{
		System.out.println("Mans home kontrolieris ir nostrādājis");

		return "home"; //parādīs home.html lapu
		
		
	}
	
	//izveidot get kontrolieru funkciju, kas nosūtītu ziņu uz frontend
	@GetMapping("/send") // url - localhost:8080/send
	public String getSend(Model model)
	{
		model.addAttribute("msg","My message from backend to frontend");
		model.addAttribute("msg2", "Karina");
		return "msg-page"; //Parādīs msg-page.html
		
	}

	// izveidot get kontroliera funkciju, kas nosūtītu objektu uz frontend
	
	@GetMapping("/object") // localhost:8080/object
	
	public String getObject (Model model)
	{
		
		Product prod = new Product("abols","garsigs",10,0.99f );
		model.addAttribute("object",prod);
		return "one-product-page";
	}
	
	//1. uztaisīt kontrolējošo funkciju, kas pados allProducts uz frontend
	
@GetMapping("/allProducts")
public String getAllProducts (Model model) // localhost:8080/allProducts
{
	model.addAttribute("object", productCRUDService.readAllProducts()); // bija allProducts
	return "all-product-page";
	
}
	//2. uztaisīt atbilstošo html lapu - saraksta attēlošania
	
	
// localhost:8080/products?id=2 -> parāda tikai 2. produktu -> 

@GetMapping ("/allProductsFilter") //localhost:8080/allProductsFilter?id=2
public String getAllProductFilter (@RequestParam(name ="id")int id, Model model)
{
	try {
		model.addAttribute("object", productCRUDService.readProductById(id));
		
		return "one-product-page";
		
		
		
	} catch (Exception e) {
		// TODO: handle exception
		return "error-page";
	}
		

		

}


	//3. Apskatīties, kas ir @RequestParam un @PathVariable un uztaisīt kontrolējošās
	// funkcijas
@GetMapping ("/allProducts/{id}")
public String getAllProductsById (@PathVariable (name = "id")int id, Model model)
{
	try {
		model.addAttribute("object", productCRUDService.readProductById(id));
		
		return "one-product-page";
		
		
		
	} catch (Exception e) {
		// TODO: handle exception
		return "error-page";
	}
	
	
	
	
}


//1. ADD getMapping kontroliera funkcija, kas nosūtītu tukšu objektu

@GetMapping ("/addProduct") // localhost:8080/addProduct
public String getAddProduct (Product product)

{
	
	return"add-product-page";
}

//2. uztaisīt produktu kur var iecvadīt visus objekta parametrus izņemot id

//3. postMapping funkcija, kas saņem aizpildīto objektu un saglabā sarakstā

@PostMapping ("/addProduct")
public String postAddProduct (@Valid Product product, BindingResult result) //Aizpildītais produkts
{
	
	if (!result.hasErrors()) {
		
		if(productCRUDService.createNewProduct(product))
			return "redirect:/allProducts"; //post norāda uz kuru adresi pāradresēt produktus
			else 
				return "redirect:/error";
		
	}
	else {
		
		return"add-product-page";
	}
	
}


@GetMapping ("/updateProduct/{id}")
public String getUpdateProduct (@PathVariable (name = "id")int id, Model model)
{
	
	
	try {
		model.addAttribute("product", productCRUDService.readProductById(id));
		
		return "update-product-page";
		
		
		
	} catch (Exception e) {
		// TODO: handle exception
		return "error-page";
	}
	
	
}
//some sort of comment
@PostMapping ("/updateProduct/{id}")
public String postUpdateProduct (@PathVariable (name = "id")int id, Product product)
{
	
		if (productCRUDService.updateProductById(id, product))
		
			
			return "redirect:/allProducts/"+id;
			
		else 
	return "redirect:/error";
}
@GetMapping ("/error")
public String getError ()
{
	return "error-page";
}

//1. getMapping Dzēšam produktu


@GetMapping ("/deleteProduct/{id}")
public String getDeleteProduct (@PathVariable (name = "id")int id, Model model)// model backend uz frontend sūtīšana
{
	
		if (productCRUDService.deleteProductById(id))
		{	
			model.addAttribute("object", productCRUDService.readAllProducts());
			return "all-product-page";
			
		}
	else {
		
		
	return "error-page";
	}
	
}

//2. funkcijas deklarācija pieliekam modeli
//3. sameklējam konkrēto produkte pēc tā id
//4. dzēšam produktu
// 5. padodma visu produktu sarakstu frontend
//6. parādam all-product-page.html




}
