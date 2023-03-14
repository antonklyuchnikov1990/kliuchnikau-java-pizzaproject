package de.telran.kliuchnikaujavapizzaproject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Pizza> pizzas = new ArrayList<>();

    @ManyToOne
    private User user;

    @ManyToOne
    private Cafe cafe;

}
