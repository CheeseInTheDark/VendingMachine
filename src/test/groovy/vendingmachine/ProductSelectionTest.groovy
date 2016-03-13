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
    def void insertCoinsIsDisplayedAfterPriceOfChipsIsDisplayed() {
        underTest.selectChips()
        underTest.display()

        assert underTest.display() == "INSERT COIN"
    }

    @Test
    def void selectingCandyDisplaysPriceWhenNoMoneyIsInsertedInTheMachine() {
        underTest.selectCandy()

        assert underTest.display() == "PRICE 0.65"
    }

    @Test
    def void selectingColaDisplaysPriceWhenNoMoneyIsInsertedInTheMachine() {
        underTest.selectCola()

        assert underTest.display() == "PRICE 1.00"
    }
}
