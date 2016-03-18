package vendingmachine

import org.junit.Test

class InventoryTests
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
    def void dispensingColaRemovesColaFromInventory() {
        underTest.restockCola(1)
        2.times { purchaseCola() }

        assert underTest.display() == "SOLD OUT"
    }

    @Test
    def void actualCountOfColaInInventoryIsTracked() {
        underTest.restockCola(2)
        2.times { purchaseCola() }

        assert underTest.display() == "THANK YOU"
    }

    def private purchaseCola() {
        4.times { underTest.insert("QUARTER") }
        underTest.selectCola()
    }

    @Test
    def void restockingChipsAllowsPurchaseOfChipsAgain() {
        underTest.restockChips(1)
        underTest.selectChips()

        assert underTest.display() == "PRICE 0.50"
    }

    @Test
    def void dispensingChipsRemovesChipsFromInventory() {
        underTest.restockChips(1)
        2.times { purchaseChips() }

        assert underTest.display() == "SOLD OUT"
    }

    @Test
    def void theActualCountOfChipsInInventoryIsTracked() {
        underTest.restockChips(2)
        2.times { purchaseChips() }

        assert underTest.display() == "THANK YOU"
    }

    def private purchaseChips() {
        2.times { underTest.insert("QUARTER") }
        underTest.selectChips()
    }

    @Test
    def void dispensingCandyRemovesCandyFromInventory() {
        underTest.restockCandy(1)
        2.times { purchaseCandy() }

        assert underTest.display() == "SOLD OUT"
    }

    @Test
    def void theActualCountOfCandyInStockIsTracked() {
        underTest.restockCandy(2)
        2.times { purchaseCandy() }

        assert underTest.display() == "THANK YOU"
    }

    def private purchaseCandy() {
        3.times { underTest.insert("QUARTER") }
        underTest.selectCandy()
    }

}
