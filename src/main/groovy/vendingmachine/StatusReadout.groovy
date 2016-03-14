package vendingmachine

class StatusReadout
{
    def private buildMoneyInMachineMessage = {
        if (coinBox.valueOfCoins() > 0.0) { coinBox.valueOfCoins().toString() }
        else { insertCoinPrompt }
    }

    def private insertCoinPrompt = "INSERT COIN"
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

    def nextMessage() {
        def message = buildMessage()
        displayWaitingForMoney()
        return message
    }
}
