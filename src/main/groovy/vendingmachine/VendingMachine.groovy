package vendingmachine

class VendingMachine {
    def private displayText = "INSERT COIN"
    def private amount = 0.0
    def private coinsReturned = []

    def private coinValues =
            ["NICKEL": 0.05,
             "DIME": 0.10,
             "QUARTER": 0.25]

    def display() {
        displayText
    }

    def insert(coin) {
        if (coinValues.containsKey(coin)) {
            amount += coinValues[coin]
        } else {
            coinsReturned << coin
        }
        displayText = amount.toString()
    }

    def retrieveReturnedCoins() {
        def coinsRetrieved = coinsReturned
        coinsReturned = []
        return coinsRetrieved
    }
}
