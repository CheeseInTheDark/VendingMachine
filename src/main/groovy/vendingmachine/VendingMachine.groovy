package vendingmachine

class VendingMachine
{
    def private moneyInMachine = new AmountOfMoney()
    def private statusReadout = new StatusReadout(moneyInMachine)
    def private returnedCoins = []
    def private dispensedItems = []

    def private coinValues =
            ["NICKEL": 0.05,
             "DIME": 0.10,
             "QUARTER": 0.25]

    def display() {
        statusReadout.nextMessage()
    }

    def selectChips() {
        statusReadout.displayPrice(0.50)
    }

    def selectCandy() {
        statusReadout.displayPrice(0.65)
    }

    def selectCola() {
        statusReadout.displayPrice(1.00)
        if (moneyInMachine.amount() == 1.00) {
            dispensedItems = ["SUPER FIZZ BOP COLA SODAPOP"]
        }
    }

    def retrieveDispensedItems() {
        dispensedItems
    }

    def insert(coin) {
        if (isAcceptable(coin)) {
            addToCoinsHeld(coin)
        } else {
            reject(coin)
        }
    }

    def private isAcceptable(coin) {
        coinValues.containsKey(coin)
    }

    def private addToCoinsHeld(coin) {
        moneyInMachine.add(coinValues[coin])
    }

    def private reject(coin) {
        returnedCoins << coin
    }

    def retrieveReturnedCoins() {
        def coinsRetrieved = returnedCoins
        returnedCoins = []
        return coinsRetrieved
    }
}
