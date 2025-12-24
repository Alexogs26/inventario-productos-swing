package ag.inventario_swing;

import ag.inventario_swing.model.Product;
import ag.inventario_swing.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


@SpringBootApplication
public class InventarioSpringApplication implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    private static final Logger logger =
            LoggerFactory.getLogger(InventarioSpringApplication.class);

	public static void main(String[] args) {
        logger.info("\nIniciando la app\n");
		SpringApplication.run(InventarioSpringApplication.class, args);
        logger.info("\nApp finalizada correctamente\n");
	}

    @Override
    public void run(String... args) throws Exception {

    }

}
