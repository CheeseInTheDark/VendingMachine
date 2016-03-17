package vendingmachine

import org.junit.Test

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
}
