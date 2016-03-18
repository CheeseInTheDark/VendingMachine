package vendingmachine

import org.junit.Before
import org.junit.Test
import vendingmachine.manipulator.InsertCoins

class ChangeMakingTest
{
    def coinReturn = new CollectionTray()
    def underTest = new CoinBox(coinReturn: coinReturn)

    @Test
    def void claimingOneDollarWithFiveQuartersInsertedReturnsOneQuarter() {
        5.times { underTest.add("QUARTER") }
        underTest.claimCoins(1.0)

        assert coinReturn.collectItemsInTray() == ["QUARTER"]
    }

    @Test
    def void claimingOneDollarWithSixQuartersInsertedReturnsTwoQuarters() {
        6.times { underTest.add("QUARTER") }
        underTest.claimCoins(1.0)

        assert coinReturn.collectItemsInTray() == ["QUARTER", "QUARTER"]
    }

    @Test
    def void claimingFiftyCentsWithSixQuartersInsertedReturnsFourQuarters() {
        6.times { underTest.add("QUARTER") }
        underTest.claimCoins(0.50)

        assert coinReturn.collectItemsInTray() == ["QUARTER", "QUARTER", "QUARTER", "QUARTER"]
    }

    @Test
    def void claimingFiftyCentsWithSixDimesInsertedReturnsOneDime() {
        6.times { underTest.add("DIME") }
        underTest.claimCoins(0.50)

        assert coinReturn.collectItemsInTray() == ["DIME"]
    }

    @Test
    def void claimingFiftyCentsWithSevenDimesInsertedReturnsTwoDimes() {
        7.times { underTest.add("DIME") }
        underTest.claimCoins(0.50)

        assert coinReturn.collectItemsInTray() == ["DIME", "DIME"]
    }

    @Test
    def void claimingThirtyCentsWithSevenDimesInsertedReturnsFourDimes() {
        7.times { underTest.add("DIME") }
        underTest.claimCoins(0.30)

        assert coinReturn.collectItemsInTray() == ["DIME", "DIME", "DIME", "DIME"]
    }
}
