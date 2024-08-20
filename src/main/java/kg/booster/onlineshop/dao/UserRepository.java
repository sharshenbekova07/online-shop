package kg.booster.onlineshop.dao;
import kg.booster.onlineshop.entity.User;
import kg.booster.onlineshop.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository

public interface UserRepository extends JpaRepository<User,Long> {

    User findFirstByEmail(String email);

    User findByEmail(String email);

    List<User> findAll();

    Optional<User> findById(Long id);

    User findByLastName(String lastName);

    User findByPhoneNumber(String phoneNumber);

    List<User> findByUserStatus(UserStatus userStatus);

   // public void updateUser(User user);

}





