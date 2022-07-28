import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DefaultInventoryManagerTest {

    @Test
    void updateInventoryTest() {
        InventoryManager inventory = new DefaultInventoryManager();
        Map<Coin, Integer> result = new HashMap<Coin, Integer>();
        int count5Cents = 5;
        Coin coin = new Coin("5 Cent", 0.05D);
        result.put(coin, count5Cents);

        inventory.updateInventory(coin, count5Cents);
        Map<Coin, Integer> getInventory = inventory.getInventory();

        assertTrue(getInventory.equals(result));
    }
}