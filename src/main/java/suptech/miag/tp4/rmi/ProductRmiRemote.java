package suptech.miag.tp4.rmi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import suptech.miag.tp4.product.IProductService;
import suptech.miag.tp4.product.Product;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

@Component
public class ProductRmiRemote implements ProductRemote {

    private final IProductService service;

    public ProductRmiRemote(IProductService service) throws RemoteException {
        this.service = service;
    }

    @Override
    public Product deleteProduct(Product product) throws Exception {
        //filters
        return service.deleteProduct(product);
    }

    @Override
    public List<Product> getProducts() throws Exception {
        //filtres
        return service.getProducts();
    }
}
