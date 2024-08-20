package kg.booster.onlineshop.controller;


import kg.booster.onlineshop.dao.UserRepository;
import kg.booster.onlineshop.entity.User;
import kg.booster.onlineshop.enums.RoleStatus;
import kg.booster.onlineshop.enums.UserStatus;
import kg.booster.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String main() {
        return "main-page";
    }

    @GetMapping(value = "/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public String registration(@ModelAttribute User user) {

        user.setRoleStatus(RoleStatus.USER);
        user.setUserStatus(UserStatus.ACTIVE);
        user.setActive(Boolean.TRUE);
        user.setDate(new Date());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "login";
    }

    @GetMapping(value = "/hello")
    public String hello(Principal principal, Authentication auth, Model model) {
        String userName = principal.getName();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        model.addAttribute("userName", userName);
        model.addAttribute("roles", authorities);
        return "hello";
    }

    @GetMapping(value = "/login")
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Почта или параоль неверны");
            model.setViewName("/login");
        }
        if (logout != null) {
            model.addObject("logout", "Logged out successfully.");
            model.setViewName("/login");
        }
        return model;
    }

    @GetMapping(value = "/contacts")
    public String getContacts() {
        return "get-contact";
    }

    @GetMapping(value = "/user")
    public String userPage() {
        return "user-page";
    }

    @GetMapping(value = "/admin")
    public String adminPage() {
        return "admin-page";
    }

    @GetMapping(value = "/findAllUsers")
    public String findAll(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "find-all";
    }

    @GetMapping(value = "/findUserByEmail/{email}")
    public String findByEmail(@RequestParam(value = "email") String email, Model model) {
        User users = userService.findByEmail(email);
        model.addAttribute("users", users);
        return "find-user-by-email";
    }

    @GetMapping(value = "/findUserById/{id}")
    public String findById(@RequestParam(value = "id") Long id, Model model) {
        User users = userService.findUserById(id);
        model.addAttribute("users", users);
        return "find-user-by-id";
    }

    @GetMapping(value = "/findUserByLastName/{lastName}")
    public String findUserByLastName(@RequestParam(value = "lastName") String lastName, Model model) {
        User users = userService.findUserByLastName(lastName);
        model.addAttribute("users", users);
        return "find-user-by-name-and-last-name";
    }

    @GetMapping(value = "/getUserByPhoneNumber/{phoneNumber}")
    public String getByPhoneNumber(@RequestParam(value = "phoneNumber") String phoneNumber, Model model) {
        User users = userService.getByPhoneNumber(phoneNumber);
        model.addAttribute("phoneNumber", users);
        return "get-by-phone-number";
    }

    @GetMapping(value = "/user/{id}/edit")
    public String updateUser(@PathVariable(value = "id") Long id, Model model) {
        User users = userService.findUserById(id);
        model.addAttribute("users", users);
        return "user-edit";
    }

    @PostMapping(value = "/user/{id}/edit")
    public String updateUser(@PathVariable(value = "id") Long id,
                             @RequestParam RoleStatus roleStatus,
                             @RequestParam UserStatus userStatus,
                             @RequestParam String email,
                             @RequestParam String password,
                             Model model) {

        User users = userService.findUserById(id);
        users.setRoleStatus(roleStatus);
        users.setUserStatus(userStatus);
        users.setEmail(email);
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        userService.updateUser(users);
        return "redirect:/findAllUsers";
    }

    @PostMapping(value = "/user/{id}/remove")
    public String userDelete(@PathVariable(value = "id") Long id, Model model) {
        User user = userService.findUserById(id);
        userService.delete(user);
        return "redirect:/findAllUsers";

    }

    @GetMapping(value = "/get/{userStatus}")
    public String getUserByStatus(@RequestParam(name = "userStatus") UserStatus userStatus, Model model) {
        List<User> users = userService.findByUserStatus(userStatus);
        model.addAttribute("status", users);
        return "get-users-by-status";
    }

    @GetMapping(value = "/userProfile")
    public String showUserProfile(Model model, Principal principal) {
        User users = userService.findByEmail(principal.getName());
        if (principal.getName() == null) {
            throw new RuntimeException("Вы не авторизованы !!!");
        }

        users.getName();
        users.getLastName();
        users.getPhoneNumber();
        users.getEmail();
        users.getPassword();
        model.addAttribute("user", users);
        return "update-user-profile";
    }

    @PostMapping(value = "/usersProfile")
    public String updateUserProfile(Model model, Principal principal,
                                    @RequestParam(value = "name") String name,
                                    @RequestParam(value = "lastName") String lastName,
                                    @RequestParam(value = "phoneNumber") String phoneNumber,
                                    @RequestParam(value = "email") String email,
                                    @RequestParam(value = "password") String password) {
        User users = userService.findByEmail(principal.getName());
        users.setName(name);
        users.setLastName(lastName);
        users.setPhoneNumber(phoneNumber);
        users.setEmail(email);
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        userService.updateProfile(users);
        return "redirect:/userProfile";

    }
}




