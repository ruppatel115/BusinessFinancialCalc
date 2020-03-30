import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InventoryTester {

    @Test
    public void InventoryConstructorTest(){
        Inventory testInventory = new Inventory();

        assertEquals(new HashMap<String, Item>(), testInventory.getInventory());
    }

    @Test
    public void addItemTest() throws ItemAlreadyExistsException {
        Inventory testInventory = new Inventory();

        Item testItem1 = new Item("0001", 5, "Buns", 1.00);
        Item testItem2 = new Item("0002", 5, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 5, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 5, "Burger Patty", 4.00);
        Item testItem5 = new Item("0004", 5, "Mustard", 4.00);
        Item testItem6 = new Item("0006", 5, "Ketchup", -.01);
        Item testItem7 = new Item("0007", 5, "Mushrooms", -50.25);
        Item testItem8 = new Item("0008", 5, "Pickles", 25.312);
        Item testItem9 = new Item("0009", 5, "Mayonnaise", .566970);

        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);
        testInventory.addItem(testItem4);
        assertEquals(testInventory.getInventory().get("0001"), testItem1);
        assertEquals(testInventory.getInventory().get("0002"), testItem2);

        //ID already exists
        assertThrows(IllegalArgumentException.class, () -> testInventory.addItem(testItem5));

        //Invalid costs
        assertThrows(IllegalArgumentException.class, () -> testInventory.addItem(testItem6));
        assertThrows(IllegalArgumentException.class, () -> testInventory.addItem(testItem7));
        assertThrows(IllegalArgumentException.class, () -> testInventory.addItem(testItem8));
        assertThrows(IllegalArgumentException.class, () -> testInventory.addItem(testItem9));


    }

    @Test
    public void removeItemTest() throws ItemDoesNotExistsException, ItemAlreadyExistsException {
        Inventory testInventory = new Inventory();

        Item testItem1 = new Item("0001", 5, "Buns", 1.00);
        Item testItem2 = new Item("0002", 5, "Lettuce", 0.50);

        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);

        testInventory.removeItem("0001");
        testInventory.removeItem("0002");
        assertNull(testInventory.getItem("0001"));
        assertNull(testInventory.getItem("0002"));

        //item does not exist
        assertThrows(IllegalArgumentException.class, () -> testInventory.removeItem("003"));
    }

    @Test
    public void getItemCostTest() throws ItemDoesNotExistsException, ItemAlreadyExistsException {
        Inventory testInventory = new Inventory();
        Item testItem1 = new Item("0001", 5, "Tomatoes", 0.69);
        Item testItem2 = new Item("0002", 5, "Burger Patty", 4.00);
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);

        assertEquals(testInventory.getInventory().get("0001").getCost(), testInventory.getItemCost("0001"));
        assertEquals(testInventory.getInventory().get("0002").getCost(), testInventory.getItemCost("0002"));

        //item does not exist
        assertThrows(IllegalArgumentException.class, () -> testInventory.getItemCost("003"));
    }


    @Test
    public void buyMoreItemsTest() throws ItemDoesNotExistsException, ItemAlreadyExistsException {
        Inventory testInventory = new Inventory();

        Item testItem1 = new Item("0001", 5, "Buns", 1.00);
        Item testItem2 = new Item("0002", 5, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 5, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 5, "Burger Patty", 4.00);
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);
        testInventory.addItem(testItem4);


        assertEquals(10, testInventory.buyMoreProducts("0001", 5));
        assertEquals(15, testInventory.buyMoreProducts("0004", 10));
        assertEquals(105, testInventory.buyMoreProducts("0002", 100));
        assertEquals(9, testInventory.buyMoreProducts("0003", 4));
        assertThrows(IllegalArgumentException.class, () ->testInventory.buyMoreProducts("0006", 10));
    }

    @Test
    public void getProductInventoryTest() throws ItemAlreadyExistsException, EmptyInventoryException {
        Inventory testInventory = new Inventory();
        //test that it throws inventory empty exception
        assertThrows(EmptyInventoryException.class, () -> testInventory.getProductInventory());

        //test with  items
        Item testItem1 = new Item("0001", 20, "Burger Patty", 1.00);
        Item testItem2 = new Item("0002", 15, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 8, "Tomatoes", 0.69);
        Item testItem4 = new Item("0004", 10, "Buns", 4.00);
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);
        testInventory.addItem(testItem3);
        testInventory.addItem(testItem4);

        String expected = "Buns: 10\n";
        expected += "Lettuce: 15\n";
        expected += "Tomatoes: 8\n";
        expected += "Burger Patty: 20\n";

        assertEquals(expected, testInventory.getProductInventory());
    }

    @Test
    public void getItemCountTest() throws ItemAlreadyExistsException, ItemDoesNotExistsException {
        Inventory testInventory = new Inventory();
        //test with  items
        Item testItem1 = new Item("0001", 20, "Burger Patty", 1.00);
        Item testItem2 = new Item("0002", 15, "Lettuce", 0.50);
        testInventory.addItem(testItem1);
        testInventory.addItem(testItem2);


        assertEquals(20, testInventory.getItemCount("0001"));
        assertEquals(15, testInventory.getItemCount("0002"));

        testInventory.removeItem("0001");

        //test that it throws exception
        assertThrows(ItemDoesNotExistsException.class, () -> testInventory.getItemCount("0001"));
    }
}
