package com.example.carservice.controller;

import com.example.carservice.model.Invoice;
import com.example.carservice.model.Service;
import com.example.carservice.repository.InvoiceRepository;
import com.example.carservice.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @PostMapping("/generate")
    public Map<String, Object> createInvoice(@RequestBody Invoice invoiceRequest) {
        List<Service> services = invoiceRequest.getServices().stream()
                .map(serviceRequest -> serviceRepository.findById(serviceRequest.getId()).orElse(null))
                .filter(service -> service != null)
                .collect(Collectors.toList());

        double totalAmount = services.stream()
                .mapToDouble(Service::getPrice)
                .sum();

        Invoice invoice = new Invoice();
        invoice.setServices(services);
        invoice.setDate(invoiceRequest.getDate());
        invoice.setPaid(invoiceRequest.isPaid());
        invoice.setPaymentMethod(invoiceRequest.getPaymentMethod());
        invoice.setTotalAmount(totalAmount);

        invoiceRepository.save(invoice);

        Map<String, Object> response = new HashMap<>();
        response.put("id", invoice.getId());
        response.put("paid", invoice.isPaid());

        List<Map<String, Object>> servicesResponse = services.stream()
                .map(service -> {
                    Map<String, Object> serviceMap = new HashMap<>();
                    serviceMap.put("name", service.getName());
                    serviceMap.put("price", service.getPrice());
                    return serviceMap;
                })
                .collect(Collectors.toList());

        response.put("services", servicesResponse);
        response.put("totalAmount", totalAmount);

        return response;
    }

    @GetMapping("/{id}")
    public Invoice getInvoiceById(@PathVariable Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
}
