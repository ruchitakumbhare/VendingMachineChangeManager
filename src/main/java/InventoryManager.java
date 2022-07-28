import java.util.Collection;
import java.util.Map;

public interface InventoryManager {
    Map<Coin, Integer> getInventory();

    void updateInventory(Collection<Coin> coinsToAdd, Collection<Coin> coinsToRemove);

    void updateInventory(Coin coin, Integer count);
}
