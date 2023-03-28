package de.telran.kliuchnikaujavapizzaproject.controller;

import de.telran.kliuchnikaujavapizzaproject.model.Pizza;
import de.telran.kliuchnikaujavapizzaproject.service.CafeService;
import de.telran.kliuchnikaujavapizzaproject.service.PizzaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class PizzaController {

    private final PizzaService pizzaService;

    private final CafeService cafeService;

    public PizzaController(PizzaService pizzaService, CafeService cafeService) {
        this.pizzaService = pizzaService;
        this.cafeService = cafeService;
    }


    @PostMapping("/addPizza")
    @PreAuthorize("hasRole('ADMIN')")
    public String addPizza(Pizza pizza, @RequestParam("image") MultipartFile file) {
        pizzaService.saveNewPizza(pizza, file);
        return "redirect:/pizzas";
    }

    @GetMapping("/addPizza")
    @PreAuthorize("hasRole('ADMIN')")
    public String addPizza(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("cafes", cafeService.getAllCafe());
        return "pizza";
    }

    @GetMapping("/editPizza/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editPizza(@PathVariable String id, Model model) {
        model.addAttribute("pizza", pizzaService.findPizzaById(id));
        model.addAttribute("cafes", cafeService.getAllCafe());
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
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePizza(@PathVariable String id, Model model) {
        pizzaService.deletePizzaById(id);
        model.addAttribute("pizzas", pizzaService.getAllPizzas());
        return "redirect:/pizzas";
    }

    @GetMapping("/pizzas/{cafeId}")
    public String getPizzasByCafe(@PathVariable String cafeId, Model model) {
        List<Pizza> pizzas = pizzaService.getPizzasInCafe(cafeService.findCafeById(cafeId));
        model.addAttribute("pizzasInCafe", pizzas);
        return "redirect:/pizzas";
    }

}
