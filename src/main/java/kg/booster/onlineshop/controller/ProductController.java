package kg.booster.onlineshop.controller;


import kg.booster.onlineshop.entity.Product;
import kg.booster.onlineshop.service.CategoryService;
import kg.booster.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/products")
    public String getAllProducts(Model model){
        List<Product> products=productService.findAll();
        model.addAttribute("products",products);
        return "all-products";
    }
    @GetMapping(value = "/add-products")
    public String showProducts(Model model){
        return "add-new-products";
    }


    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/add-products";
    @PostMapping(value = "/add-products")
    public String addNewProducts(@RequestParam String title, @RequestParam String color,
                                 @RequestParam BigDecimal price,
                                 @RequestParam String currency) throws IOException {
        Product product=new Product();

      // String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
      //
      //
      // String uploadDir = "user-photos/" + product.getId() ;
      // FileUploadUtil.saveFile(uploadDir, fileName, multipartFile) ;
        

        product.setColor(color);
        product.setTitle(title);
        product.setPrice(price);
        product.setCurrency(currency);
        //product.setImage(fileName);


        productService.addProducts(product);
        return "redirect:/products";
    }
    @PostMapping(value = "/product/{id}/delete")
    public String deleteProduct(@PathVariable(value = "id")long id,Model model){
        Product product=productService.findById(id);
        productService.delete(product);
        return "all-products";
    }
    @GetMapping (value = "/product/{id}/update")
    public String productsForUpdate(@PathVariable(value = "id")long id,Model model){
        Product product=productService.findById(id);

        //product.setImage(product.getImage());
        productService.updateProduct(product);
        return "update-product";
    }

    @PutMapping(value = "/product/{id}/update")
    public String updateProducts(@PathVariable(value = "id")long id,Model model){
        Product product=productService.findById(id);

        //product.setImage(product.getImage());
        productService.updateProduct(product);
        return "all-products";
    }


}
