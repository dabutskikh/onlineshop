package ru.dabutskikh.onlineshop.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    @PreAuthorize("hasAuthority('customers:read')")
    public List<Customer> getAll() {
        return CUSTOMERS;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('customers:read')")
    public Customer getById(@PathVariable Long id) {
        return CUSTOMERS.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('customers:write')")
    public Customer create(@RequestBody Customer customer) {
        CUSTOMERS.add(customer);
        return customer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('customers:write')")
    public void deleteById(@PathVariable Long id) {
        CUSTOMERS.removeIf(customer -> customer.getId().equals(id));
    }
}
