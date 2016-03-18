package vendingmachine.manipulator

class InsertCoins
{
    def static insert(coins) {
        [into: {
            machine -> coins.each {
                machine.insert(it)
            }
        }]
    }

    def static insert(coins) {

    }
}
