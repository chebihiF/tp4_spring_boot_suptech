package suptech.miag.tp4.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product) throws Exception;
    Product updateProduct(Product product) throws Exception;
    Product deleteProduct(Product product) throws Exception;
    Product getProduct(String ref) throws Exception;
    List<Product> getProducts() throws Exception;
    Page<Product> getProductsByPages(Pageable pageable) throws Exception;
    List<Product> getProductsByLabel(String label) throws Exception;
}
