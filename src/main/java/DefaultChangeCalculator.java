import customException.InsufficientAmountException;
import customException.InsufficientCoinException;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class DefaultChangeCalculator implements ChangeCalculator{
    @Override
    public Collection<Coin> calculateChange(Map<Coin, Integer> inventory, double productPrice, List<Coin> coins) throws Exception {
        Comparator<? super Map.Entry<Coin, Integer>> ReverseComparator = new ReverseComparator();
        Stream<Map.Entry<Coin, Integer>> sortedCoinsInventory = inventory.entrySet().stream().sorted(ReverseComparator);
        double total = coins.stream().flatMapToDouble(a -> DoubleStream.of(a.getValue())).reduce(0, (a, b) -> a + b);
        AtomicReference<Double> changeToReturn = new AtomicReference<>(total - productPrice);
        ArrayList<Coin> coinsToReturn = new ArrayList<>();
        if (changeToReturn.get() < 0) throw new InsufficientAmountException("Insufficient amount received.");
            if (changeToReturn.get() != 0) {
                sortedCoinsInventory.forEach((entry) -> {
                    if (changeToReturn.get() != 0) {
                        Integer availableCount = entry.getValue();
                        Coin coin = entry.getKey();
                        for (int i = availableCount; i > 0; i--) {
                            if (coin.getValue() <= Utils.round(changeToReturn.get())) {
                                coinsToReturn.add(coin.createNew());
                                double val = changeToReturn.get() - coin.getValue();
                                changeToReturn.set(Utils.round(val));
                            }
                        }
                    }
                });
            }

        if(changeToReturn.get() !=0) {
            throw new InsufficientCoinException("Change not available.");
        }
        return coinsToReturn;
    }

    class ReverseComparator implements Comparator<Map.Entry<Coin, Integer>> {
        @Override
        public int compare(Map.Entry<Coin, Integer> o1, Map.Entry<Coin, Integer> o2) {
            return o2.getKey().getValue().compareTo(o1.getKey().getValue());
        }
    }
}
