package vendingmachine

import org.junit.Before
import org.junit.Test
import vendingmachine.VendingMachine

class VendingMachineTest
{
    def underTest

    @Before
    def void setup() {
        underTest = new VendingMachine()
    }

    @Test
    def void displaysInsertCoinWhenNoCoinsAreInserted() {
        assert underTest.display() == "INSERT COIN"
    }

    @Test
    def void displaysFiveCentsWhenNickelIsInserted() {
        underTest.insert("NICKEL")

        assert underTest.display() == "0.05"
    }

    @Test
    def void displaysTenCentsWhenTwoNickelsAreInserted() {
        underTest.insert("NICKEL")
        underTest.insert("NICKEL")

        assert underTest.display() == "0.10"
    }

    @Test
    def void displaysTenCentsWhenADimeIsInserted() {
        underTest.insert("DIME")

        assert underTest.display() == "0.10"
    }
}
