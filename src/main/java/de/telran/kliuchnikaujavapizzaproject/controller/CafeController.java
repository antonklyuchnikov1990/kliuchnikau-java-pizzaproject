package de.telran.kliuchnikaujavapizzaproject.controller;

import de.telran.kliuchnikaujavapizzaproject.model.Cafe;
import de.telran.kliuchnikaujavapizzaproject.repository.CafeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CafeController {

    private final CafeRepository cafeRepository;

    public CafeController(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @GetMapping("/addCafe")
    public String addCafe(Model model) {
        model.addAttribute("cafe", new Cafe());
        return "cafe";
    }

    @PostMapping("/addCafe")
    public String addCafe(Cafe cafe) {
        cafeRepository.save(cafe);
        return "redirect:/cafes";
    }

    @GetMapping("/editCafe/{id}")
    public String editCafe(@PathVariable String id, Model model) {
        model.addAttribute("cafe", cafeRepository.findById(id).get());
        return "cafe";
    }

    @GetMapping("/cafe/{id}")
    public String getCafe(@PathVariable String id, Model model) {
        model.addAttribute("getCafe", cafeRepository.findById(id));
        return "cafes";
    }

    @GetMapping("/cafes")
    public String getCafes(Model model) {
        model.addAttribute("getCafes", cafeRepository.findAll());
        return "cafes";
    }

    @GetMapping("/deleteCafe/{id}")
    public String deleteCafe(@PathVariable String id, Model model) {
        cafeRepository.deleteById(id);
        model.addAttribute("cafes", cafeRepository.findAll());
        return "redirect:/cafes";
    }

}
