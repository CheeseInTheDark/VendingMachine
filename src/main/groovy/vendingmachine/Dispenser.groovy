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
        statusReadout.displayPrice(product.price)
        if (moneyInMachine.amount() >= product.price) {
            dispensedItems << product.name
            statusReadout.displayGratification()
        }
    }
}
