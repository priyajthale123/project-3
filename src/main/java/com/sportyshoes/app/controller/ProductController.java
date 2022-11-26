package com.sportyshoes.app.controller;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sportyshoes.app.entity.Product;
import com.sportyshoes.app.service.ProductService;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/manageProduct")
	public String manageProduct(Model model) {
		model.addAttribute("products",productService.getAllProducts());
		Product product = new Product();
		model.addAttribute("product", product);
		return "manageProduct";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(@ModelAttribute("product") Product product, Model model, HttpSession session) {
		int min=10000;int max=99999;int b = (int)(Math.random()*(max-min+1)+min);
		productService.addProduct(product);
		session.setAttribute("action","Product Added succesfully");
		model.addAttribute("product", product);
		return "redirect:/manageProduct";
	}
	
	@GetMapping("/showProductUpdate/{id}")
	public String showProductUpdate(@PathVariable(value="id") int id, Model model) {
		Optional<Product> product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "update_product";
	}
	
	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute("product") Product product, Model model,HttpSession session) {
		productService.addProduct(product);
		session.setAttribute("action","Product Updated succesfully");
		model.addAttribute("product", product);
		return "redirect:/manageProduct";
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(value="id") int id,Model model,HttpSession session) {
		productService.deleteProduct(id);
		session.setAttribute("action", "Product Deleted Succesfully");
		Product product = new Product();
		model.addAttribute("product", product);
		return "redirect:/manageProduct";
	}
}
