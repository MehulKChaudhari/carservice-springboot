# Car Service Center API

This API provides endpoints to manage vehicles, services, service records, invoices, employees, and customers in a car service center.

## Endpoints

### Customers
- **POST /api/customers** - Add a new customer
  - Payload:
    ```json
    {
      "firstName": "Mehul",
      "lastName": "Chaudhari",
      "email": "mehul@example.com",
      "phone": "123-456-7890"
    }
    ```
- **GET /api/customers** - Get all customers
- **GET /api/customers/{id}** - Get a customer by ID

### Vehicles
- **POST /api/vehicles** - Add a new vehicle
  - Payload:
    ```json
    {
      "owner": {
        id: 1
      },
      "model": "Porche 911",
      "numberPlate": "ABC 007"
    }
    ```
- **GET /api/vehicles** - Get all vehicles
- **GET /api/vehicles/{id}** - Get a vehicle by ID

### Employees
- **POST /api/employees** - Add a new employee
  - Payload:
    ```json
    {
      "firstName": "Ramesh",
      "lastName": "Gupta",
      "role": "Technician"
    }
    ```
- **GET /api/employees** - Get all employees

  

### Services
- **POST /api/services** - Add a new service
  - Payload:
    ```json
    {
      "name": "Oil change",
      "description": "Clean oil filter and refill engine oil",
      "price": "5000"
    }
    ```
- **GET /api/services** - Get all services
- **GET /api/services/{id}** - Get a service by ID

### Service Records
- **POST /api/serviceRecords** - Add a new service record
  - Payload:
    ```json
    {
      "services": [
          {"id": 1},
          {"id": 5},
          {"id": 6},
          {"id": 2}
      ],
      "vehicle": { "id": 1 },
      "employee": { "id": 1 },
      "date": "2024-07-26",
      "notes": "Oil change completed"
    }
    ```
- **GET /api/serviceRecords** - Get all service records

### Invoices
- **POST /api/invoices/generate** - Create a new invoice
  - Payload:
    ```json
    {
    "services": [
        {"id": 1},
        {"id": 5},
        {"id": 6},
        {"id": 2}
    ],
    "date": "2024-07-26",
    "paid": true,
    "paymentMethod": "Credit card"
    }

    ```
- **GET /api/invoices** - Get all invoices
- **GET /api/invoices/{id}** - Get an invoice by ID

