package kg.booster.onlineshop.entity;

import kg.booster.onlineshop.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @CreationTimestamp
    private LocalDateTime created;

   //@UpdateTimestamp
   //private LocalDateTime updated;

    private BigDecimal sum;

    private String address;

    @OneToOne(cascade = CascadeType.ALL)
            @JoinColumn(name = "bucket_id")
    private Bucket bucket;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order",orphanRemoval = true)
    private List<OrderDetails>orderDetails;


    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


}
