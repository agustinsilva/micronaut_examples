package ar.com.globallogic;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.retry.annotation.CircuitBreaker;

@Client(id = "product-microservice")
@LogParameters
public interface IProductClient {

    @Get("/products")
    @CircuitBreaker(delay = "5s", attempts = "2", multiplier = "1", reset = "300s")
    ProductsDTO getProducts();

    @Get("/products/{id}")
    @CircuitBreaker(delay = "5s", attempts = "2", multiplier = "1", reset = "300s")
    ProductDTO getProductsById(@PathVariable long id);
}
