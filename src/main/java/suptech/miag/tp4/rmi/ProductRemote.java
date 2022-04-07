package suptech.miag.tp4.rmi;

import suptech.miag.tp4.product.Product;

import java.rmi.Remote;
import java.util.List;

public interface ProductRemote{
    Product deleteProduct(Product product) throws Exception;
    List<Product> getProducts() throws Exception;
}
