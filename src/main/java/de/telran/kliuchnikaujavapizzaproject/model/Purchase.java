package de.telran.kliuchnikaujavapizzaproject.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Purchase {

    private String id;

    private double amount;



}
