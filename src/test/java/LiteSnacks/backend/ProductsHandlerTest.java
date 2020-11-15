package LiteSnacks.backend;

import LiteSnacks.UI.Products;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ProductsHandlerTest {

    ProductsHandlerTest() {
        removeRoot();
    }

    private void removeRoot() {
        File root = new File(ResourceHandler.root);
        if (root.exists()) {
            ResourceHandlerTest.deleteDirectory(root);
        }
    }

    @Test public void getAllItemsTest() {
        removeRoot();
        ProductsHandler ph = new  ProductsHandler();
        assertNotNull(ph.getAllItems());
        assertEquals(5, ph.getAllItems().entrySet().size());
    }

    @Test public void getAllItemsForCategoryTest() {
        removeRoot();
        ProductsHandler ph = new  ProductsHandler();
        assertNotNull(ph.getCategories());
        assertEquals(5, ph.getCategories().size());
    }

    @Test public void increaseQuantityOfAnItem() {
        removeRoot();
        List<Item> chipItems;
        ProductsHandler ph = new ProductsHandler();

        assertNotNull(chipItems = ph.getItemsForCategory("Chips"));
        assertNotNull(chipItems.get(0));
        assertNotNull(chipItems.get(0).getQuantity());
        assertEquals(7, chipItems.get(0).getQuantity());
        
        String name = chipItems.get(0).getName();
        ph.editQuantity(name, 2);

        assertNotNull(chipItems = ph.getItemsForCategory("Chips"));
        assertNotNull(chipItems.get(0));
        assertNotNull(chipItems.get(0).getQuantity());
        assertEquals(name, chipItems.get(0).getName());
        assertEquals(9, chipItems.get(0).getQuantity());
        
        removeRoot();
    }

    @Test public void decreaseQuantityOfAnItem() {
        removeRoot();
        List<Item> drinkItems;

        ProductsHandler ph = new ProductsHandler();
        assertNotNull(drinkItems = ph.getItemsForCategory("Drinks"));
        assertNotNull(drinkItems.get(2));
        assertNotNull(drinkItems.get(2).getQuantity());
        assertEquals(7, drinkItems.get(2).getQuantity());
        
        String name = drinkItems.get(2).getName();
        ph.editQuantity(name, -5);

        assertNotNull(drinkItems = ph.getItemsForCategory("Drinks"));
        assertNotNull(drinkItems.get(2));
        assertNotNull(drinkItems.get(2).getQuantity());
        assertEquals(name, drinkItems.get(2).getName());
        assertEquals(2, drinkItems.get(2).getQuantity());

        removeRoot();
    }

    @Test
    public void getQuantityTest() {
        File file = ResourceHandler.getProducts();
        ProductsHandler ph = new ProductsHandler();

        // clone file
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail();
        }
//        StringBuilder document = new StringBuilder();
        String[] line;
        String name = null;
        String quantity = null;
        while(sc.hasNextLine()) {
            line = sc.nextLine().split(",");
            if(line.length > 1){
                name = line[0];
                quantity = line[1];
            }
        }
        assertNotNull(name);
        assertNotNull(quantity);
        assertEquals(Integer.parseInt(quantity), ph.getQuantitiy(name));

    }

}