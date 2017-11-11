package src;

public class CollectionTest {
    public static void main(String[] args) {
        
        // create new collection
        CoinCollection collection = new CoinCollection();

        // add coins to wallet
        boolean coin_added;
        Coin coin1 = new Coin(5);
        Coin coin2 = new Coin(10);
        Coin coin3 = new Coin(5);
        coin_added = collection.addCoin(coin1);
        assert coin_added;
        coin_added = collection.addCoin(coin2);
        assert coin_added;
        coin_added = collection.addCoin(coin3);
        assert !coin_added;

        //check getSize and getTotal
        System.out.println("collection is of size " +  collection.getCollectionSize() +
                " total " + collection.getCollectionTotal());

    }
}
