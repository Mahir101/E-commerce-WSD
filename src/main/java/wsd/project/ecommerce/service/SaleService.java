package wsd.project.ecommerce.service;

import wsd.project.ecommerce.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public Double getTotalSalesAmount(LocalDate date) {
        return saleRepository.findTotalSalesAmountByDate(date);
    }

    public LocalDate getMaxSaleDay(LocalDate startDate, LocalDate endDate) {
        return saleRepository.findMaxSaleDay(startDate, endDate);
    }
}
