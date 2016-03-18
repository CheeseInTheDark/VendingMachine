package vendingmachine

class CoinBox
{
    def private coinValues =
            ["NICKEL": 0.05,
             "DIME": 0.10,
             "QUARTER": 0.25]

    def private coinsInBox = []
    def coinReturn

    def private quartersInReserve = 0
    def private dimesInReserve = 0
    def private nickelsInReserve = 0
    def addToReserves(coin) {
        if (coin == "DIME") {
            dimesInReserve++
        } else if (coin == "NICKEL") {
            nickelsInReserve++
        } else {
            quartersInReserve++
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

            while (valueToReturn >= 0.25 && (coinsInBox.contains("QUARTER") || quartersInReserve > 0)) {
                if (!coinsInBox.contains("QUARTER")) {
                    quartersInReserve--
                }
                coinsInBox.remove("QUARTER")
                coinReturn.add("QUARTER")
                valueToReturn -= 0.25
            }

            while (valueToReturn >= 0.10 && (coinsInBox.contains("DIME") || dimesInReserve > 0)) {
                coinReturn.add("DIME")
                if (!coinsInBox.contains("DIME")) {
                    dimesInReserve--
                }
                coinsInBox.remove("DIME")

                valueToReturn -= 0.10
            }

            while (valueToReturn >= 0.05 && (coinsInBox.contains("NICKEL") || nickelsInReserve > 0)) {
                coinReturn.add("NICKEL")
                if (!coinsInBox.contains("NICKEL")) {
                    nickelsInReserve--
                }
                coinsInBox.remove("NICKEL")
                valueToReturn -= 0.05
            }
        }

        while (coinsInBox.contains("QUARTER")) {
            coinsInBox.remove("QUARTER")
            quartersInReserve++
        }

        while (coinsInBox.contains("DIME")) {
            coinsInBox.remove("DIME")
            dimesInReserve++
        }

        while (coinsInBox.contains("NICKEL")) {
            coinsInBox.remove("NICKEL")
            nickelsInReserve++
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
