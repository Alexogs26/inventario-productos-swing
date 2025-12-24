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
        InventoryApp();
    }

    private void InventoryApp() {
        logger.info("\n*** Inventario de Productos ***\n");
        var console = new Scanner(System.in);
        int option = 0;
        listProducts();

        do {
            try {
                option = displayMenu(console);
                executeOption(option, console);
            } catch (NumberFormatException e) {
                logger.info("\nError: Ingrese solo numeros" + e.getMessage() + "\n");
            }
        } while (option != 6);
    }

    private int displayMenu(Scanner console) {
        logger.info("""
                \n\nMenu:
                1. Listar Productos
                2. Buscar producto por sku
                3. Agregar productos
                4. Modificar productos
                5. Eliminar productos
                6. Salir del sistema
                Elije una opcion:\s""");
        return Integer.parseInt(console.nextLine());
    }

    private void executeOption(int option, Scanner console) {
        switch (option) {
            case 1 -> listProducts();
            case 2 -> getProductBySku(console);
            case 3 -> addProduct(console);
            case 4 -> updateProduct(console);
            case 5 -> deleteProduct(console);
            case 6 -> logger.info("\nCerrando el sistema...\n");
            default -> logger.info("\nOpcion invalida\n");
        }
    }

    private void listProducts() {
        logger.info("\nListando Productos:\n");
        List<Product> products = productService.listProducts();
        products.forEach(product -> logger.info(product.toString() + "\n"));
    }

    private void getProductBySku(Scanner console) {
        logger.info("\nIngresa el sku del producto a buscar: ");
        Integer sku = Integer.parseInt(console.nextLine());
        if (productService.getProductBySku(sku) != null) {
            logger.info("\n" + productService.getProductBySku(sku).toString() + "\n");
        } else {
            logger.info("\nProducto no encontrado con sku " + sku + "\n");
        }
    }

    private void addProduct(Scanner console) {
        logger.info("\nIntroduce el nombre de el producto: ");
        String name = console.nextLine();
        logger.info("\nIntroduce el precio de el producto: ");
        BigDecimal price = new BigDecimal(console.nextLine());

        Product product = Product.builder().name(name).price(price).build();
        try {
            productService.saveProduct(product);
            logger.info("\nProducto guardado correctamente: " + product.toString() + "\n");
        } catch (Exception e) {
            logger.info("\nError al guardar el producto: " + e.getMessage() + "\n");
        }
    }

    private void updateProduct(Scanner console) {
        logger.info("\nIngresa el sku del producto a modificar: ");
        Integer sku = Integer.parseInt(console.nextLine());
        Product product = productService.getProductBySku(sku);

        try {
            if (product != null) {
                logger.info("\nIntroduce el nombre nuevo: ");
                String name = console.nextLine();
                logger.info("\nIntoduce el precio nuevo: ");
                BigDecimal price = new BigDecimal(console.nextLine());

                product.setName(name);
                product.setPrice(price);

                productService.saveProduct(product);
                logger.info("\nProducto modificado: " + product + "\n");
            } else {
                logger.info("\nProducto no encontrado con sku: " + sku + "\n");
            }
        } catch (Exception e) {
            logger.info("\nError al modificar el producto: " + e.getMessage() + "\n");
        }
    }

    private void deleteProduct(Scanner console) {
        logger.info("\nIngresa el sku del producto que deseas borrar: ");
        Integer sku = Integer.parseInt(console.nextLine());

        try {
            Product product = productService.getProductBySku(sku);

            if (product != null) {
                productService.deleteProduct(product);
                logger.info("\nProducto eliminado correctamente\n");
            } else {
                logger.info("\nProducto no encontrado con sku: " + sku + "\n");
            }
        } catch (Exception e) {
            logger.info("\nError al eliminar el producto: " + e.getMessage() + "\n");
        }
    }
}
