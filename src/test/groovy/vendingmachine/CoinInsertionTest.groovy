package vendingmachine

import org.junit.Before
import org.junit.Test
import vendingmachine.VendingMachine

class CoinInsertionTest
{
    def underTest

    @Before
    def void setup() {
        underTest = new VendingMachine()
    }

    @Test
    def void displaysInsertCoinWhenNoCoinsAreInserted() {
        assert underTest.display() == "INSERT COIN"
    }

    @Test
    def void displaysFiveCentsWhenNickelIsInserted() {
        underTest.insert("NICKEL")

        assert underTest.display() == "0.05"
    }

    @Test
    def void displaysTenCentsWhenTwoNickelsAreInserted() {
        underTest.insert("NICKEL")
        underTest.insert("NICKEL")

        assert underTest.display() == "0.10"
    }

    @Test
    def void displaysTenCentsWhenADimeIsInserted() {
        underTest.insert("DIME")

        assert underTest.display() == "0.10"
    }

    @Test
    def void displaysTwentyFiveCentsWhenAQuarterIsInserted() {
        underTest.insert("QUARTER")

        assert underTest.display() == "0.25"
    }

    @Test
    def void coinReturnStartsWithNoCoins() {
        assert underTest.retrieveReturnedCoins() == []
    }

    @Test
    def void penniesArePlacedInTheCoinReturn() {
        underTest.insert("PENNY")

        assert underTest.retrieveReturnedCoins() == ["PENNY"]
    }

    @Test
    def void returnedCoinsCanOnlyBeRetrievedOnce() {
        underTest.insert("PENNY")
        underTest.retrieveReturnedCoins()

        assert underTest.retrieveReturnedCoins() == []
    }

    @Test
    def void returningCoinsWhenAQuarterIsInsertedReturnsTheQuarter() {
        underTest.insert("QUARTER")
        underTest.returnCoins()

        assert underTest.retrieveReturnedCoins() == ["QUARTER"]
    }

    @Test
    def void returningCoinsWhenTwoQuartersAreInsertedReturnsTwoQuarters() {
        underTest.insert("QUARTER")
        underTest.insert("QUARTER")
        underTest.returnCoins()

        assert underTest.retrieveReturnedCoins() == ["QUARTER", "QUARTER"]
    }

    @Test
    def void returningCoinsWhenOneQuarterTwoDimesAndOneNickelAreInsertedReturnsThoseCoins() {
        insertCoins(["QUARTER", "QUARTER", "DIME", "DIME", "NICKEL"])
        underTest.returnCoins()

        assert underTest.retrieveReturnedCoins() == ["QUARTER", "QUARTER", "DIME", "DIME", "NICKEL"]
    }

    @Test
    def void returningCoinsResetsTheAmountOfMoneyInTheMachine() {
        underTest.insert("QUARTER")
        underTest.returnCoins()

        assert underTest.display() == "INSERT COIN"
    }

    def private insertCoins(coins) {
        coins.each { underTest.insert(it) }
    }
}
