package kg.booster.onlineshop.service;

import kg.booster.onlineshop.dao.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

//    public void addCategory(Category category){
//        this.categoryRepository.addCategory(category);
//    }
//
//    public List <Category>getAllCategory(){
//        return categoryRepository.findAll();
//    }
//    public Category getCategoryById(long id){
//        return categoryRepository.getCategoryById(id);
//    }
//
//    public Category getCategoryByTitle(String title){
//        return categoryRepository.getCategoryByTitle(title);
//    }
}
