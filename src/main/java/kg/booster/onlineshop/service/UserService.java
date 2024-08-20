package kg.booster.onlineshop.service;

import kg.booster.onlineshop.dao.UserRepository;
import kg.booster.onlineshop.entity.User;
import kg.booster.onlineshop.enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(User user) {

        this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findFirstByEmail(email));
        if (!optionalUser.isPresent()){
            throw new UsernameNotFoundException("User not found");
        }
        User user = optionalUser.get();
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(String.valueOf(user.getRoleStatus()));
        grantedAuthorities.add(simpleGrantedAuthority);
        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),grantedAuthorities);
    }

    public User findByEmail(String email){
       return userRepository.findByEmail(email);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public User findUserByLastName(String lastName){
        return userRepository.findByLastName(lastName);
    }
    public User getByPhoneNumber(String phoneNumber){

        return userRepository.findByPhoneNumber(phoneNumber);
    }
    public void updateUser(User user){
        this.userRepository.save(user);
    }

    public void delete(User user){
         userRepository.delete(user);
    }

    public List<User> findByUserStatus(UserStatus userStatus){
        return userRepository.findByUserStatus(userStatus);
    }
    public void updateProfile(User user){
        userRepository.save(user);
    }


}
