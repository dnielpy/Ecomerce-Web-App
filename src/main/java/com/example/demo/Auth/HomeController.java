package com.example.demo.Auth;

import com.example.demo.Product.ProductDTO;
import com.example.demo.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/index")
    public String index2() {
        return index();
    }

    @RequestMapping("/change-password")
    public String changePassword() {
        return "change-password";
    }

    @RequestMapping("/product")
    public String product() {
        return "product";
    }

    @RequestMapping("/account")
    public String account() {
        return "account";
    }

    @RequestMapping("/orders")
    public String orders() {
        return "orders";
    }

    @GetMapping("/shop")
    public String shop(@RequestParam(required = false) String name, @RequestParam(required = false) String category, Model model) {
        List<ProductDTO> products = new ArrayList<>();
        String errorMessage = null;
        if (name != null && !name.isEmpty()) {
            try {
                ProductDTO product = productService.getProduct(name);
                if (product != null) {
                    products.add(product);
                }
            } catch (IllegalArgumentException e) {
                errorMessage = "El producto no existe en la base de datos";
            }
        } else if (category != null && !category.isEmpty()) {
            products = productService.getProductsByCategory(category.charAt(0));
        } else {
            products = productService.getNineRandomProducts();
        }
        model.addAttribute("products", products);
        model.addAttribute("errorMessage", errorMessage);
        return "shop";
    }

    @GetMapping("/shop/range")
    @ResponseBody
    public List<ProductDTO> getProductsByRange(@RequestParam double min, @RequestParam double max) {
        return productService.getProductByRange(min, max);
    }
+

    @RequestMapping("/shop")
    public String shop() {
        return "shop";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/refund")
    public String refund() {
        return "refund";
    }

    @RequestMapping("/terms")
    public String terms() {
        return "terms";
    }

    @RequestMapping("/cart")
    public String cart() {
        return "cart";
    }

    @RequestMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

}