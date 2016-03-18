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

            while (valueToReturn >= 0.25 && (coinsInBox.contains("QUARTER") || coinsInReserve.contains("QUARTER"))) {
                if (!coinsInBox.contains("QUARTER")) {
                    coinsInReserve.remove("QUARTER")
                }
                coinsInBox.remove("QUARTER")
                coinReturn.add("QUARTER")
                valueToReturn -= 0.25
            }

            while (valueToReturn >= 0.10 && (coinsInBox.contains("DIME") || coinsInReserve.contains("DIME"))) {
                coinReturn.add("DIME")
                if (!coinsInBox.contains("DIME")) {
                    coinsInReserve.remove("DIME")
                }
                coinsInBox.remove("DIME")

                valueToReturn -= 0.10
            }

            while (valueToReturn >= 0.05 && (coinsInBox.contains("NICKEL") || coinsInReserve.contains("NICKEL"))) {
                coinReturn.add("NICKEL")
                if (!coinsInBox.contains("NICKEL")) {
                    coinsInReserve.remove("NICKEL")
                }
                coinsInBox.remove("NICKEL")
                valueToReturn -= 0.05
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
