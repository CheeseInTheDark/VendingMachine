package vendingmachine

class VendingMachine
{
    def private coinReturn = new CoinReturn()
    def private coinBox = new CoinBox(coinReturn: coinReturn)
    def private statusReadout = new StatusReadout(coinBox)
    def private dispensedItems = []
    def private dispenser = new Dispenser(statusReadout: statusReadout, coinBox: coinBox, dispensedItems: dispensedItems)

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
        coinBox.add(coin)
    }

    def returnCoins() {
        coinBox.returnCoins()
    }

    def retrieveReturnedCoins() {
        coinReturn.collectCoins()
    }
}
