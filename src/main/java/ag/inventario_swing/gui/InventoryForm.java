package ag.inventario_swing.gui;

import ag.inventario_swing.service.ProductService;
import ag.inventario_swing.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class InventoryForm extends JFrame{
    private JPanel mainPanel;
    ProductService productService;

    @Autowired
    public InventoryForm(ProductServiceImpl productService) {
        this.productService = productService;
        initializeForm();
    }

    private void initializeForm() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
    }
}
