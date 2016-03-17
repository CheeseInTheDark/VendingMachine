package vendingmachine

class VendingMachine
{
    def private coinReturn = new CollectionTray()
    def private itemBin = new CollectionTray()
    def private coinBox = new CoinBox(coinReturn: coinReturn)
    def private statusReadout = new StatusReadout(coinBox)
    def private dispenser = new Dispenser(statusReadout: statusReadout, coinBox: coinBox, itemBin: itemBin)

    def private chips = new Product(name: "SUPER GOOD STARCH SLICES", price: 0.50)
    def private candy = new Product(name: "EXCELLENT SUGARBOMBS", price: 0.65)
    def private cola = new Product(name: "SUPER FIZZ BOP COLA SODAPOP", price: 1.00)

    def private hasCandy = false
    def private hasCola = false

    def display() {
        statusReadout.nextMessage()
    }

    def selectChips() {
        if (!hasCandy) {
            statusReadout.displaySoldOut()
        } else {
            dispenser.request(chips)
        }
    }

    def selectCandy() {
        if (!hasCandy) {
            statusReadout.displaySoldOut()
        } else {
            dispenser.request(candy)
        }
    }

    def selectCola() {
        if (!hasCola) {
            statusReadout.displaySoldOut()
        } else {
            dispenser.request(cola)
        }
    }

    def retrieveDispensedItems() {
        itemBin.collectItemsInTray()
    }

    def insert(coin) {
        coinBox.add(coin)
    }

    def returnCoins() {
        coinBox.returnCoins()
    }

    def retrieveReturnedCoins() {
        coinReturn.collectItemsInTray()
    }

    def restockCandy(amount) {
        hasCandy = true
    }

    def restockChips(amount) {

    }

    def restockCola(amount) {
        hasCola = true
    }
}
