package vendingmachine

class Dispenser
{
    def private statusReadout
    def private moneyInMachine
    def private dispensedItems

    Dispenser(statusReadout, moneyInMachine, dispensedItems) {
        this.statusReadout = statusReadout
        this.moneyInMachine = moneyInMachine
        this.dispensedItems = dispensedItems
    }

    def request(product) {
        if (canDispense(product)) {
            dispense(product)
        } else {
            rejectRequestFor(product)
        }
    }

    def private canDispense(product) {
        product.price <= moneyInMachine.amount()
    }

    def private dispense(product) {
        dispensedItems << product.name
        statusReadout.displayGratification()
        moneyInMachine.setToZero()
    }

    def private rejectRequestFor(product) {
        statusReadout.displayPrice(product.price)
    }
}
