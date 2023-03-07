package ray1024.weblab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ray1024.weblab4.data.Hasher;
import ray1024.weblab4.data.User;
import ray1024.weblab4.repository.IUserRepository;

import javax.servlet.http.HttpSession;


@RestController
public class UserController {
    private final IUserRepository userRepository;
    private final Hasher hasher;

    public UserController(@Autowired IUserRepository userRepository, @Autowired Hasher hasher) {
        this.userRepository = userRepository;
        this.hasher = hasher;
    }

    @CrossOrigin
    @PostMapping("/login")
    public String login(HttpSession session, @RequestBody User other) {
        User user = userRepository.findByUsername(other.getUsername());
        if (user != null && hasher.hash(other.getPassword()).equals(user.getPassword())) {
            user.setSessionID(session.getId());
            userRepository.save(user);
            return "{\"result\":\"OK\"}";
        }
        return "{\"result\":\"User doesn't exists\"}";
    }

    @CrossOrigin
    @GetMapping("/isLogged")
    public String isLogged(HttpSession session) {
        System.out.println(session.getId());
        if (userRepository.findBySessionID(session.getId()) != null) return "{\"result\":\"OK\"}";
        return "{\"result\":\"NO\"}";
    }

    @CrossOrigin
    @PostMapping("/register")
    public String register(HttpSession session, @RequestBody User other) {
        User user = userRepository.findByUsername(other.getUsername());
        if (user == null) {
            other.setSessionID(session.getId());
            other.setPassword(hasher.hash(other.getPassword()));
            userRepository.save(other);
            return "{\"result\":\"OK\"}";
        }
        return "{\"result\":\"User already exists\"}";
    }

    @CrossOrigin
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        User user = userRepository.findBySessionID(session.getId());
        if (user != null) {
            user.setSessionID(null);
            userRepository.save(user);
            return "{\"result\":\"OK\"}";
        }
        return "{\"result\":\"Logout error\"}";
    }


}
