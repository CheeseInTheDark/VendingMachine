package vendingmachine

import org.junit.Before
import org.junit.Test
import vendingmachine.manipulator.InsertCoins

class ChangeMakingTest
{
    def coinReturn
    def underTest

    @Before
    def void setup() {
        coinReturn = new CollectionTray()
        underTest = new CoinBox(coinReturn: coinReturn)
    }

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

    @Test
    def void claimingTenCentsWithTwoNickelsAndOneDimeInsertedReturnsOneDime() {
        underTest.add("NICKEL")
        underTest.add("NICKEL")
        underTest.add("DIME")
        underTest.claimCoins(0.10)

        assert coinReturn.collectItemsInTray() == ["DIME"]
    }

    @Test
    def void claimingThirtyCentsWithSixNickelsAndOneDimeInsertedReturnsOneDime() {
        6.times { underTest.add("NICKEL") }
        underTest.add("DIME")
        underTest.claimCoins(0.30)

        assert coinReturn.collectItemsInTray() == ["DIME"]
    }

    @Test
    def void claimingThirtyCentsWithEightNickelsInsertedReturnsTwoNickels() {
        8.times { underTest.add("NICKEL") }
        underTest.claimCoins(0.30)

        assert coinReturn.collectItemsInTray() == ["NICKEL", "NICKEL"]
    }

    @Test
    def void claimingTwentyFiveCentsWithOneQuarterOneDimeAndOneNickelInsertedReturnsTheNickelAndTheDime() {
        underTest.add("QUARTER")
        underTest.add("NICKEL")
        underTest.add("DIME")
        underTest.claimCoins(0.25)

        assert coinReturn.collectItemsInTray() == ["DIME", "NICKEL"]
    }

    @Test
    def void changeIsNotDispensedWhenUnavailable() {
        underTest.add("QUARTER")
        underTest.claimCoins(0.20)

        assert coinReturn.collectItemsInTray() == []
    }

    @Test
    def void changeIsMadeUsingCoinsHeldInReserve() {
        underTest.addToReserves("NICKEL")
        underTest.add("QUARTER")
        underTest.claimCoins(0.20)

        assert coinReturn.collectItemsInTray() == ["NICKEL"]
    }
}
