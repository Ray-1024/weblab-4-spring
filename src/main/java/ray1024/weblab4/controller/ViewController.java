package ray1024.weblab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ray1024.weblab4.repository.IUserRepository;

import javax.servlet.http.HttpSession;

@Controller
public class ViewController {
    private final IUserRepository userRepository;

    public ViewController(@Autowired IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String choosePage(HttpSession session) {
        return "index.html";
    }

}
