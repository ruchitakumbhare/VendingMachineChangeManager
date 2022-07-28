import java.util.*;

public class DefaultInventoryManager implements InventoryManager{
    private Map<Coin, Integer> coinsInventory = new HashMap<>();

    @Override
    public Map<Coin, Integer> getInventory() {
        return Collections.unmodifiableMap(coinsInventory);
    }

    @Override
    public void updateInventory(Collection<Coin> coinsToAdd, Collection<Coin> coinsToRemove) {
        coinsToAdd.forEach((coin) -> {
            coinsInventory.computeIfAbsent(coin, (key) -> 0);
            coinsInventory.computeIfPresent(coin, (key, value) -> value + 1);
        });
        coinsToRemove.forEach((coin) -> {
            coinsInventory.computeIfAbsent(coin, (key) -> 0);
            coinsInventory.computeIfPresent(coin, (key, value) -> value - 1);
        });
    }

    @Override
    public void updateInventory(Coin coin, Integer count) {
        coinsInventory.computeIfAbsent(coin, (key) -> 0);
        coinsInventory.computeIfPresent(coin, (key, value) -> value + count);
    }
}
