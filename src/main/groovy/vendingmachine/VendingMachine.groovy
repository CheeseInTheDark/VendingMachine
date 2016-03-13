package vendingmachine

class VendingMachine
{
    def private moneyInMachine = new AmountOfMoney()
    def private statusReadout = new StatusReadout(moneyInMachine)
    def private dispensedItems = []
    def private dispenser = new Dispenser(statusReadout, moneyInMachine, dispensedItems)
    def private returnedCoins = []

    def private coinValues =
            ["NICKEL": 0.05,
             "DIME": 0.10,
             "QUARTER": 0.25]

    def private chips = new Product(name: "SUPER GOOD STARCH SLICES", price: 0.50)
    def private candy = new Product(name: "EXCELLENT SUGARBOMBS", price: 0.65)

    def display() {
        statusReadout.nextMessage()
    }

    def selectChips() {
        dispenser.request(chips)
    }

    def selectCandy() {
        dispenser.request(candy)
    }

    def selectCola() {
        statusReadout.displayPrice(1.00)
        if (moneyInMachine.amount() >= 1.00) {
            dispensedItems = ["SUPER FIZZ BOP COLA SODAPOP"]
            statusReadout.displayGratification()
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
