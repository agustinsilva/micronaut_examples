package ar.com.globallogic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class ProductsDTO {

    private List<ProductDTO> products;

    public ProductsDTO() {
        this.products = new ArrayList<>();
    }
}
