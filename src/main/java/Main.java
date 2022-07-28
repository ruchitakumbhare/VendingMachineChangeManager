import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Coin> coinToCommandMap =  new HashMap<>(){{
            put(1,SupportedDenominations.Coins.get(0));
            put(2,SupportedDenominations.Coins.get(1));
            put(3,SupportedDenominations.Coins.get(2));
            put(4,SupportedDenominations.Coins.get(3));
            put(5,SupportedDenominations.Coins.get(4));
            put(6,SupportedDenominations.Coins.get(5));
        }};
        CoinWallet coinsWallet = new CoinWallet();
        coinsWallet.setChangeCalculator(new DefaultChangeCalculator());
        coinsWallet.setInventoryManager(new DefaultInventoryManager());
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n ========= Coin Manager System ========= \n");
        for (Coin coin : SupportedDenominations.Coins) {
            System.out.println(MessageFormat.format("Enter total number of {0} coins to load :", coin.getName()));
            Integer count = scanner.nextInt();
            coinsWallet.LoadCoins(coin, count);
        }
        System.out.println("** Coins wallet initialized **");
        while (true) {
            try {
                System.out.println("\n\nPlease enter product value");
                Double productValue = scanner.nextDouble();
                System.out.println("Please enter coins..");
                System.out.println("Select\n1 for 5 Pence\n2 for 10 Pence\n3 for 20 Pence\n4 for 50 Pence\n5 for 1 Pound" +
                        "\n6 for 2 Pound\n7 to execute order");
                boolean keepAddingCoin = true;
                ArrayList<Coin> coins = new ArrayList<>();
                Double total = 0D;
                while (keepAddingCoin) {
                    Integer command = scanner.nextInt();
                    if (command == 7){
                        break;
                    }
                    coins.add(coinToCommandMap.get(command).createNew());
                    total = Utils.round(coins.stream().flatMapToDouble(a -> DoubleStream.of(a.getValue())).
                            reduce(0, (a, b) -> a + b));
                    System.out.println("Received amount " + total);
                    if (total >= productValue)
                        break;
                }

                System.out.println("Processing order... \nExpected change " + Utils.round (total - productValue));
                Collection<Coin> change = coinsWallet.Transact(productValue, coins);
                Double totalChange = change.stream().flatMapToDouble(a -> DoubleStream.of(a.getValue())).
                        reduce(0, (a, b) -> a + b);
                totalChange = Utils.round(totalChange);
                System.out.println("Order processed! \n\nChange dispensed: " + (totalChange));
                for (Coin coin : change) {
                    System.out.println("Coin " + (coin.getName()));
                }
                System.out.println("\n");
            } catch (Exception ex) {
                System.out.println(ex);
            }
            System.out.println("Inventory after transaction: ");
            coinsWallet.getInventory().forEach((k, v)->
                    System.out.println(MessageFormat.format("Coin {0} - Count {1}", k.getName(), v))
            );
        }
    }

}