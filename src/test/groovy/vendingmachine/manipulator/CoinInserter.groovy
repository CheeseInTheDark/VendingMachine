package vendingmachine.manipulator

class CoinInserter
{
    def static insert(coins) {
        [into: {
            machine -> coins.each {
                machine.insert(it)
            }
        }]
    }
}
