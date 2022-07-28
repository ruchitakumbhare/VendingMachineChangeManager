import customException.InsufficientAmountException;
import customException.InsufficientCoinException;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DefaultChangeCalculatorTest {
    @Test
    public void calculateChangeTest() throws Exception {
        Map<Coin, Integer> inventory = new HashMap<Coin, Integer>();

        inventory.put(new Coin("10 Cent", 0.10D), 5);
        inventory.put(new Coin("50 Cent", 0.50D), 5);
        inventory.put(new Coin("2 Pound", 2.00D), 2);

        List<Coin> userCoinsList = new ArrayList<Coin>();
        userCoinsList.add(new Coin("2 Pound", 2.00D));
        userCoinsList.add(new Coin("2 Pound", 2.00D));

        DefaultChangeCalculator defChangeCalc = new DefaultChangeCalculator();
        ArrayList<Coin> coinsToReturn = (ArrayList<Coin>) defChangeCalc.calculateChange(inventory, 3.10, userCoinsList);

        Double sum = Utils.round(coinsToReturn.stream().flatMapToDouble(a -> DoubleStream.of(a.getValue())).
                reduce(0, (a, b) -> a + b));

        assertEquals(5, coinsToReturn.size());
        assertEquals(0.90, sum);
    }

    @Test()
    public void CalculateChangeForInsufficientCoinTest(){
        Throwable exception = assertThrows(
                InsufficientCoinException.class, () -> {
                    Map<Coin, Integer> inventory = new HashMap<Coin, Integer>();

                    inventory.put(new Coin("10 Cent", 0.10D), 5);
                    inventory.put(new Coin("2 Pound", 2.00D), 2);

                    List<Coin> userCoinsList = new ArrayList<Coin>();
                    userCoinsList.add(new Coin("2 Pound", 2.00D));
                    userCoinsList.add(new Coin("2 Pound", 2.00D));

                    DefaultChangeCalculator defChangeCalc = new DefaultChangeCalculator();
                    ArrayList<Coin> coinsToReturn = (ArrayList<Coin>) defChangeCalc.calculateChange(inventory, 3.10, userCoinsList);
                }
        );
        assertEquals("Change not available.", exception.getMessage());
    }

    @Test()
    public void CalculateChangeForInsufficientAmountTest(){
        Throwable exception = assertThrows(
                InsufficientAmountException.class, () -> {
                    Map<Coin, Integer> inventory = new HashMap<Coin, Integer>();

                    inventory.put(new Coin("10 Cent", 0.10D), 5);
                    inventory.put(new Coin("2 Pound", 2.00D), 2);

                    List<Coin> userCoinsList = new ArrayList<Coin>();
                    userCoinsList.add(new Coin("2 Pound", 2.00D));
                    userCoinsList.add(new Coin("2 Pound", 2.00D));

                    DefaultChangeCalculator defChangeCalc = new DefaultChangeCalculator();
                    ArrayList<Coin> coinsToReturn = (ArrayList<Coin>) defChangeCalc.calculateChange(inventory, 4.10, userCoinsList);
                }
        );
        assertEquals("Insufficient amount received.", exception.getMessage());
    }

}