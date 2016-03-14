package vendingmachine

class CoinBox
{
    def private coinValues =
            ["NICKEL": 0.05,
             "DIME": 0.10,
             "QUARTER": 0.25]

    def private coinsInBox = []
    def coinReturn

    def add(coin) {
        if (isAcceptable(coin)) {
            addToCoinsHeld(coin)
        } else {
            reject(coin)
        }
    }

    def private isAcceptable(coin) {
        coinValues.containsKey(coin)
    }

    def private addToCoinsHeld(coin) {
        coinsInBox << coin
    }

    def valueOfCoins() {
        coinsInBox.sum { coinValues[it] }
    }

    def claimCoins() {
        coinsInBox = []
    }

    def returnCoins() {
        coinsInBox.each { coinReturn.add(it) }
        coinsInBox = []
    }

    def private reject(coin) {
        coinReturn.add(coin)
    }
}
