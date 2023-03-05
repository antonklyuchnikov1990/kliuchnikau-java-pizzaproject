package de.telran.kliuchnikaujavapizzaproject.controller;

import de.telran.kliuchnikaujavapizzaproject.model.GoogleResponse;
import de.telran.kliuchnikaujavapizzaproject.model.User;
import de.telran.kliuchnikaujavapizzaproject.service.CaptchaService;
import de.telran.kliuchnikaujavapizzaproject.service.PizzaService;
import de.telran.kliuchnikaujavapizzaproject.service.RoleService;
import de.telran.kliuchnikaujavapizzaproject.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class MainController {

    private final PizzaService pizzaService;
    private final UserService userService;
    private final RoleService roleService;

    private final CaptchaService captchaService;

    @Value("${images.dir}")
    private String imagesDir;

    @Autowired
    public MainController(PizzaService pizzaService, UserService userService, RoleService roleService, CaptchaService captchaService) {
        this.pizzaService = pizzaService;
        this.userService = userService;
        this.roleService = roleService;
        this.captchaService = captchaService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("menu", pizzaService.getAllPizzas());
        return "index";
    }

    @GetMapping("/image/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String filename) throws IOException {
        byte[] image = Files.readAllBytes(new File(imagesDir + "/" + filename).toPath());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.addError(new FieldError("user", "password",
                    "Passwords must be equals"));
            return "registration";
        }
        userService.saveUser(user);
        userService.authenticateUser(user);
        String response = request.getParameter("g-recaptcha-response");
        String ip = request.getRemoteAddr();
        GoogleResponse googleResponse = captchaService.processResponse(response, ip);
        if (!googleResponse.isSuccess()) {
            return "registration";
        }
        return "redirect:/";
    }

}
