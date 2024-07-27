package wsd.project.ecommerce.controller;

import wsd.project.ecommerce.aspects.RateLimit;
import wsd.project.ecommerce.model.Item;
import wsd.project.ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}/wishlist")
    public List<Item> getCustomerWishlist(@PathVariable Long id) {
        return customerService.getCustomerWishlist(id);
    }
}
