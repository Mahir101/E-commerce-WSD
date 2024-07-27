package wsd.project.ecommerce.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(indexes = {
        @Index(name = "idx_sale_date", columnList = "date"),
        @Index(name = "idx_sale_customer_id", columnList = "customer_id"),
        @Index(name = "idx_sale_item_id", columnList = "item_id")
})
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
