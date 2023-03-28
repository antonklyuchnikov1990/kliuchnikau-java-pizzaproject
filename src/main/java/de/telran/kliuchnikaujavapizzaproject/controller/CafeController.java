package de.telran.kliuchnikaujavapizzaproject.controller;

import de.telran.kliuchnikaujavapizzaproject.model.Cafe;
import de.telran.kliuchnikaujavapizzaproject.service.CafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CafeController {

    private final CafeService cafeService;

    @Autowired
    public CafeController(CafeService cafeService) {
        this.cafeService = cafeService;
    }

    @GetMapping("/addCafe")
    @PreAuthorize("hasRole('ADMIN')")
    public String addCafe(Model model) {
        model.addAttribute("cafe", new Cafe());
        return "cafe";
    }

    @PostMapping("/addCafe")
    @PreAuthorize("hasRole('ADMIN')")
    public String addCafe(Cafe cafe) {
        cafeService.saveCafe(cafe);
        return "redirect:/cafes";
    }

    @GetMapping("/editCafe/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editCafe(@PathVariable String id, Model model) {
        model.addAttribute("cafe", cafeService.findCafeById(id));
        return "cafe";
    }

    @GetMapping("/cafe/{id}")
    public String getCafe(@PathVariable String id, Model model) {
        model.addAttribute("getCafe", cafeService.findCafeById(id));
        return "cafes";
    }

    @GetMapping("/cafes")
    public String getCafes(Model model) {
        model.addAttribute("getCafes", cafeService.getAllCafe());
        return "cafes";
    }

    @GetMapping("/deleteCafe/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteCafe(@PathVariable String id, Model model) {
        cafeService.deleteCafeById(id);
        model.addAttribute("cafes", cafeService.getAllCafe());
        return "redirect:/cafes";
    }

}
