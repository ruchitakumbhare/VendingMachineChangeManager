import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ChangeCalculator {
    Collection<Coin> calculateChange(Map<Coin, Integer> inventory, double productPrice, List<Coin> coins) throws Exception;

}
