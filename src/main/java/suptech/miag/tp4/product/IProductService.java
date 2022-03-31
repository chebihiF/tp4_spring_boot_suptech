package suptech.miag.tp4.product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product) throws Exception;
    Product updateProduct(Product product) throws Exception;
    Product deleteProduct(Product product) throws Exception;
    Product getProduct(String ref) throws Exception;
    List<Product> getProducts(String ref) throws Exception;
    List<Product> getProductsByLabel(String label) throws Exception;
}
