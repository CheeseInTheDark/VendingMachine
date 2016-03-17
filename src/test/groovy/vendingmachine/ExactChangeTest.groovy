package vendingmachine

import org.junit.Test

class ExactChangeTest
{
    def underTest = new VendingMachine()

    @Test
    def void exactChangeOnlyIsDisplayedWhenNoMoneyIsInTheMachine() {
        assert underTest.display() == "EXACT CHANGE ONLY"
    }
}
