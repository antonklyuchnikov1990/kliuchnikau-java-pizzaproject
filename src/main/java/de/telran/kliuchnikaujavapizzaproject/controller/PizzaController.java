package de.telran.kliuchnikaujavapizzaproject.controller;

import de.telran.kliuchnikaujavapizzaproject.model.Pizza;
import de.telran.kliuchnikaujavapizzaproject.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PizzaController {

    private final PizzaRepository pizzaRepository;

    public PizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @PostMapping("/addPizza")
    public String addPizza(Pizza pizza) {
        pizzaRepository.save(pizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/addPizza")
    public String addPizza(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizza";
    }

    @GetMapping("/editPizza/{id}")
    public String editPizza(@PathVariable String id, Model model) {
        model.addAttribute("pizza", pizzaRepository.findById(id).get());
        return "pizza";
    }

    @GetMapping("/pizza/{id}")
    public String getPizza(@PathVariable String id, Model model) {
        model.addAttribute("getPizza", pizzaRepository.findById(id));
        return "pizzas";
    }

    @GetMapping("/pizzas")
    public String getPizzas(Model model) {
        model.addAttribute("getPizzas", pizzaRepository.findAll());
        return "pizzas";
    }

    @GetMapping("/deletePizza/{id}")
    public String deletePizza(@PathVariable String id, Model model) {
        pizzaRepository.deleteById(id);
        model.addAttribute("pizzas", pizzaRepository.findAll());
        return "redirect:/pizzas";
    }
}
