package wsd.project.ecommerce.repository;

import wsd.project.ecommerce.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Sale s JOIN s.item i GROUP BY i ORDER BY SUM(s.amount) DESC")
    Page<Item> findTopKSellingItemsOfAllTime(Pageable pageable);

    @Query("SELECT i FROM Sale s JOIN s.item i WHERE s.date BETWEEN :startDate AND :endDate GROUP BY i ORDER BY COUNT(s.id) DESC")
    Page<Item> findTopKSellingItemsOfLastMonth(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, Pageable pageable);
}