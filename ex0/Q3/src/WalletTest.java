package src;

public class WalletTest {
    public static void main(String[] args)
    {
        // create new wallet
        Wallet my_wallet = new Wallet();
        System.out.println("new wallet initiated");

        // add coins to wallet
        boolean coin_added;
        Coin coin1 = new Coin(5);
        Coin coin2 = new Coin(10);
        coin_added = my_wallet.addCoin(coin1);
        assert coin_added;
        System.out.println("coin of value " + coin1.getValue() +" added to wallet");
        coin_added = my_wallet.addCoin(coin2);
        assert coin_added;
        System.out.println("coin of value " + coin2.getValue() +" added to wallet");
        printWallet(my_wallet);

        // check add for same coin
        coin_added = my_wallet.addCoin(coin1);
        assert !coin_added;
        System.out.println("add of coin1 failed; already exists in wallet!");

        // check containsCoin
        boolean contains_1 = my_wallet.containsCoin(1);
        assert !contains_1;
        boolean contains_10 = my_wallet.containsCoin(10);
        assert contains_10;

        // check pay
        double payment;
        System.out.println("attempting payment of 20");
        payment = my_wallet.pay(20);
        System.out.println("payed " + payment);
        printWallet(my_wallet);
        System.out.println("attempting payment of 5");
        payment = my_wallet.pay(5);
        System.out.println("payed " + payment);
        printWallet(my_wallet);
        System.out.println("attempting payment of 3");
        payment = my_wallet.pay(3);
        System.out.println("payed " + payment);
        printWallet(my_wallet);

        // check emptyWallet
        my_wallet.addCoin(coin1);
        System.out.println("coin of value " + coin1.getValue() +" added to wallet");
        my_wallet.addCoin(coin2);
        System.out.println("coin of value " + coin2.getValue() +" added to wallet");
        printWallet(my_wallet);
        my_wallet.emptyWallet();
        System.out.println("emptied wallet");
        printWallet(my_wallet);
    }

    private static void printWallet(Wallet wallet)
    {
        System.out.println("wallet is of size " +  wallet.getWalletSize() +
                " total " + wallet.getWalletTotal());
    }
}