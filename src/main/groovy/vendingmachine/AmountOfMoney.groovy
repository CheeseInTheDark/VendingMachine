package vendingmachine

class AmountOfMoney {
    def amount = 0.0

    def add(amount) {
        this.amount += amount
    }

    def amount() { amount }

    def setToZero() { amount = 0.0 }
}
