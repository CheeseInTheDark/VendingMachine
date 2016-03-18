package vendingmachine

import org.junit.Before
import org.junit.Test
import vendingmachine.manipulator.AddCoinsToReserves
import vendingmachine.manipulator.InsertCoins

import static vendingmachine.manipulator.AddCoinsToReserves.put
import static vendingmachine.manipulator.InsertCoins.insert

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

    @Test
    def void addingCoinsToTheVendingMachinesReservesMakesThemAvailableForMakingChange() {
        put(["QUARTER"]).inCoinReservesOf(underTest)
        12.times { underTest.insert("DIME") }
        underTest.insert("NICKEL")
        underTest.selectCola()

        assert underTest.retrieveReturnedCoins() == ["QUARTER"]

    }
}
