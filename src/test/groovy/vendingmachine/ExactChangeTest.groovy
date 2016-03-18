package vendingmachine

import org.junit.Test
import vendingmachine.manipulator.AddCoinsToReserves

import static vendingmachine.manipulator.AddCoinsToReserves.put
import static vendingmachine.manipulator.AddCoinsToReserves.put

class ExactChangeTest
{
    def underTest = new VendingMachine()

    @Test
    def void exactChangeOnlyIsDisplayedWhenNoMoneyIsInCoinReserves() {
        assert underTest.display() == "EXACT CHANGE ONLY"
    }

    @Test
    def void exactChangeOnlyIsDisplayedWhenOneNickelIsInCoinReserves() {
        underTest.addToCoinReserves("NICKEL")

        assert underTest.display() == "EXACT CHANGE ONLY"
    }

    @Test
    def void exactChangeOnlyIsDisplayedWhenTwoQuartersAreInCoinReserves() {
        2.times { underTest.addToCoinReserves("QUARTER") }

        assert underTest.display() == "EXACT CHANGE ONLY"
    }

    @Test
    def void insertCoinIsDisplayedWhenOneDimeAndOneNickelAreInCoinReserves() {
        put(["NICKEL", "DIME"]).inCoinReservesOf(underTest)

        assert underTest.display() == "INSERT COIN"
    }

    @Test
    def void insertCoinIsDisplayedWhenOneDimeOneQuarterAndOneNickelAreInCoinReserves() {
        put(["DIME", "QUARTER", "NICKEL"]).inCoinReservesOf(underTest)

        assert underTest.display() == "INSERT COIN"
    }
}
