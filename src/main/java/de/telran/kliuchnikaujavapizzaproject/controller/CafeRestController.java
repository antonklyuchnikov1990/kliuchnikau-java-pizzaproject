package de.telran.kliuchnikaujavapizzaproject.controller;

import de.telran.kliuchnikaujavapizzaproject.model.Cafe;
import de.telran.kliuchnikaujavapizzaproject.repository.CafeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CafeRestController {

   private final CafeRepository cafeRepository;

    public CafeRestController(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @GetMapping("/cafe/{id}")
    public ResponseEntity<?> getCafe(@PathVariable String id) {
        if (!cafeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(cafeRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/cafes")
    public ResponseEntity<?> getAllCafes() {
        return  new ResponseEntity<>(cafeRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/cafe", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> updateCafe(@RequestBody Cafe cafe) {
        return new ResponseEntity<>(cafeRepository.save(cafe), HttpStatus.CREATED);
    }

    @DeleteMapping("/cafe/{id}")
    public ResponseEntity<?> deleteCafe(@PathVariable String id) {
        if (!cafeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cafeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
