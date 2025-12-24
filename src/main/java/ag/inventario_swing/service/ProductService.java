package ag.inventario_swing.service;

import ag.inventario_swing.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> listProducts();

    public Product getProductBySku(String sku);

    public void saveProduct(Product product);

    public void deleteProduct(Product product);
}
