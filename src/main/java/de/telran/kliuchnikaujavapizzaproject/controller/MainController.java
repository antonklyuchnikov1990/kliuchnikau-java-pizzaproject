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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final CafeRepository cafeRepository;

    private final PizzaRepository pizzaRepository;

    @Value("${images.dir}")
    private String imagesDir;

    @GetMapping("/image/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String filename) throws IOException {
        byte[] image = Files.readAllBytes(new File(imagesDir + "/" + filename).toPath());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }

    public MainController(CafeRepository cafeRepository, PizzaRepository pizzaRepository) {
        this.cafeRepository = cafeRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("menu", pizzaRepository.findAll());
        return "index";

//        Map<Cafe, Iterable<Pizza>> pizzasByCafes = new HashMap<>();
//        for (Cafe cafe : cafeRepository.findAll()) {
//            pizzasByCafes.put(cafe, pizzaRepository.findAllById(Collections.singleton(cafe.getId())));
//        }
//        model.addAttribute("map", pizzasByCafes);
//        return "index";
    }



}
