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

    def private coinsAdded = 0

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
        coinsAdded++
        if (coinsAdded > 2 && coin != "QUARTER") {
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
