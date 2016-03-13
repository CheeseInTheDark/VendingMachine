package vendingmachine

class StatusReadout
{
    def private buildMoneyInMachineMessage = {
        if (moneyInMachine.amount() > 0.0) { moneyInMachine.amount().toString() }
        else { insertCoinPrompt }
    }

    def private insertCoinPrompt = "INSERT COIN"
    def private buildMessage = buildMoneyInMachineMessage
    def private moneyInMachine

    StatusReadout(moneyInMachine) {
        this.moneyInMachine = moneyInMachine
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
