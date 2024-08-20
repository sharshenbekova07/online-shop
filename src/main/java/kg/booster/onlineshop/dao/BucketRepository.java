package kg.booster.onlineshop.dao;


import kg.booster.onlineshop.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepository extends JpaRepository<Bucket,Long> {

  //  Bucket createBucket(User user, List<Long>productId);
//
  //  void addProduct(Bucket bucket,List<Long>productId);
}
