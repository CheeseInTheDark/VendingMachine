package vendingmachine

import org.junit.Before
import org.junit.Test

import static vendingmachine.manipulator.CoinInserter.insert

class ProductSelectionTest
{
    def underTest = new VendingMachine()

    @Before
    def void setup() {
        underTest.restockChips(1)
        underTest.restockCola(1)
        underTest.restockCandy(1)
        4.times { underTest.addToCoinReserves("NICKEL") }
    }

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

    @Test
    def void selectingColaWithoutSufficientMoneyInTheMachineDoesNotDispenseCola() {
        underTest.selectCola()

        assert underTest.retrieveDispensedItems() == []
    }

    @Test
    def void selectingColaWithSufficientMoneyInTheMachineDispensesCola() {
        4.times { underTest.insert("QUARTER") }
        underTest.selectCola()

        assert underTest.retrieveDispensedItems() == ["SUPER FIZZ BOP COLA SODAPOP"]
    }

    @Test
    def void selectingCandyWithSufficentMoneyInTheMachineDispensesCandy() {
        insert(["QUARTER", "QUARTER", "DIME", "NICKEL"]).into(underTest)
        underTest.selectCandy()

        assert underTest.retrieveDispensedItems() == ["EXCELLENT SUGARBOMBS"]
    }

    @Test
    def void selectingCandyWithoutSufficientMoneyInTheMachineDoesNotDispenseCandy() {
        underTest.selectCandy()

        assert underTest.retrieveDispensedItems() == []
    }

    @Test
    def void selectingCandyWithMoreThanEnoughMoneyInTheMachineDispensesCandy() {
        3.times { underTest.insert("QUARTER") }
        underTest.selectCandy()

        assert underTest.retrieveDispensedItems() == ["EXCELLENT SUGARBOMBS"]
    }

    @Test
    def void selectingColaWithMoreThanEnoughMoneyInTheMachineDispensesCola() {
        5.times { underTest.insert("QUARTER") }
        underTest.selectCola()

        assert underTest.retrieveDispensedItems() == ["SUPER FIZZ BOP COLA SODAPOP"]
    }

    @Test
    def void selectingChipsWithEnoughMoneyInTheMachineDispensesChips() {
        2.times { underTest.insert("QUARTER") }
        underTest.selectChips()

        assert underTest.retrieveDispensedItems() == ["SUPER GOOD STARCH SLICES"]
    }

    @Test
    def void selectingChipsWithoutEnoughMoneyInTheMachineDoesNotDispenseChips() {
        underTest.selectChips()

        assert underTest.retrieveDispensedItems() == []
    }

    @Test
    def void selectingChipsWithMoreThanEnoughMoneyInTheMachineDispensesChips() {
        3.times { underTest.insert("QUARTER") }
        underTest.selectChips()

        assert underTest.retrieveDispensedItems() == ["SUPER GOOD STARCH SLICES"]
    }

    @Test
    def void thankYouIsDisplayedAfterDispensingChips() {
        2.times { underTest.insert("QUARTER") }
        underTest.selectChips()

        assert underTest.display() == "THANK YOU"
    }

    @Test
    def void thankYouIsDisplayedAfterDispensingCola() {
        4.times { underTest.insert("QUARTER") }
        underTest.selectCola()

        assert underTest.display() == "THANK YOU"
    }

    @Test
    def void thankYouIsDisplayedAfterDispensingCandy() {
        insert(["QUARTER", "QUARTER", "DIME", "NICKEL"]).into(underTest)
        underTest.selectCandy()

        assert underTest.display() == "THANK YOU"
    }

    @Test
    def void dispensingAProductResetsTheMoneyInTheMachineToZero() {
        2.times { underTest.insert("QUARTER") }
        underTest.selectChips()
        underTest.display()

        assert underTest.display() == "INSERT COIN"
    }

    @Test
    def void moneyInTheMachineRemainsAtItsPriorAmountIfProductIsNotDispensed() {
        underTest.insert("QUARTER")
        underTest.selectCandy()
        underTest.display()

        assert underTest.display() == "0.25"
    }

    @Test
    def void dispensedProductsCanOnlyBeRetrievedOnce() {
        2.times { underTest.insert("QUARTER") }
        underTest.selectChips()
        underTest.retrieveDispensedItems()

        assert underTest.retrieveDispensedItems() == []
    }
}
