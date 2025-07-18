package com.udea.parcial_practico.storage.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@Table(name = "store")
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "address", nullable = false, length = Integer.MAX_VALUE)
    private String address;

    @Column(name = "person_in_charge", nullable = false, length = Integer.MAX_VALUE)
    private String personInCharge;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

}