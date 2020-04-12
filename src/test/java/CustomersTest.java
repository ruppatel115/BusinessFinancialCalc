import org.junit.jupiter.api.Test;
import javax.management.OperationsException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CustomersTest {

    @Test
    public void orderTest(){
        BusinessTracker bus1 = new BusinessTracker("Business 1");
        Inventory testInventory = new Inventory();



        //create items (ingredients)
        Item testItem1 = new Item("0001", 5, "Buns", 1.00);
        Item testItem2 = new Item("0002", 5, "Lettuce", 0.50);
        Item testItem3 = new Item("0003", 5, "Meat", 2.69);
        Item testItem4 = new Item("0004", 5, "Potatoes", 1.50);
        Item testItem5 = new Item("0005", 5, "Cheese", .50);


        ArrayList<Item> burgerIngredients = new ArrayList<>();
        burgerIngredients.add(testItem1);
        burgerIngredients.add(testItem2);
        burgerIngredients.add(testItem3);

        //create menuItem object (burger)
        MenuItem burger = new MenuItem("001", "Burger", 5.00);
        MenuItem fries = new MenuItem("002", "Fries", 3.00);


        Customers customer1 = new Customers("001", "Bob");

        Customers customer2 = new Customers("004", "Billy");

        Customers customer3 = new Customers("006", "Jim");

        Customers customer4 = new Customers("007", "Game");


        customer1.order(burger);
        customer1.order(fries);



        customer2.order(burger);
        customer2.order(burger);
        customer2.order(burger);

        customer3.order(fries);

        customer4.order(burger);
        customer4.order(fries);
        customer4.order(fries);
        customer4.order(burger);

        assertEquals("001" , customer1.getOrderId());
        assertEquals("004" , customer2.getOrderId());
        assertEquals("006" , customer3.getOrderId());

        assertEquals("Bob" , customer1.getCustomerName());
        assertEquals("Billy" , customer2.getCustomerName());
        assertEquals("Jim" , customer3.getCustomerName());





        assertEquals("Burger, Fries" , customer1.getItems());
        assertEquals("Burger, Burger, Burger" , customer2.getItems());
        assertEquals("Fries" , customer3.getItems());
        assertEquals("Burger, Fries, Fries, Burger" , customer4.getItems());


        assertEquals(8, customer1.getTotal());
        assertEquals(15 , customer2.getTotal());
        assertEquals(3, customer3.getTotal());
        assertEquals(16, customer4.getTotal());






    }

  }
