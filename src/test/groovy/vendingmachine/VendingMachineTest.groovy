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

    @Test
    def void displaysFiveCentsWhenNickelIsInserted() {
        def underTest = new VendingMachine()

        underTest.insert("NICKEL")

        assert underTest.display() == "0.05"
    }

    @Test
    def void displaysTenCentsWhenTwoNickelsAreInserted() {
        def underTest = new VendingMachine()

        underTest.insert("NICKEL")
        underTest.insert("NICKEL")

        assert underTest.display() == "0.10"
    }
}
