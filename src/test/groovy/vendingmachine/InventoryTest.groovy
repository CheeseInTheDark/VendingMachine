package vendingmachine

import org.junit.Test

class InventoryTest
{
    def underTest = new VendingMachine()

    @Test
    def void selectingAnOutOfStockProductDisplaysSoldOut() {
        underTest.selectCandy()

        assert underTest.display() == "SOLD OUT"
    }

    @Test
    def void selectingChipsWhenChipsAreOutOfStockDisplaysSoldOut() {
        underTest.selectChips()

        assert underTest.display() == "SOLD OUT"
    }

    @Test
    def void selectingColaWhenColaIsOutOfStockDisplaysSoldOut() {
        underTest.selectCola()

        assert underTest.display() == "SOLD OUT"
    }

    @Test
    def void restockingColaAllowsPurchaseOfColaAgain() {
        underTest.restockCola(1)
        underTest.selectCola()

        assert underTest.display() == "PRICE 1.00"
    }

    @Test
    def void dispensingColaRemovesChipsFromInventory() {
        underTest.restockCola(1)
        2.times { purchaseCola() }

        assert underTest.display() == "SOLD OUT"
    }

    @Test
    def void restockingChipsAllowsPurchaseOfChipsAgain() {
        underTest.restockChips(1)
        underTest.selectChips()

        assert underTest.display() == "PRICE 0.50"
    }

    def private purchaseCola() {
        4.times { underTest.insert("QUARTER") }
        underTest.selectCola()
    }
}
