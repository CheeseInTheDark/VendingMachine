package vendingmachine

import static vendingmachine.Selections.candy
import static vendingmachine.Selections.chips
import static vendingmachine.Selections.cola

class VendingMachine
{
    def private coinReturn = new CollectionTray()
    def private itemBin = new CollectionTray()
    def private coinBox = new CoinBox(coinReturn: coinReturn)
    def private statusReadout = new StatusReadout(coinBox)
    def private inventory = new Inventory()
    def private dispenser = new Dispenser(statusReadout: statusReadout,
            coinBox: coinBox,
            itemBin: itemBin,
            inventory: inventory)

    def private dimesAdded = 0
    def private nickelsAdded = 0

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
        itemBin.collectItemsInTray()
    }

    def insert(coin) {
        coinBox.add(coin)
    }

    def returnCoins() {
        coinBox.returnCoins()
    }

    def addToCoinReserves(coin) {
        if (coin == "DIME") {
            dimesAdded++
        } else if (coin == "NICKEL") {
            nickelsAdded++
        }

        if (nickelsAdded == 2 || (nickelsAdded == 1 && dimesAdded == 1)) {
            statusReadout.useInsertCoin()
        }
    }

    def retrieveReturnedCoins() {
        coinReturn.collectItemsInTray()
    }

    def restockCandy(amount) {
        inventory.add(candy.name, amount)
    }

    def restockChips(amount) {
        inventory.add(chips.name, amount)
    }

    def restockCola(amount) {
        inventory.add(cola.name, amount)
    }
}
