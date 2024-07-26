package com.example.carservice.controller;

import com.example.carservice.model.Customer;
import com.example.carservice.model.Vehicle;
import com.example.carservice.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VehicleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VehicleRepository vehicleRepository;

    @Test
    public void getVehicleById() throws Exception {
        Customer owner = new Customer(1L, "John", "Doe", "john.doe@example.com", "123-456-7890");
        Vehicle vehicle = new Vehicle(2L, owner, "Maruti Swift", "abc-007");
        when(vehicleRepository.findById(2L)).thenReturn(Optional.of(vehicle));

        mvc.perform(MockMvcRequestBuilders.get("/api/vehicles/2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.model").value("Maruti Swift"))
                .andExpect(jsonPath("$.numberPlate").value("abc-007"))
                .andExpect(jsonPath("$.owner.firstName").value("John"))
                .andExpect(jsonPath("$.owner.lastName").value("Doe"));
    }
}
