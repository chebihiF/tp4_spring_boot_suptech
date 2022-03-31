package suptech.miag.tp4.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

    List<Product> findProductByQuantityIsGreaterThan(int quantity);
    List<Product> findProductByLabelStartingWith(String start);

    @Query("from Product p where p.label like '%:keyword%'")
    List<Product> findAllProductsByLabel(String keyword);
}
