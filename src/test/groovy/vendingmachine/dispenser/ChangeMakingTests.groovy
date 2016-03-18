package vendingmachine.dispenser

import org.junit.Before
import org.junit.Test
import vendingmachine.CoinBox
import vendingmachine.CollectionTray

class ChangeMakingTests
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

    @Test
    def void coinsFromReservesWhichAreUsedToMakeChangeAreRemovedFromReserves() {
        underTest.addToReserves("NICKEL")
        underTest.add("QUARTER")
        underTest.claimCoins(0.15)

        assert coinReturn.collectItemsInTray() == ["NICKEL"]
    }

    @Test
    def void dimesFromReservesAreUsedToMakeChange() {
        underTest.addToReserves("DIME")
        underTest.add("QUARTER")
        underTest.claimCoins(0.15)

        assert coinReturn.collectItemsInTray() == ["DIME"]
    }

    @Test
    def void dimesFromReservesWhichAreUsedToMakeChangeAreRemovedFromReserves() {
        underTest.addToReserves("DIME")
        underTest.add("QUARTER")
        underTest.claimCoins(0.05)

        assert coinReturn.collectItemsInTray() == ["DIME"]
    }

    @Test
    def void moreThanOneDimeIsTakenFromReservesToMakeChange() {
        2.times { underTest.addToReserves("DIME") }
        underTest.add("QUARTER")
        underTest.claimCoins(0.05)

        assert coinReturn.collectItemsInTray() == ["DIME", "DIME"]
    }

    @Test
    def void moreThanOneNickelIsTakenFromReservesToMakeChange() {
        4.times { underTest.addToReserves("NICKEL") }
        underTest.add("QUARTER")
        underTest.claimCoins(0.05)

        assert coinReturn.collectItemsInTray() == ["NICKEL", "NICKEL", "NICKEL", "NICKEL"]
    }

    @Test
    def void makingChangeDoesNotRemovedDimesFromReservesIfDimesAreAvailableFromHeldCoins() {
        underTest.addToReserves("DIME")
        underTest.add("DIME")
        underTest.claimCoins(0.00)
        underTest.add("QUARTER")
        underTest.claimCoins(0.05)

        assert coinReturn.collectItemsInTray() == ["DIME", "DIME"]
    }

    @Test
    def void makingChangeDoesNotRemoveNickelsFromReservesIfNickelsAreAvailableFromHeldCoins() {
        underTest.addToReserves("NICKEL")
        underTest.add("NICKEL")
        underTest.claimCoins(0.00)
        underTest.add("QUARTER")
        underTest.claimCoins(0.05)

        assert coinReturn.collectItemsInTray() == ["NICKEL", "NICKEL"]
    }

    @Test
    def void makingChangeRemovesCoinsFromBoxAsTheyAreReturned() {
        underTest.add("QUARTER")
        4.times { underTest.add("DIME") }
        2.times { underTest.add("NICKEL") }
        underTest.claimCoins(0.00)

        assert coinReturn.collectItemsInTray() == ["QUARTER", "DIME", "DIME", "DIME", "DIME", "NICKEL", "NICKEL"]
    }

    @Test
    def void quartersHeldInReserveAreUsedToMakeChangeBeforeDimes() {
        underTest.addToReserves("QUARTER")
        2.times { underTest.add("DIME") }
        underTest.add("NICKEL")
        underTest.claimCoins(0.00)

        assert coinReturn.collectItemsInTray() == ["QUARTER"]
    }

    @Test
    def void quartersUsedToMakeChangeAreRemovedFromReserves() {
        underTest.addToReserves("QUARTER")
        2.times {
            2.times { underTest.add("DIME") }
            underTest.add("NICKEL")
            underTest.claimCoins(0.00)
        }

        assert coinReturn.collectItemsInTray() == ["QUARTER", "DIME", "DIME", "NICKEL"]
    }

    @Test
    def void countOfQuartersInReserveIsKept() {
        2.times { underTest.addToReserves("QUARTER") }
        2.times {
            add(["DIME", "DIME", "NICKEL"])
            underTest.claimCoins(0.00)
        }

        assert coinReturn.collectItemsInTray() == ["QUARTER", "QUARTER"]
    }

    @Test
    def void quartersHeldAreUsedToMakeChangeBeforeQuartersInReserves() {
        underTest.addToReserves("QUARTER")
        underTest.add("QUARTER")
        underTest.claimCoins(0.00)
        add(["DIME", "DIME", "NICKEL"])
        underTest.claimCoins(0.00)

        assert coinReturn.collectItemsInTray() == ["QUARTER", "QUARTER"]
    }

    @Test
    def void quartersLeftAfterMakingChangeAreAddedToReserves() {
        add(["QUARTER", "QUARTER"])
        underTest.claimCoins(0.50)
        5.times { underTest.add("DIME") }
        underTest.claimCoins(0.00)

        assert coinReturn.collectItemsInTray() == ["QUARTER", "QUARTER"]
    }

    @Test
    def void dimesLeftAfterMakingChangeAreAddedToReserves() {
        add(["DIME", "DIME"])
        underTest.claimCoins(0.20)
        4.times { underTest.add("NICKEL") }
        underTest.claimCoins(0.00)

        assert coinReturn.collectItemsInTray() == ["DIME", "DIME"]
    }

    @Test
    def void noNickelsAreReturnedWhenExactChangeIsProvided() {
        add(["NICKEL", "NICKEL"])
        underTest.claimCoins(0.10)

        assert coinReturn.collectItemsInTray() == []
    }

    @Test
    def void noDimesAreReturnedWhenExactChangeIsProvided() {
        add(["DIME", "DIME"])
        underTest.claimCoins(0.20)

        assert coinReturn.collectItemsInTray() == []
    }

    @Test
    def void nickelsLeftAfterMakingChangeAreAddedToReserves() {
        add(["NICKEL", "NICKEL"])
        underTest.claimCoins(0.10)
        underTest.add("QUARTER")
        underTest.claimCoins(0.15)

        assert coinReturn.collectItemsInTray() == ["NICKEL", "NICKEL"]
    }

    def private add(coins) {
        coins.each { underTest.add(it) }
    }
}
