package wsd.project.ecommerce;

import wsd.project.ecommerce.controller.ItemController;
import wsd.project.ecommerce.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import wsd.project.ecommerce.service.ItemService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ItemControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    public void testGetTop5SellingItemsOfAllTime() throws Exception {
        List<Item> items = List.of(new Item(), new Item());
        when(itemService.getTopSellingItemsOfAllTime(5)).thenReturn(items);

        mockMvc.perform(get("/items/top-selling-all-time")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetTop5SellingItemsOfLastMonth() throws Exception {
        List<Item> items = List.of(new Item(), new Item());
        when(itemService.getTopSellingItemsOfLastMonth(5)).thenReturn(items);

        mockMvc.perform(get("/items/top-selling-last-month")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}



