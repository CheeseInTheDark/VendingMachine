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
}
