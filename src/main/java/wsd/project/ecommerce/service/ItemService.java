package wsd.project.ecommerce.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import wsd.project.ecommerce.model.Item;
import wsd.project.ecommerce.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getTopSellingItemsOfAllTime(int limit) {
        PageRequest pageRequest = PageRequest.of(0, limit); // 0 is the page number, limit is the page size
        Page<Item> page = itemRepository.findTopKSellingItemsOfAllTime(pageRequest);
        return page.getContent();
    }

    public List<Item> getTopSellingItemsOfLastMonth(int limit) {
        YearMonth lastMonth = YearMonth.now().minusMonths(1);
        LocalDate startDate = lastMonth.atDay(1);
        LocalDate endDate = lastMonth.atEndOfMonth();

        PageRequest pageRequest = PageRequest.of(0, limit); // 0 is the page number, limit is the page size
        Page<Item> page = itemRepository.findTopKSellingItemsOfLastMonth(startDate, endDate, pageRequest);
        return page.getContent();
    }
}
