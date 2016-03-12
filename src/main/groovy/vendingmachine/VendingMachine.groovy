package vendingmachine

class VendingMachine {
    def private displayText = "INSERT COIN"

    def display() {
        displayText
    }

    def insert(value) {
        displayText = "0.05"
    }
}
