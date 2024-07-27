package wsd.project.ecommerce.repository;

import wsd.project.ecommerce.model.CustomerWishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerWishlistRepository extends JpaRepository<CustomerWishlist, Long> {
}
