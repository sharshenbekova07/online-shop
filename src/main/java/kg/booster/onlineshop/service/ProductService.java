package kg.booster.onlineshop.service;

import kg.booster.onlineshop.dao.ProductRepository;
import kg.booster.onlineshop.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public Product findProductByTitle(String title){
        return productRepository.findProductByTitle(title);
    }

    public void addProducts(Product product){
        this.productRepository.save(product);
    }
    public Product findById(long id){
       return productRepository.findById( id).orElse(null);
    }
    public void delete(Product product){
        this.productRepository.delete(product);
    }
    public void updateProduct(Product product){
        this.productRepository.save(product);
    }


}
