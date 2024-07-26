package com.example.carservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service_records")
public class ServiceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="vehicleId")
    private Vehicle vehicle;

    @ManyToMany
    @JoinTable(
            name = "records",
            joinColumns = @JoinColumn(name = "record_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> services;

    private String date;

    @ManyToOne
    @JoinColumn(name="employeeId")
    private Employee employee;

    private String notes;
}
