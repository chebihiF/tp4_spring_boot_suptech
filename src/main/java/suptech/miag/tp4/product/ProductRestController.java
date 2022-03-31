package suptech.miag.tp4.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductRestController {

    private final IProductService service;

    public ProductRestController(IProductService service) {
        this.service = service;
    }

    @PutMapping("/{ref}")
    public Product updateProduct(@PathVariable String ref, @RequestBody Product product){
        try {
            return service.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product){
        try {
            return service.addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @DeleteMapping("/{ref}")
    public Product deleteByRef(@PathVariable String ref){
        try {
            return service.deleteProduct(service.getProduct(ref));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @GetMapping("/{ref}")
    public Product findByRef(@PathVariable String ref){
        try {
            return service.getProduct(ref);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @GetMapping(path = "/page")
    public Page<Product> findAllByPages(
            @RequestParam(name = "size", defaultValue = "20") int size,
            @RequestParam(name="num", defaultValue = "1") int num){
        try {
            return service.getProductsByPages(PageRequest.of(num,size));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
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
