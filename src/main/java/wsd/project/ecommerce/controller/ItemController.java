package wsd.project.ecommerce.controller;

import wsd.project.ecommerce.aspects.RateLimit;
import wsd.project.ecommerce.model.Item;
import wsd.project.ecommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RateLimit(50000)
    @GetMapping("/top-selling-all-time")
    public List<Item> getTop5SellingItemsOfAllTime() {
        return itemService.getTopSellingItemsOfAllTime(5);
    }

    @RateLimit(10000)
    @GetMapping("/top-selling-last-month")
    public List<Item> getTop5SellingItemsOfLastMonth() {
        return itemService.getTopSellingItemsOfLastMonth(5);
    }
}
