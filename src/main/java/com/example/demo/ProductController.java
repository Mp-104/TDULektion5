package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Controller //RestController
@SpringBootApplication
public class ProductController {

    public static Map<Integer, Product> products = new HashMap<>();

    public ProductController () {
        products.put(1, new Product(1,"TV Samsung", 1000));
        products.put(2, new Product(2,"Joker-10", 3000));
        products.put(3, new Product(3, "Kinzhal", 100000000));
        products.put(4, new Product(4, "Lancet", 300000));
        products.put(5, new Product(5, "T-80", 30000000));
        products.put(6, new Product(6, "Ka-52", 150000000));

        //fler produkter
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductController.class, args);
    }

    @GetMapping("/hello")
    public String hello (Model model) {
        model.addAttribute("message", "Hello");
        return "hello";
    }

    @GetMapping("/myendpoint")
    public String myEndPoint (@RequestParam(value = "name") String name) {

        return "Hello " + name;
    }

    @GetMapping("/products")
    public String getProducts (Model model) {
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/{id}")
    public String getProduct (@PathVariable Integer id, Model model) {
        model.addAttribute("product",  products.get(id));
        return "product";
    }

}
