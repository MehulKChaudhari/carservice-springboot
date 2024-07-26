package com.example.carservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="customerId")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name="vehicleId")
    private Vehicle vehicle;
    @ManyToOne
    @JoinColumn(name="serviceId")
    private Service service;
    private String appointmentDate;
    private String status;
}
