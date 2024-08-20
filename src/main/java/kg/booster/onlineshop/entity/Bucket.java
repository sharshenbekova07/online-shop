package kg.booster.onlineshop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "buckets")
@Entity

public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;


    @OneToOne(mappedBy = "bucket", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "bucket", cascade = CascadeType.ALL, orphanRemoval = true)

    private Set<OrderDetails> orderDetails;

      //@ManyToMany
      //@JoinTable(name = "buckets_products",
      //      joinColumns = @JoinColumn(name = "bucket_id"),
      //      inverseJoinColumns =@JoinColumn(name = "product_id"))
      //private List<Product>products;
}
