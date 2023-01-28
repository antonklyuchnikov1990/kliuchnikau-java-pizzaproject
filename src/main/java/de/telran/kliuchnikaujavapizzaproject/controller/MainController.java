package de.telran.kliuchnikaujavapizzaproject.controller;

import de.telran.kliuchnikaujavapizzaproject.model.Cafe;
import de.telran.kliuchnikaujavapizzaproject.model.Pizza;
import de.telran.kliuchnikaujavapizzaproject.repository.CafeRepository;
import de.telran.kliuchnikaujavapizzaproject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class MainController {

    private final PizzaRepository pizzaRepository;

    private final CafeRepository cafeRepository;

    public MainController(PizzaRepository pizzaRepository, CafeRepository cafeRepository) {
        this.pizzaRepository = pizzaRepository;
        this.cafeRepository = cafeRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("/", pizzaRepository.findAll());
        return "index";
    }

    @PostMapping("/add_pizza")
    public String addPizza(Pizza pizza, Model model) {
        model.addAttribute("add_pizza", pizzaRepository.save(pizza));
        return "index";
    }

    @PostMapping("/add_cafe")
    public String addCafe(Cafe cafe, Model model) {
        model.addAttribute("add_cafe", cafeRepository.save(cafe));
        return "index";
    }

    @GetMapping("/pizza{id}")
    public String getPizza(@PathVariable String id, Model model) {
        model.addAttribute("get_pizza", pizzaRepository.findById(id));
        return "index";
    }

    @GetMapping("/cafe{id}")
    public String getCafe(@PathVariable String id, Model model) {
        model.addAttribute("get_cafe", cafeRepository.findById(id));
        return "index";
    }

    @GetMapping("/cafes")
    public String getCafes(Model model) {
        model.addAttribute("get_cafes", cafeRepository.findAll());
        return "index";
    }

    @Value("${images.dir}")
    private String imagesDir;

    @GetMapping("/image/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String filename) throws IOException {
        byte[] image = Files.readAllBytes(new File(imagesDir + "/" + filename).toPath());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

}
