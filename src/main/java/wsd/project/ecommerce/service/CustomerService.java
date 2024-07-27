package wsd.project.ecommerce.service;

import wsd.project.ecommerce.model.Customer;
import wsd.project.ecommerce.model.Item;
import wsd.project.ecommerce.repository.CustomerRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Cacheable(value = "wishlist", key = "#customerId")
    public List<Item> getCustomerWishlist(Long customerId) {
        return customerRepository.findById(customerId)
                .map(Customer::getWishlist)
                .orElse(List.of());
    }

    // we can apply cache aside policy when data is written
}
