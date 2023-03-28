package de.telran.kliuchnikaujavapizzaproject.repository;

import de.telran.kliuchnikaujavapizzaproject.model.Cafe;
import de.telran.kliuchnikaujavapizzaproject.model.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, String> {

    void deleteById(String id);
    Optional<Pizza> findById(String id);

    Pizza findByName(String name);

    List<Pizza> findPizzasByCafe(Cafe cafe);

}
