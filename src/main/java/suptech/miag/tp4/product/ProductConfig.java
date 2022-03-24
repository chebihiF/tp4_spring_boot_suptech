package suptech.miag.tp4.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    private final ProductRepository productRepository;

    public ProductConfig(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Bean
    CommandLineRunner init(){
        return args -> {
            productRepository.save(new Product("R001","Product1",300,100));
            productRepository.save(new Product("R002","Product2",500,30));
            productRepository.save(new Product("R003","Product3",800,20));
            productRepository.save(new Product("R004","Product4",600,10));

            productRepository.findProductByQuantityIsGreaterThan(30).forEach
                    (product -> System.out.println(product.getRef()));

        };
    }
}
