package de.telran.kliuchnikaujavapizzaproject.repository;

import de.telran.kliuchnikaujavapizzaproject.model.Cafe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CafeRepository extends CrudRepository<Cafe, String> {
}
