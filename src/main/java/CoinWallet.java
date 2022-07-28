import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.DoubleStream;

public class CoinWallet {
    private ChangeCalculator changeCalculator;

    private InventoryManager inventoryManager;

    public CoinWallet(InventoryManager inventoryManager, ChangeCalculator changeCalculator)
    {
        this.changeCalculator = changeCalculator;
        this.inventoryManager= inventoryManager;
    }

    public CoinWallet()
    {

    }

    public void LoadCoins(Coin coin, Integer count){
        inventoryManager.updateInventory(coin, count);
    }

    public Collection<Coin> Transact(double productPrice, List<Coin> coins) throws Exception {
        try {
            Collection<Coin> coinsToReturn = this.changeCalculator.calculateChange(inventoryManager.getInventory(), productPrice, coins);
            //Update Inventory
            inventoryManager.updateInventory(coins, coinsToReturn);
            return coinsToReturn;
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    public void setChangeCalculator(ChangeCalculator changeCalculator) {
        this.changeCalculator = changeCalculator;
    }

    public void setInventoryManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public Map<Coin, Integer> getInventory() {
        return inventoryManager.getInventory();
    }
}
