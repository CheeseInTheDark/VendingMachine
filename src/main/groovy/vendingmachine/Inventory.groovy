package vendingmachine

import static vendingmachine.Selections.candy
import static vendingmachine.Selections.chips
import static vendingmachine.Selections.cola

class Inventory
{
    def private productCounts

    Inventory() {
        productCounts = new HashMap<String, Integer>()
        productCounts.put(candy.name, 0)
        productCounts.put(chips.name, 0)
        productCounts.put(cola.name, 0)
    }

    def has(name) {
        productCounts.get(name) > 0
    }

    def add(name, amountToAdd) {
        def previousQuantity = productCounts.get(name)
        def newQuantity = previousQuantity + amountToAdd
        productCounts.put(name, newQuantity)
    }

    def removeOneOf(name) {
        def newQuantity = productCounts.get(name) - 1
        productCounts.put(name, newQuantity)
    }
}
