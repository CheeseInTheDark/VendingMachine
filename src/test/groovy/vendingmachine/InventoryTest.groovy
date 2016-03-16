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
}
