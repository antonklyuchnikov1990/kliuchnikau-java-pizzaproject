package de.telran.kliuchnikaujavapizzaproject.service;

import de.telran.kliuchnikaujavapizzaproject.model.Cafe;
import de.telran.kliuchnikaujavapizzaproject.model.Pizza;
import de.telran.kliuchnikaujavapizzaproject.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    @Value("${images.dir}")
    private String imagesDir;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public void saveNewPizza(Pizza pizza, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        pizza.setPicture(fileName);
        Path path = Paths.get(imagesDir + '/' + fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pizzaRepository.save(pizza);
    }

    public Pizza findPizzaById(String id) {
        return pizzaRepository.findById(id).get();
    }

    public List<Pizza> getAllPizzas() {
        return StreamSupport.stream(pizzaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void deletePizzaById(String id) {
        pizzaRepository.deleteById(id);
    }

    public List<Pizza> getPizzasInCafe(Cafe cafe) {
       return pizzaRepository.findPizzasByCafe(cafe);
    }

}
