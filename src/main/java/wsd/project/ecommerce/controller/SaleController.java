package wsd.project.ecommerce.controller;

import wsd.project.ecommerce.aspects.RateLimit;
import wsd.project.ecommerce.service.SaleService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @RateLimit(1000000)
    @GetMapping("/total")
    public Double getTotalSalesAmountOfDay() {
        return saleService.getTotalSalesAmount(LocalDate.now());
    }

    @RateLimit(1000000)
    @GetMapping("/max-sale-day")
    public LocalDate getMaxSaleDay(@RequestParam("startDate") LocalDate start, @RequestParam("endDate") LocalDate end) {
        return saleService.getMaxSaleDay(start, end);
    }
}
