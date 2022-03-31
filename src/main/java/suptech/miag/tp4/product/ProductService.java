package suptech.miag.tp4.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) throws Exception {
        if(!product.getRef().startsWith("R00"))
            throw new Exception("invalid product reference");
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) throws Exception {
        if(!product.getRef().startsWith("R00"))
            throw new Exception("invalid product reference");
        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Product product) throws Exception {
        productRepository.delete(product);
        return product;
    }

    @Override
    public Product getProduct(String ref) throws Exception {
        return productRepository.findById(ref).get();
    }

    @Override
    public List<Product> getProducts() throws Exception {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByLabel(String label) throws Exception {
        return productRepository.findProductByLabelStartingWith(label);
    }
}
