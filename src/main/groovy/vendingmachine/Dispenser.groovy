package vendingmachine

class Dispenser
{
    def private statusReadout
    def private coinBox
    def private dispensedItems

    def request(product) {
        if (canDispense(product)) {
            dispense(product)
        } else {
            rejectRequestFor(product)
        }
    }

    def private canDispense(product) {
        product.price <= coinBox.valueOfCoins()
    }

    def private dispense(product) {
        dispensedItems << product.name
        statusReadout.displayGratification()
        coinBox.claimCoins()
    }

    def private rejectRequestFor(product) {
        statusReadout.displayPrice(product.price)
    }
}
