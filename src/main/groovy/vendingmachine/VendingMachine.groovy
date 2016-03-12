package vendingmachine

class VendingMachine {
    def private displayText = "INSERT COIN"
    def private amount = 0.0

    def display() {
        displayText
    }

    def insert(value) {
        amount += 0.05
        displayText = amount.toString()
    }
}
