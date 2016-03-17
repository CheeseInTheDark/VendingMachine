package vendingmachine

class StatusReadout
{
    def private buildMoneyInMachineMessage = {
        if (coinBox.valueOfCoins() > 0.0) { coinBox.valueOfCoins().toString() }
        else { insertCoinPrompt }
    }

    def private insertCoinPrompt = "EXACT CHANGE ONLY"
    def private buildMessage = buildMoneyInMachineMessage
    def private coinBox

    StatusReadout(coinBox) {
        this.coinBox = coinBox
    }

    def useInsertCoin() {
        insertCoinPrompt = "INSERT COIN"
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
