package wsd.project.ecommerce;

import wsd.project.ecommerce.model.Item;
import wsd.project.ecommerce.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import wsd.project.ecommerce.service.ItemService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTopSellingItemsOfAllTime() {
        List<Item> items = List.of(new Item(), new Item());
        Page<Item> page = new PageImpl<>(items);
        when(itemRepository.findTopKSellingItemsOfAllTime(PageRequest.of(0, 5))).thenReturn(page);

        List<Item> result = itemService.getTopSellingItemsOfAllTime(5);

        assertEquals(2, result.size());
        verify(itemRepository, times(1)).findTopKSellingItemsOfAllTime(PageRequest.of(0, 5));
    }

    @Test
    public void testGetTopSellingItemsOfLastMonth() {
        List<Item> items = List.of(new Item(), new Item());
        Page<Item> page = new PageImpl<>(items);
        YearMonth lastMonth = YearMonth.now().minusMonths(1);
        LocalDate startDate = lastMonth.atDay(1);
        LocalDate endDate = lastMonth.atEndOfMonth();

        when(itemRepository.findTopKSellingItemsOfLastMonth(startDate, endDate, PageRequest.of(0, 5))).thenReturn(page);

        List<Item> result = itemService.getTopSellingItemsOfLastMonth(5);

        assertEquals(2, result.size());
        verify(itemRepository, times(1)).findTopKSellingItemsOfLastMonth(startDate, endDate, PageRequest.of(0, 5));
    }
}
