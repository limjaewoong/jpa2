package net.ljw.jpa2.controller;

import lombok.RequiredArgsConstructor;
import net.ljw.jpa2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/userRegForm")
    public String userRegForm() { return "userRegForm"; }

    @PostMapping(value = "/userReg")
    public String userReg(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password
    ){

        userService.addUser(name, email, password);

        return "redirect:/welcome";
    }

    @GetMapping(value = "/welcome")
    public String welcome() { return "welcome"; }
}
