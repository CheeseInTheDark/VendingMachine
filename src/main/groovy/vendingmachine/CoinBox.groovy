package vendingmachine

class CoinBox
{
    def private coinValues =
            ["NICKEL": 0.05,
             "DIME": 0.10,
             "QUARTER": 0.25]

    def private coinsInBox = []
    def private coinsInReserve = []
    def private coinReturn

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

    def valueOfHeldCoins() {
        coinsInBox.sum { coinValues[it] }
    }

    def canMakeChangeForAllProducts() {
        coinsInReserve.count("NICKEL") >= 2 ||
                coinsInReserve.count("NICKEL") >= 1 &&
                coinsInReserve.count("DIME") >= 1
    }

    def claimCoins(amountToClaim) {
        def valueOfCoinsHeldBeforeClaiming = valueOfHeldCoins()
        moveHeldCoinsToReserves()

        if (amountToClaim != null) {
            def changeDue = valueOfCoinsHeldBeforeClaiming - amountToClaim
            returnChange(changeDue)
        }
    }

    def private moveHeldCoinsToReserves() {
        coinsInReserve.addAll(coinsInBox)
        coinsInBox = []
    }

    def private returnChange(changeDue) {
        ["QUARTER", "DIME", "NICKEL"].each { denomination ->
            while (changeDue >= coinValues[denomination] && coinsInReserve.contains(denomination)) {
                move(denomination).fromCoinsInReserveToCoinReturn()
                changeDue -= coinValues[denomination]
            }
        }
    }

    def private move(coin) {
        [fromCoinsInReserveToCoinReturn: {
            coinsInReserve.remove(coin)
            coinReturn.add(coin)
        }]
    }

    def returnCoins() {
        coinsInBox.each { coinReturn.add(it) }
        coinsInBox = []
    }

    def private reject(coin) {
        coinReturn.add(coin)
    }
}
