package vendingmachine

class CoinReturn
{
    def private returnedCoins = []

    def add(coin) {
        returnedCoins << coin
    }

    def collectCoins() {
        def collectedCoins = returnedCoins
        returnedCoins = []
        return collectedCoins
    }
}
