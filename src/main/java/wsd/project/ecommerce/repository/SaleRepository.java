package wsd.project.ecommerce.repository;

import org.springframework.data.repository.query.Param;
import wsd.project.ecommerce.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT SUM(s.amount) FROM Sale s WHERE s.date = :date")
    Double findTotalSalesAmountByDate(@Param("date") LocalDate date);

    @Query("SELECT s.date FROM Sale s WHERE s.date BETWEEN :start AND :end GROUP BY s.date ORDER BY SUM(s.amount) DESC")
    LocalDate findMaxSaleDay(LocalDate start, LocalDate end);
}
