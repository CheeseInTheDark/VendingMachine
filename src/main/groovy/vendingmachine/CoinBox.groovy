package vendingmachine

class CoinBox
{
    def private coinValues =
            ["NICKEL": 0.05,
             "DIME": 0.10,
             "QUARTER": 0.25]

    def private coinsInBox = []
    def private coinsInReserve = []
    def coinReturn

    def addToReserves(coin) {
        coinsInReserve << coin
    }

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

    def claimCoins(value) {
        if (value != null) {
            def valueToReturn = valueOfCoins() - value

            ["QUARTER", "DIME", "NICKEL"].each {
                while (valueToReturn >= coinValues[it] && (coinsInBox.contains(it) || coinsInReserve.contains(it))) {
                    if (!coinsInBox.contains(it)) {
                        coinsInReserve.remove(it)
                    }
                    coinsInBox.remove(it)
                    coinReturn.add(it)
                    valueToReturn -= coinValues[it]
                }
            }
        }

        coinsInReserve.addAll(coinsInBox)
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
