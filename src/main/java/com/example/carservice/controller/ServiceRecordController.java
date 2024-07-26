package com.example.carservice.controller;

import com.example.carservice.model.Employee;
import com.example.carservice.model.Service;
import com.example.carservice.model.ServiceRecord;
import com.example.carservice.model.Vehicle;
import com.example.carservice.repository.EmployeeRepository;
import com.example.carservice.repository.ServiceRecordRepository;
import com.example.carservice.repository.ServiceRepository;
import com.example.carservice.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/serviceRecords")
public class ServiceRecordController {

    @Autowired
    private ServiceRecordRepository serviceRecordRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private  VehicleRepository vehicleRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping
    public Map<String, Object> addServiceRecord(@RequestBody ServiceRecord serviceRecordRequest) {
        ServiceRecord savedServiceRecord = serviceRecordRepository.save(serviceRecordRequest);
        Map<String, Object> response = new HashMap<>();

        List<Service> services = serviceRecordRequest.getServices().stream()
                .map(serviceRequest -> serviceRepository.findById(serviceRequest.getId()).orElse(null))
                .filter(service -> service != null)
                .collect(Collectors.toList());

        List<Map<String, Object>> servicesResponse = services.stream()
                .map(service -> {
                    Map<String, Object> serviceMap = new HashMap<>();
                    serviceMap.put("name", service.getName());
                    return serviceMap;
                })
                .collect(Collectors.toList());

        response.put("services", servicesResponse);

        Vehicle vehicle = vehicleRepository.findById(serviceRecordRequest.getVehicle().getId()).orElse(null);

        Map<String, Object> vehicleResponse = new HashMap<>();
        vehicleResponse.put("model", vehicle.getModel());
        vehicleResponse.put("numberPlate", vehicle.getNumberPlate());

        response.put("vehicle", vehicleResponse);

        Employee employee = employeeRepository.findById(serviceRecordRequest.getEmployee().getId()).orElse(null);
        Map<String, Object> employeeResponse = new HashMap<>();
        employeeResponse.put("firstName", employee.getFirstName());
        employeeResponse.put("lastName", employee.getLastName());
        employeeResponse.put("role", employee.getRole());
        response.put("employee", employeeResponse);

        response.put("date", savedServiceRecord.getDate());
        response.put("notes", savedServiceRecord.getNotes());

        return response;
    }

    @GetMapping
    public List<ServiceRecord> getAllServiceRecords() {
        return serviceRecordRepository.findAll();
    }
}
