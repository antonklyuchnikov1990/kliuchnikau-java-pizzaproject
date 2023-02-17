package de.telran.kliuchnikaujavapizzaproject.controller;

import de.telran.kliuchnikaujavapizzaproject.model.Pizza;
import de.telran.kliuchnikaujavapizzaproject.repository.CafeRepository;
import de.telran.kliuchnikaujavapizzaproject.service.PizzaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PizzaController {

    private final PizzaService pizzaService;

    private final CafeRepository cafeRepository;

    public PizzaController(PizzaService pizzaService, CafeRepository cafeRepository) {
        this.pizzaService = pizzaService;
        this.cafeRepository = cafeRepository;
    }


    @PostMapping("/addPizza")
    public String addPizza(Pizza pizza, @RequestParam("image") MultipartFile file) {
        pizzaService.saveNewPizza(pizza, file);
        return "redirect:/pizzas";
    }

    @GetMapping("/addPizza")
    public String addPizza(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("cafes", cafeRepository.findAll());
        return "pizza";
    }

    @GetMapping("/editPizza/{id}")
    public String editPizza(@PathVariable String id, Model model) {
        model.addAttribute("pizza", pizzaService.findPizzaById(id));
        model.addAttribute("cafes", cafeRepository.findAll());
        return "pizza";
    }

    @GetMapping("/pizza/{id}")
    public String getPizza(@PathVariable String id, Model model) {
        model.addAttribute("getPizza", pizzaService.findPizzaById(id));
        return "pizzas";
    }

    @GetMapping("/pizzas")
    public String getPizzas(Model model) {
        model.addAttribute("getPizzas", pizzaService.getAllPizzas());
        return "pizzas";
    }

    @GetMapping("/deletePizza/{id}")
    public String deletePizza(@PathVariable String id, Model model) {
        pizzaService.deletePizzaById(id);
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        return "redirect:/pizzas";
    }


}
