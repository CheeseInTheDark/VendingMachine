package vendingmachine

import org.junit.Before
import org.junit.Test

class ChangeMakingIntegrationTests
{
    def underTest

    @Before
    def void setup() {
        underTest = new VendingMachine()
        underTest.restockCola(1)
    }

    @Test
    def void selectingColaWithTooMuchMoneyInTheMachineDispensesChange() {
        6.times { underTest.insert("QUARTER") }
        underTest.selectCola()

        assert underTest.retrieveReturnedCoins() == ["QUARTER", "QUARTER"]
    }
}
