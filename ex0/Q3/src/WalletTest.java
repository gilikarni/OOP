package src;

public class WalletTest {
    public static void main(String[] args)
    {
        // create new wallet
        Wallet myWallet = new Wallet();
        System.out.println("new wallet initiated");

        // add coins to wallet
        boolean coinAdded;
        Coin coin1 = new Coin(5);
        Coin coin2 = new Coin(10);
        coinAdded = myWallet.addCoin(coin1);
        assert coinAdded;
        System.out.println("coin of value " + coin1.getValue() +" added to wallet");
        coinAdded = myWallet.addCoin(coin2);
        assert coinAdded;
        System.out.println("coin of value " + coin2.getValue() +" added to wallet");
        printWallet(myWallet);

        // check add for same coin
        coinAdded = myWallet.addCoin(coin1);
        assert !coinAdded;
        System.out.println("add of coin1 failed; already exists in wallet!");

        // check containsCoin
        boolean contains_1 = myWallet.containsCoin(1);
        assert !contains_1;
        boolean contains_10 = myWallet.containsCoin(10);
        assert contains_10;

        // check pay
        double payment;
        System.out.println("attempting payment of 20");
        payment = myWallet.pay(20);
        System.out.println("payed " + payment);
        printWallet(myWallet);
        System.out.println("attempting payment of 5");
        payment = myWallet.pay(5);
        System.out.println("payed " + payment);
        printWallet(myWallet);
        System.out.println("attempting payment of 3");
        payment = myWallet.pay(3);
        System.out.println("payed " + payment);
        printWallet(myWallet);

        // check emptyWallet
        myWallet.addCoin(coin1);
        System.out.println("coin of value " + coin1.getValue() +" added to wallet");
        myWallet.addCoin(coin2);
        System.out.println("coin of value " + coin2.getValue() +" added to wallet");
        printWallet(myWallet);
        myWallet.emptyWallet();
        System.out.println("emptied wallet");
        printWallet(myWallet);
    }

    private static void printWallet(Wallet wallet)
    {
        System.out.println("wallet is of size " +  wallet.getWalletSize() +
                " total " + wallet.getWalletTotal());
    }
}