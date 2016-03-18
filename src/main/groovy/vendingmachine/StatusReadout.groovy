package vendingmachine

class StatusReadout
{
    def private buildMoneyInMachineMessage = {
        if (coinBox.valueOfHeldCoins() > 0.0) { coinBox.valueOfHeldCoins().toString() }
        else { insertCoinPrompt() }
    }

    def private insertCoinPrompt() {
        if (coinBox.canMakeChangeForAllProducts()) {
            "INSERT COIN"
        } else {
            "EXACT CHANGE ONLY"
        }
    }
    def private buildMessage = buildMoneyInMachineMessage
    def private coinBox

    StatusReadout(coinBox) {
        this.coinBox = coinBox
    }

    def displayWaitingForMoney() {
        buildMessage = buildMoneyInMachineMessage
    }

    def displayPrice(price) {
        buildMessage = { "PRICE " + price.toString() }
    }

    def displayGratification() {
        buildMessage = { "THANK YOU" }
    }

    def displaySoldOut() {
        buildMessage = { "SOLD OUT" }
    }

    def nextMessage() {
        def message = buildMessage()
        displayWaitingForMoney()
        return message
    }
}
