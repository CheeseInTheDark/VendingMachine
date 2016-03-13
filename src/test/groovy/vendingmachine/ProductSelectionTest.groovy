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
        underTest.insert("QUARTER")
        underTest.insert("QUARTER")
        underTest.insert("DIME")
        underTest.insert("NICKEL")
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
        underTest.insert("QUARTER")
        underTest.insert("QUARTER")
        underTest.insert("QUARTER")
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
        underTest.insert("QUARTER")
        underTest.insert("QUARTER")
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
        underTest.insert("QUARTER")
        underTest.insert("QUARTER")
        underTest.selectChips()

        assert underTest.display() == "THANK YOU"
    }
}
