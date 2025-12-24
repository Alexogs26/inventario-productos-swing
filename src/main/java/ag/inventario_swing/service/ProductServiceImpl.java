package ag.inventario_swing.service;

import ag.inventario_swing.model.Product;
import ag.inventario_swing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> listProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    @Transactional(readOnly = true)
    public Product getProductBySku(String sku) {
        return productRepository.findBySku(sku).orElse(null);
    }

    @Override
    @Transactional
    public void saveProduct(Product product) {
        if (product.getId() == null && productRepository.existsBySku(product.getSku())) {
            throw new RuntimeException("El SKU " + product.getSku() + " ya existe");
        } else {
            productRepository.save(product);
        }
    }

    @Override
    @Transactional
    public void deleteProduct(Product product) {

        productRepository.delete(product);
    }
}
