package wsd.project.ecommerce;

import wsd.project.ecommerce.model.Item;
import wsd.project.ecommerce.model.Customer;
import wsd.project.ecommerce.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wsd.project.ecommerce.service.CustomerService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCustomerWishlist() {
        Long customerId = 1L;
        List<Item> wishlist = List.of(new Item(), new Item());
        Customer customer = new Customer();
        customer.setWishlist(wishlist);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        List<Item> result = customerService.getCustomerWishlist(customerId);

        assertEquals(2, result.size());
        verify(customerRepository, times(1)).findById(customerId);
    }
}
