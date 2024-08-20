package kg.booster.onlineshop.entity;

import kg.booster.onlineshop.enums.RoleStatus;
import kg.booster.onlineshop.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "USER_LAST_NAME")
    private String lastName;

    @Column(name = "USER_EMAIL")
    private String email;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name = "CREATED_DATE")
    private Date date;

    @Enumerated(EnumType.STRING)
    private RoleStatus roleStatus;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column
    private Boolean active;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bucket_id")
    private Bucket bucket;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Order> orders;




}


