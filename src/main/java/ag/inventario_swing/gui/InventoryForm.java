package ag.inventario_swing.gui;

import ag.inventario_swing.model.Product;
import ag.inventario_swing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

@Component
public class InventoryForm extends JFrame{
    private JPanel mainPanel;
    private JTable productsTable;
    private JTextField textSku;
    private JTextField textName;
    private JTextField textPrice;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    private final ProductService productService;
    private DefaultTableModel modelTableProducts;

    @Autowired
    public InventoryForm(ProductService productService) {
        this.productService = productService;
        initializeForm();
    }

    private void initializeForm() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
         modelTableProducts = new DefaultTableModel(0, 4);
         String[] tableHeaders = {"Id", "Sku", "Nombre", "Precio"};
         modelTableProducts.setColumnIdentifiers(tableHeaders);
         productsTable = new JTable(modelTableProducts);

         listProducts();
    }

    private void listProducts() {
        modelTableProducts.setRowCount(0);
        List<Product> products = productService.listProducts();
        products.forEach(product -> {
            Object[] rowProduct = {
                    product.getId(),
                    product.getSku(),
                    product.getName(),
                    product.getPrice()
            };
            modelTableProducts.addRow(rowProduct);
        });
    }
}
