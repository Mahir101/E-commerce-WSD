package wsd.project.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import wsd.project.ecommerce.controller.SaleController;
import wsd.project.ecommerce.service.SaleService;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SaleControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SaleService saleService;

    @InjectMocks
    private SaleController saleController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(saleController).build();
    }

    @Test
    public void testGetTotalSalesAmountOfDay() throws Exception {
        when(saleService.getTotalSalesAmount(LocalDate.now())).thenReturn(1000.0);

        mockMvc.perform(get("/sales/total")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1000.0));
    }

    @Test
    public void testGetMaxSaleDay() throws Exception {
        LocalDate startDate = LocalDate.now().minusDays(30);
        LocalDate endDate = LocalDate.now();
        LocalDate maxSaleDay = LocalDate.now().minusDays(10);

        when(saleService.getMaxSaleDay(startDate, endDate)).thenReturn(maxSaleDay);

        mockMvc.perform(get("/sales/max-sale-day")
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(maxSaleDay.toString()));
    }
}
