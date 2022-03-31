package suptech.miag.tp4.product;

import java.util.List;

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
        return null;
    }

    @Override
    public Product getProduct(String ref) throws Exception {
        return null;
    }

    @Override
    public List<Product> getProducts(String ref) throws Exception {
        return null;
    }

    @Override
    public List<Product> getProductsByLabel(String label) throws Exception {
        return null;
    }
}
