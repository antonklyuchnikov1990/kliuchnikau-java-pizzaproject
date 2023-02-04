package de.telran.kliuchnikaujavapizzaproject.repository;

import de.telran.kliuchnikaujavapizzaproject.model.Pizza;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, String> {
}
