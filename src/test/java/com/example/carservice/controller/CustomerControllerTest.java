package com.example.carservice.controller;

import com.example.carservice.model.Customer;
import com.example.carservice.repository.CustomerRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void getCustomerById() throws Exception {
        Customer customer = new Customer(2L, "Mehul", "Chaudhari", "mehul@example.com", "123-456-7890");
        when(customerRepository.findById(2L)).thenReturn(Optional.of(customer));

        mvc.perform(MockMvcRequestBuilders.get("/api/customers/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Mehul"))
                .andExpect(jsonPath("$.lastName").value("Chaudhari"))
                .andExpect(jsonPath("$.email").value("mehul@example.com"))
                .andExpect(jsonPath("$.phone").value("123-456-7890"));
    }

}
