package suptech.miag.tp4.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products/")
public class ProductRestController {

    private final IProductService service;

    public ProductRestController(IProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> findAll(){
        try {
            return service.getProducts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }
}
