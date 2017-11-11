package src;

import java.util.ArrayList;

/**
 * A wallet can contain a number of coins. There could be several coins of the same value,
 * but the same coin cannot appear in the wallet twice
 */
public class Wallet {
	private ArrayList<Coin> coinList;

    /**
     * @effects Creates a new empty wallet
     */
    public Wallet() {
        this.coinList = new ArrayList<>();
    }

    /**
     * @modifies this
     * @effects Adds a coin to the wallet
     * @return true if the coin was successfully added to the wallet;
     * 		   false otherwise
     */
    public boolean addCoin(Coin coin) {
        // check if coin exists in wallet. if so, return false
        for (Coin existingCoin : this.coinList) {
            if (existingCoin == coin) {
                return false;
            }
        }
        // place coin in coinList according to value
        for (int i = 0; i < getWalletSize(); i++) {
            if (coin.getValue() >= coinList.get(i).getValue()) {
                coinList.add(i, coin);
                return true;
            }
        }
        // if list is empty or coin value is smallest, add to end of list
        return this.coinList.add(coin);
    }

    /**
     * @modifies this
     * @effects tries to match at least the sum "sum" with coins in the wallet. 
	 *			If transaction is possible, removes the paid coins from the wallet; else; changes nothing
     * @return the amount actually paid, 0 if amount could not be obtained
     */
    public double pay(double sum) {
        // first checks if wallet holds enough money; if so, returns 0
        if (this.getWalletTotal() < sum) {
            return 0;
        }
        double payment = 0;
        while (payment < sum) {
            payment += this.coinList.get(0).getValue();
            this.coinList.remove(0);
        }
        return payment;
    }

    /**
     * @modifies this
     * @effects tries to match at least the sum "sum" with the minimum number
     *          of coins available from the wallet.
     *			If transaction is possible, removes the paid coins from the wallet; else; changes nothing
     * @return the amount actually paid, 0 if amount could not be obtained
     */
    public double payMinimum(double sum) {
        return pay(sum);
    }

    /**
     * @return the current total value of coins in the wallet
     */
    public double getWalletTotal() {
    	double total = 0;
    	for (Coin coin : this.coinList) {
    	    total += coin.getValue();
        }
        return total;
    }

    /**
     * @return the number of coins in the wallet
     */
    public int getWalletSize() {
    	return this.coinList.size();
    }


    /**
     * @modifies this
     * @effects Empties the the wallet. After this method is called,
	 * 			both getWalletSize() and getWalletTotal() will return 0
	 * 			if called
     */
    public void emptyWallet() {
    	this.coinList.clear();
    }


    /**
     * @return true if this wallet contains a coin with value "value"
     *  	   false otherwise
     */
    public boolean containsCoin(double value) {
        for (Coin coin : this.coinList) {
            if (coin.getValue() == value) {
                return true;
            }
        }
        return false;
    }
}
