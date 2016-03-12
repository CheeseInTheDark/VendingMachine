package vendingmachine

import org.junit.Test

class ProductSelectionTest
{
    def underTest = new VendingMachine()

    @Test
    def void selectingChipsDisplaysPriceWhenNoMoneyIsInsertedInTheMachine() {
        underTest.selectChips()

        assert underTest.display() == "PRICE 0.50"
    }

    @Test
    def void insertCoinsIsDisplayedAfterPriceIsDisplayed() {
        underTest.selectChips()
        underTest.display()

        assert underTest.display() == "INSERT COIN"
    }
}
