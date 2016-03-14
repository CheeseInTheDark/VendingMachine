package vendingmachine

class CollectionTray
{
    def private items = []

    def add(item) {
        items << item
    }

    def collectItemsInTray() {
        def collectedItems = items
        items = []
        return collectedItems
    }
}
