package kg.booster.onlineshop.dao;


import kg.booster.onlineshop.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    //@Override
   // List<Category> findAll();

    //void addCategory(Category category);
//
    //Category getCategoryById(long id);
//
    //Category getCategoryByTitle(String title);
}
