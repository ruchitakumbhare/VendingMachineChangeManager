import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

class CoinWalletTest {

    @Test
    void loadCoinsTest() {
        InventoryManager inventory = Mockito.mock(InventoryManager.class);
        ChangeCalculator changeCalculator = Mockito.mock(ChangeCalculator.class);
        CoinWallet coinWallet = new CoinWallet(inventory, changeCalculator);
        Coin coinsToLoad = new Coin("5 Cent", 0.05D);
        Integer coinsCount = 5;
        coinWallet.LoadCoins(coinsToLoad, 5);
        org.mockito.Mockito.verify(inventory, Mockito.times(1)).updateInventory(coinsToLoad, coinsCount);
        org.mockito.Mockito.verifyZeroInteractions(changeCalculator);
    }

    @Test
    void transactTest() throws Exception {
        InventoryManager inventoryManager = Mockito.mock(InventoryManager.class);
        ChangeCalculator changeCalculator = Mockito.mock(ChangeCalculator.class);
        CoinWallet coinWallet = new CoinWallet(inventoryManager, changeCalculator);
        Coin coin = new Coin("5 Cent", 0.05D);
        Coin coin2 = new Coin("5 Cent", 0.05D);
        Map<Coin, Integer> inventory = Map.of(coin, 0);
        List<Coin> changeToReturn = List.of(coin);
        List<Coin> coinsSubmitted = List.of(coin2);
        when(coinWallet.getInventory()).thenReturn(inventory);
        when(changeCalculator.calculateChange(eq(coinWallet.getInventory()), anyDouble(), anyList())).thenReturn(changeToReturn);
        coinWallet.Transact(0.06, coinsSubmitted);
        verify(changeCalculator, times(1)).calculateChange(inventory, 0.06, coinsSubmitted);
        verify(inventoryManager, times(1)).updateInventory(coinsSubmitted, changeToReturn);
    }
}