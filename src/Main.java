public class Main {

    public static void main(String[] args) {
        Deck testDeck = new Deck();
        System.out.println("\nNewly generated deck of cards:\n");
        testDeck.shuffle();
        testDeck.showDeck();
        System.out.println("\nGetting the first card from the top of the card deck\n");
        testDeck.showCard(testDeck.getDeckHead());
        testDeck.getRemainingCards();
        System.out.println("\nPicking a random card from the deck:\n");
        testDeck.showCard(testDeck.pickACard());
        testDeck.getRemainingCards();
        System.out.println("\nGetting a hand from the top of the card deck:\n");
        testDeck.getAHand();
        testDeck.getRemainingCards();
    }

}