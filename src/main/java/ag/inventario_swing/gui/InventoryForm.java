package ag.inventario_swing.gui;

import ag.inventario_swing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@Component
public class InventoryForm extends JFrame{
    private JPanel mainPanel;
    private JTable productsTable;
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
        setSize(900, 700);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
         modelTableProducts = new DefaultTableModel(0, 4);
         String[] tableHeaders = {"Id", "Sku", "Nombre", "Precio"};
         modelTableProducts.setColumnIdentifiers(tableHeaders);
         productsTable = new JTable(modelTableProducts);
    }
}
