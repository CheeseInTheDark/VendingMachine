package vendingmachine

class Dispenser
{
    def private statusReadout
    def private coinBox
    def private itemBin
    def private inventory

    def request(product) {
        if (outOfStock(product)) {
            displaySoldOut()
        } else if (insufficientFundsToPurchase(product)) {
            displayPriceOf(product)
        } else {
            dispense(product)
        }
    }

    def private outOfStock(product) {
        !inventory.has(product.name)
    }

    def private displaySoldOut() {
        statusReadout.displaySoldOut()
    }

    def private insufficientFundsToPurchase(product) {
        product.price > coinBox.valueOfCoins()
    }

    def private displayPriceOf(product) {
        statusReadout.displayPrice(product.price)
    }

    def private dispense(product) {
        inventory.removeOneOf(product.name)
        itemBin.add(product.name)
        statusReadout.displayGratification()
        coinBox.claimCoins()
    }
}
