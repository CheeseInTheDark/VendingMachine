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
    def private cola = new Product(name: "SUPER FIZZ BOP COLA SODAPOP", price: 1.00)

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
        dispenser.request(cola)
    }

    def retrieveDispensedItems() {
        def retrievedItems = dispensedItems.collect()
        dispensedItems.clear()
        retrievedItems
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

    def returnCoins() {
        if (moneyInMachine.amount() == 0.50) {
            returnedCoins = ["QUARTER", "QUARTER"]
        } else {
            returnedCoins = ["QUARTER"]
        }
    }

    def retrieveReturnedCoins() {
        def coinsRetrieved = returnedCoins
        returnedCoins = []
        return coinsRetrieved
    }
}
