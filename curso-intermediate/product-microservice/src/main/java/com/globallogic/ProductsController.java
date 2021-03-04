package com.globallogic;

import com.globallogic.domain.Product;
import com.globallogic.domain.ProductRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.inject.Inject;
import java.util.Optional;

@Controller("/")
public class ProductsController {

    @Inject
    ProductRepository productRepository;

    @Get(uri="/products", produces = MediaType.APPLICATION_JSON)
    public ProductsDTO getProducts() {

        Iterable<Product> productsList = productRepository.findAll();
        ProductsDTO products = new ProductsDTO();
        productsList.forEach(product -> {
            ProductDTO newProduct = ProductDTO.builder().name(product.getName()).description(product.getDescription()).active(product.isActive()).quantity(product.getQuantity()).build();
            products.getProducts().add(newProduct);
        });
        return products;
    }

    @Get(uri="/products/{id}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse getProduct(Long id) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            ProductDTO foundProduct = ProductDTO.builder().name(product.getName()).description(product.getDescription()).active(product.isActive()).quantity(product.getQuantity()).build();
            return HttpResponse.ok(foundProduct);
        } else {
            return HttpResponse.notFound();
        }

    }

    @Post(uri="/products", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public ProductDTO createProducts(@RequestBody ProductDTO product){

        Product productEntity = new Product();
        productEntity.setName(product.getName());
        productEntity.setActive(product.isActive());
        productEntity.setDescription(product.getDescription());
        productEntity.setQuantity(product.getQuantity());
        productRepository.save(productEntity);
        return product;
    }

    @Delete(uri="/products/{id}", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public HttpResponse deleteProduct(Long id){

        productRepository.deleteById(id);
        return HttpResponse.ok();
    }


}
