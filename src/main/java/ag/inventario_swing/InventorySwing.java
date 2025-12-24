package ag.inventario_swing;

import com.formdev.flatlaf.FlatDarculaLaf;
import ag.inventario_swing.gui.InventoryForm;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class InventorySwing {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();

        ConfigurableApplicationContext springContext =
                new SpringApplicationBuilder(InventorySwing.class)
                        .headless(false)
                        .web(WebApplicationType.NONE)
                        .run(args);

        SwingUtilities.invokeLater(() -> {
            InventoryForm inventoryForm = springContext.getBean(InventoryForm.class);

            inventoryForm.setVisible(true);
        });
    }
}
