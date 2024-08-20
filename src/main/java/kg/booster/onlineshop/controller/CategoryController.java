package kg.booster.onlineshop.controller;


import kg.booster.onlineshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

   // @GetMapping(value = "/categories")
   // public String getAllCategory(Model model){
   //     List<Category> categories=categoryService.getAllCategory();
   //     model.addAttribute("category",categories);
   //     return "all-categories";
//
   // }
}
