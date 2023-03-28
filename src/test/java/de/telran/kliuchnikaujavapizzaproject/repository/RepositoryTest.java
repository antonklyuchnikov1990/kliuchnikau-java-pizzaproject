package de.telran.kliuchnikaujavapizzaproject.repository;

import de.telran.kliuchnikaujavapizzaproject.model.Cafe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RepositoryTest {

    @Autowired
    CafeRepository cafeRepository;

    @Test
    public void testCafeCRUD() {
        Cafe cafe = new Cafe();
        cafe.setName("Cafe Test");

        cafeRepository.save(cafe);

        Cafe found = cafeRepository.findCafeByName("Cafe Test");
        Assertions.assertEquals(found.getName(), "Cafe Test");

        cafeRepository.delete(cafe);
        Assertions.assertNull(cafeRepository.findCafeByName("Cafe Test"));
    }
}
