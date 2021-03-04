package ar.com.globallogic;

import io.micronaut.http.HttpResponse;
import io.micronaut.retry.annotation.Fallback;

@Fallback
@LogParameters
public class NoProduct implements IProductClient{

    @Override
    public ProductsDTO getProducts() {
        ProductsDTO products = new ProductsDTO();
        return products;
    }

    @Override
    public ProductDTO getProductsById(long id) {
        ProductDTO product = new ProductDTO();
        product.setName("Producto dummy");
        product.setActive(false);
        product.setDescription("Me quede sin conexion");
        product.setQuantity(999);
        return product;
    }
}
