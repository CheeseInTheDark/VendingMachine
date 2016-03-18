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

    def claimCoins(valueToClaim) {
        def valueOfCoinsHeldBeforeClaiming = valueOfCoins()
        coinsInReserve.addAll(coinsInBox)
        coinsInBox = []

        if (valueToClaim != null) {
            def changeDue = valueOfCoinsHeldBeforeClaiming - valueToClaim
            returnChange(changeDue)
        }
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
