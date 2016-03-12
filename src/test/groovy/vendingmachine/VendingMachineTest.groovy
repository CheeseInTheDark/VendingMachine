package vendingmachine

import org.junit.Test
import vendingmachine.VendingMachine

class VendingMachineTest
{
    @Test
    def void displaysInsertCoinWhenNoCoinsAreInserted() {
        def underTest = new VendingMachine()

        assert underTest.display() == "INSERT COIN"
    }
}
