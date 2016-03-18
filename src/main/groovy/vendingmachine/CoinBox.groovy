package vendingmachine

class CoinBox
{
    def private coinValues =
            ["NICKEL": 0.05,
             "DIME": 0.10,
             "QUARTER": 0.25]

    def private coinsInBox = []
    def coinReturn

    def private quarterInReserve = false
    def private dimesInReserve = 0
    def private nickelsInReserve = 0
    def addToReserves(coin) {
        if (coin == "DIME") {
            dimesInReserve++
        } else if (coin == "NICKEL") {
            nickelsInReserve++
        } else {
            quarterInReserve = true
        }
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

            while (valueToReturn >= 0.25 && (coinsInBox.contains("QUARTER") || quarterInReserve)) {
                quarterInReserve = false
                coinReturn.add("QUARTER")
                coinsInBox.remove("QUARTER")
                valueToReturn -= 0.25
            }

            while (valueToReturn >= 0.10 && (coinsInBox.contains("DIME") || dimesInReserve > 0)) {
                if (!coinsInBox.contains("DIME")) {
                    dimesInReserve--
                }
                coinsInBox.remove("DIME")
                coinReturn.add("DIME")
                valueToReturn -= 0.10
            }

            while (valueToReturn >= 0.05 && (coinsInBox.contains("NICKEL") || nickelsInReserve > 0)) {
                coinReturn.add("NICKEL")
                if (!coinsInBox.contains("NICKEL")) {
                    nickelsInReserve--
                }
                valueToReturn -= 0.05
            }
        }
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
