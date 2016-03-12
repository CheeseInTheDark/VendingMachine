package vendingmachine

import org.junit.Test

class ProductSelectionTest
{
    def underTest = new VendingMachine()

    @Test
    def void priceOfChipsIsDisplayedWhenNoMoneyIsInsertedInTheMachine() {
        underTest.selectChips()

        assert underTest.display() == "PRICE 0.50"
    }
}
