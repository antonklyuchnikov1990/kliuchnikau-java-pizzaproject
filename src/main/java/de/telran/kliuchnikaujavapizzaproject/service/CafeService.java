package de.telran.kliuchnikaujavapizzaproject.service;

import de.telran.kliuchnikaujavapizzaproject.model.Cafe;
import de.telran.kliuchnikaujavapizzaproject.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CafeService {

    private final CafeRepository cafeRepository;

    @Autowired
    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    public Iterable<Cafe> getAllCafe() {
        return cafeRepository.findAll();
    }

    public void saveCafe(Cafe cafe) {
        cafeRepository.save(cafe);
    }

    public Cafe findCafeById(String id) {
        return cafeRepository.findById(id).orElse(null);
    }

    public void deleteCafeById(String id) {
        cafeRepository.deleteById(id);
    }

}
