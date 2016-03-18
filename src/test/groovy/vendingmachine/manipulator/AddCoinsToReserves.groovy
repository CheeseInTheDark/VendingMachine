package vendingmachine.manipulator

class AddCoinsToReserves
{
    def static put(coins) {
        [inCoinReservesOf: {
            machine -> coins.each {
                machine.addToCoinReserves(it)
            }
        }]
    }
}
