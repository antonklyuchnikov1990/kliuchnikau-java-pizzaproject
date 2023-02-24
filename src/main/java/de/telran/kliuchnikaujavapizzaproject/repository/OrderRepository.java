package de.telran.kliuchnikaujavapizzaproject.repository;

import de.telran.kliuchnikaujavapizzaproject.model.Cafe;
import de.telran.kliuchnikaujavapizzaproject.model.Order;
import de.telran.kliuchnikaujavapizzaproject.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, String> {

    List<Order> findByUser(User user);

    List<Order> findByCafe(Cafe cafe);
}
