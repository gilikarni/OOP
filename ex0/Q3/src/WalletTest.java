package src;

public class WalletTest {
    public static void main(String[] args)
    {
        Wallet my_wallet = new Wallet();
        Coin coin1 = new Coin(10);
        Coin coin2 = new Coin(5);
        my_wallet.addCoin(coin1);
        my_wallet.addCoin(coin2);
        printWallet(my_wallet);
        boolean contains_1 = my_wallet.containsCoin(1);
        assert contains_1;
        boolean contains_10 = my_wallet.containsCoin(10);
        assert contains_10;
    }

    private static void printWallet(Wallet wallet)
    {
        System.out.println("wallet is of size " +  wallet.getWalletSize() +
                " total " + wallet.getWalletTotal());
    }
}