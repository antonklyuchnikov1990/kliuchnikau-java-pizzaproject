package de.telran.kliuchnikaujavapizzaproject.controller;

import de.telran.kliuchnikaujavapizzaproject.model.Pizza;
import de.telran.kliuchnikaujavapizzaproject.repository.PizzaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PizzaRestController {

    private final PizzaRepository pizzaRepository;

    public PizzaRestController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/pizza/{id}")
    public ResponseEntity<?> getPizza(@PathVariable String id) {
        if (!pizzaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(pizzaRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/pizzas")
    public ResponseEntity<?> getAllPizzas() {
        return new ResponseEntity<>(pizzaRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/pizza", method = {RequestMethod.PUT, RequestMethod.POST})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createPizza(@RequestBody Pizza pizza) {
        return new ResponseEntity<>(pizzaRepository.save(pizza), HttpStatus.CREATED);
    }

    @DeleteMapping("/pizza/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deletePizza(@PathVariable String id) {
        if (!pizzaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pizzaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pizza")
    public ResponseEntity<?> getPizzaByName(@RequestParam String name) {
        if (pizzaRepository.findByName(name) == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(pizzaRepository.findByName(name), HttpStatus.OK);
    }

}
