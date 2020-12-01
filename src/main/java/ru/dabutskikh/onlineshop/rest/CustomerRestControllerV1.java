package ru.dabutskikh.onlineshop.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dabutskikh.onlineshop.model.Customer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerRestControllerV1 {

    private List<Customer> CUSTOMERS = Stream.of(
            new Customer(1L, "Ivan", "Ivanov"),
            new Customer(2L, "Sergey", "Sergeev"),
            new Customer(3L, "Petr", "Petrov")
    ).collect(Collectors.toList());

    @GetMapping
    public List<Customer> getAll() {
        return CUSTOMERS;
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id) {
        return CUSTOMERS.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
