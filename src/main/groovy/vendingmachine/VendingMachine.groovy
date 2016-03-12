package vendingmachine

class VendingMachine {
    def private displayText = "INSERT COIN"
    def private moneyInMachine = 0.0
    def private returnedCoins = []

    def private coinValues =
            ["NICKEL": 0.05,
             "DIME": 0.10,
             "QUARTER": 0.25]

    def display() {
        displayText
    }

    def insert(coin) {
        if (isAcceptable(coin)) {
            addToCoinsHeld(coin)
        } else {
            rejectCoin(coin)
        }
        displayText = moneyInMachine.toString()
    }

    def retrieveReturnedCoins() {
        def coinsRetrieved = returnedCoins
        returnedCoins = []
        return coinsRetrieved
    }

    def private isAcceptable(coin) {
        coinValues.containsKey(coin)
    }

    def private addToCoinsHeld(coin) {
        moneyInMachine += coinValues[coin]
    }

    def private rejectCoin(coin) {
        returnedCoins << coin
    }
}
