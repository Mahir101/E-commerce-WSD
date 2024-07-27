package wsd.project.ecommerce;

import wsd.project.ecommerce.repository.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import wsd.project.ecommerce.service.SaleService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SaleServiceTest {

    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private SaleService saleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTotalSalesAmount() {
        LocalDate date = LocalDate.now();
        when(saleRepository.findTotalSalesAmountByDate(date)).thenReturn(1000.0);

        Double result = saleService.getTotalSalesAmount(date);

        assertEquals(1000.0, result);
        verify(saleRepository, times(1)).findTotalSalesAmountByDate(date);
    }

    @Test
    public void testGetMaxSaleDay() {
        LocalDate startDate = LocalDate.now().minusDays(30);
        LocalDate endDate = LocalDate.now();
        LocalDate expectedDate = LocalDate.now().minusDays(10);

        when(saleRepository.findMaxSaleDay(startDate, endDate)).thenReturn(expectedDate);

        LocalDate result = saleService.getMaxSaleDay(startDate, endDate);

        assertEquals(expectedDate, result);
        verify(saleRepository, times(1)).findMaxSaleDay(startDate, endDate);
    }
}
