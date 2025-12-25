package ag.inventario_swing.gui;

import ag.inventario_swing.model.Product;
import ag.inventario_swing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.List;

@Component
public class InventoryForm extends JFrame{
    private JPanel mainPanel;
    private JTable productsTable;
    private JTextField textSku;
    private JTextField textName;
    private JTextField textPrice;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton clearButton;
    private final ProductService productService;
    private DefaultTableModel modelTableProducts;
    private Long idProduct;

    @Autowired
    public InventoryForm(ProductService productService) {
        this.productService = productService;
        initializeForm();
        saveButton.addActionListener(actionEvent -> saveProduct());
        productsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loadSelectProduct();
            }
        });
        clearButton.addActionListener(actionEvent -> clearForm());
        deleteButton.addActionListener(actionEvent -> deleteProduct());
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

    private void saveProduct() {
        if (textSku.getText().isEmpty()) {
            displayMessege("Ingresa un sku");
            textSku.requestFocusInWindow();
            return;
        }
        if (textName.getText().isEmpty()) {
            displayMessege("Ingresa un nombre");
            textName.requestFocusInWindow();
            return;
        }
        if (textPrice.getText().isEmpty()) {
            displayMessege("Ingresa un precio");
            textPrice.requestFocusInWindow();
            return;
        }

        BigDecimal price = BigDecimal.ZERO;
        try {
            price = new BigDecimal(textPrice.getText());

            if (price.signum() <= 0) {
                displayMessege("Ingresa un numero positivo");
                textPrice.requestFocusInWindow();
                return;
            }
        } catch (NumberFormatException e) {
            displayMessege("Ingresa solo numeros");
            return;
        }

        String sku = textSku.getText();
        String name = textName.getText();
        Product product = new Product(idProduct, sku, name, price);
        productService.saveProduct(product);

        if (idProduct == null) {
            displayMessege("Se agrego el producto");
        } else {
            displayMessege("Se modifico el producto");
        }
        clearForm();
        listProducts();
    }

    private void loadSelectProduct() {
        var row = productsTable.getSelectedRow();

        if (row != -1) {
            String id = productsTable.getModel().getValueAt(row, 0).toString();
            idProduct = Long.parseLong(id);
            String sku = productsTable.getModel().getValueAt(row, 1).toString();
            textSku.setText(sku);
            String name = productsTable.getModel().getValueAt(row, 2).toString();
            textName.setText(name);
            String price = productsTable.getModel().getValueAt(row, 3).toString();
            textPrice.setText(price);
        }
    }

    private void deleteProduct() {
        var row = productsTable.getSelectedRow();

        if (row != -1) {
            String sku = textSku.getText();
            Product product = productService.getProductBySku(sku);

            if (product != null) {
                productService.deleteProduct(product);
                displayMessege("Producto eliminado correctamente");
            } else {
                displayMessege("Producto no encontrado");
            }
        } else {
            displayMessege("Selecciona un producto para eliminar");
        }

        clearForm();
        listProducts();
    }

    private void clearForm() {
        textSku.setText("");
        textName.setText("");
        textPrice.setText("");
        idProduct = null;
        productsTable.getSelectionModel().clearSelection();
    }

    private void displayMessege(String messege) {
        JOptionPane.showMessageDialog(this, messege);
    }
}
