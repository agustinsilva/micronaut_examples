package ar.com.globallogic;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.tracing.annotation.ContinueSpan;
import io.micronaut.tracing.annotation.NewSpan;

import javax.inject.Inject;

@Controller("/")
@LogParameters
public class BffController {

    @Value("${products-message:default}")
    String configTest;

    @Inject
    IProductClient courseClient;

    @NewSpan("products")
    @Produces(MediaType.APPLICATION_JSON)
    @Get(uri="/products", produces = MediaType.APPLICATION_JSON)
    public ProductsDTO getProducts() {
        System.out.println("Prueba de config: " + configTest);
        return courseClient.getProducts();
    }

    @NewSpan("product")
    @Produces(MediaType.APPLICATION_JSON)
    @Get(uri="/products/{id}", produces = MediaType.APPLICATION_JSON)
    public ProductDTO getProductsById(Long id) {
        return courseClient.getProductsById(id);
    }


}
