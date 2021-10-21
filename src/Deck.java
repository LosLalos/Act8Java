import java.util.concurrent.ThreadLocalRandom;

public class Deck {

    // First and last cards of the deck.
    private Card firstCard, lastCard;
    // The number of cards the deck has left.
    private int deckLength = 52;

    // Generates a new deck of 52 cards.
    public Deck() {
        // Generated deck values.
        String[] valueSet = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] colorSet = {"Black", "Red"};
        String[] setSet = {"Clovers", "Pikes", "Hearts", "Diamonds"};
        // Color controller.
        int color = 0;

        // Generates one card set at the time.
        for (int clock_1 = 0; clock_1 < 4; clock_1++) {

            // Changes the color to match the card's set value.
            if (clock_1 == 2) {
                color = 1;
            }

            // Generates all the cards of the set.
            for (int clock_2 = 0; clock_2 < 13; clock_2++) {
                // Card is generated.
                Card newCard = new Card(setSet[clock_1], colorSet[color], valueSet[clock_2]);

                // Deck already has a first card position.
                if (firstCard != null) {
                    // Card is inserted into the deck.
                    lastCard.setNextCard(newCard);
                    newCard.setPreviousCard(lastCard);
                }
                // If not it doesn't.
                else {
                    // First card is set.
                    firstCard = newCard;
                }
                // Last card is reset.
                lastCard = newCard;
            }
        }
    }

    /**
     * Shows the card's values.
     * @param cardToShow The card that will be shown on the console.
     */
    public void showCard(Card cardToShow) {
        cardToShow.getCard();
    }

    /**
     * Gets the first card of the card deck.
     * @return The first card of the deck.
     */
    public Card getDeckHead() {
        // Current first card is saved into this variable.
        Card cardPicker = firstCard;

        // Deck still has more than one card.
        if (deckLength != 1) {
            // New first card is set.
            firstCard = firstCard.getNextCard();
            firstCard.setPreviousCard(cardPicker.getPreviousCard());
        }
        // Deck has only one card left.
        else {
            // First and last cards are set to null.
            firstCard = null;
            lastCard = null;
        }

        // Deck length is updated.
        deckLength--;

        // First card is returned.
        return cardPicker;
    }

    /**
     * Gets the last card of the card deck.
     * @return The last card of the deck.
     */
    public Card getDeckTail() {
        // Current last card is saved into this variable.
        Card cardPicker = lastCard;

        // Deck still has more than one card.
        if (deckLength != 1) {
            // New last card is set.
            lastCard = lastCard.getPreviousCard();
            lastCard.setNextCard(cardPicker.getNextCard());
        }
        // Deck has only one card left.
        else {
            // First and last cards are set to null.
            firstCard = null;
            lastCard = null;
        }

        // Deck length is updated.
        deckLength--;

        // Last card is returned.
        return cardPicker;
    }

    /**
     * Picks a random card from the deck.
     * @return The randomly chosen card.
     */
    public Card pickACard() {
        // Deck card that is going to get picked.
        int random = ThreadLocalRandom.current().nextInt(1, deckLength + 1);

        // Deck still has more than one card.
        if (random != 1 && random != deckLength) {
            // Deck is accessed.
            Card cardPicker = firstCard;

            // Moves to the positions of the picked card.
            for (int clock = 1; clock < random; clock++) {
                cardPicker = cardPicker.getNextCard();
            }

            // Card is removed from deck.
            cardPicker.getPreviousCard().setNextCard(cardPicker.getNextCard());
            cardPicker.getNextCard().setPreviousCard(cardPicker.getPreviousCard());

            // Deck length is updated.
            deckLength--;

            // Picked card is returned.
            return cardPicker;
        }
        // First card get picked.
        else if (random == 1) {
            // First card is returned.
            return getDeckHead();
        }
        // Last card gets picked.
        else {
            // Last card is returned.
            return getDeckTail();
        }
    }

    /**
     * Randomly shuffles the entire deck of cards.
     */
    public void shuffle() {
        //"cardPicker" accesses the deck.
        //"newFirstCard" will be the new firstCard of the deck.
        //"newLastCard" will be the new lastCard of the deck.
        //"cardCreator" creates new cards.
        //"length" preserves the current length of the deck.
        Card cardPicker, newFirstCard = null, newLastCard = null, cardCreator;
        int length = deckLength;

        // Randomly picks all the cards of the deck to create a new one.
        for (int clock = 0; clock < length; clock++) {
            // Randomly picked card.
            cardPicker = pickACard();
            // Card's next and previous card positions are reset.
            cardCreator = new Card(cardPicker.getCardSet(), cardPicker.getCardColor(), cardPicker.getCardValue());

            // New deck already has a first card position.
            if (newFirstCard != null) {
                // Card is inserted into the new deck.
                newLastCard.setNextCard(cardCreator);
                cardCreator.setPreviousCard(newLastCard);
            }
            // If not it doesn't.
            else {
                // New first card of the deck is set.
                newFirstCard = cardCreator;
            }
            // New last card is reset.
            newLastCard = cardCreator;
        }

        // First and last cards are reset.
        firstCard = newFirstCard;
        lastCard = newLastCard;

        // Deck length is restored.
        deckLength = length;
    }

    /**
     * Gets and shows 5 cards from the top of the card deck.
     */
    public void getAHand() {
        // Deck has 5 cards or more.
        if (deckLength >= 5) {
            for (int clock = 0; clock < 5; clock++) {
                showCard(getDeckHead());
            }
        }
        // Deck has less than 5 cards.
        else {
            System.out.println("The deck has less than 5 cards. We cannot give you a hand of cards.");
        }
    }

    /**
     * Shows the entire deck of cards.
     */
    public void showDeck() {
        // Deck is accessed.
        Card cardPointer = firstCard;

        // Cycles throughout all the cards.
        for (int clock = 0; clock < deckLength; clock++) {
            // Shows the card's values.
            showCard(cardPointer);
            // Moves to the next card position.
            cardPointer = cardPointer.getNextCard();
        }
    }

    /**
     * Prints the remaining number of cards left in the deck.
     */
    public int getRemainingCards() {
        System.out.println("\nThe deck has " + deckLength + " cards left.");
        return deckLength;
    }

    /**
     * Returns the remaining number of cards left in the deck.
     * @return number of cards left.
     */
    public int getDeckLength() {
        return deckLength;
    }
}
