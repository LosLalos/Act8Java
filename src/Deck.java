import java.util.concurrent.ThreadLocalRandom;

public class Deck {

    private Card firstCard, lastCard;
    private int deckLength = 52;

    public Deck() {

        String[] valueSet = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] colorSet = {"Black", "Red"};
        String[] setSet = {"Clovers", "Pikes", "Hearts", "Diamonds"};
        int color = 0;

        for (int clock_1 = 0; clock_1 < 4; clock_1++) {

            if(clock_1 == 2) {
                color = 1;
            }

            for (int clock_2 = 0; clock_2 < 13; clock_2++) {
                Card newCard = new Card(setSet[clock_1], colorSet[color], valueSet[clock_2]);

                if(firstCard != null) {
                    lastCard.setNextCard(newCard);
                    newCard.setPreviousCard(lastCard);
                }
                else {
                    firstCard = newCard;
                }
                lastCard = newCard;

            }

        }

    }

    /**
     * @param cardToShow The card that will be shown on the console.
     */
    public void showCard(Card cardToShow) {
        cardToShow.getCard();
    }

    /**
     * @return The first card from the deck.
     */
    public Card getDeckHead() {
        Card cardPicker = firstCard;

        if (deckLength != 1) {
            firstCard = firstCard.getNextCard();
            firstCard.setPreviousCard(cardPicker.getPreviousCard());
        }else {
            firstCard = null;
            lastCard = null;
        }
        deckLength--;
        return cardPicker;
    }

    /**
     *  Gets the last card from the deck.
     * @return The last card from the deck.
     */
    private Card getDeckTail(){
        Card cardPicker = lastCard;

        if(deckLength != 1) {
            lastCard = lastCard.getPreviousCard();
            lastCard.setNextCard(cardPicker.getNextCard());
        } else {
            firstCard = null;
            lastCard = null;
        }
        deckLength--;
        return cardPicker;
    }

    /**
     *  Picks a random card from the deck.
     * @return The randomly chosen card.
     */
    public Card pickACard() {

        int random = ThreadLocalRandom.current().nextInt(1, deckLength + 1);

        if (random != 1 && random != deckLength) {
            Card cardPicker = firstCard;

            for (int clock = 1; clock < random; clock++) {
                cardPicker = cardPicker.getNextCard();
            }

            cardPicker.getPreviousCard().setNextCard(cardPicker.getNextCard());
            cardPicker.getNextCard().setPreviousCard(cardPicker.getPreviousCard());
            deckLength--;

            return cardPicker;

        } else if (random == 1) {
            return getDeckHead();
        } else {
            return getDeckTail();
        }

    }

    /**
     *  Randomly shuffles the entire deck of cards.
     */
    public void shuffle() {
        Card cardPicker, newFirstCard = null, newLastCard = null, cardCreator;
        int length = deckLength;

        for (int clock = 0; clock < length; clock++) {
            cardPicker = pickACard();
            cardCreator = new Card(cardPicker.getCardSet(), cardPicker.getCardColor(), cardPicker.getCardValue());

            if(newFirstCard != null) {
                newLastCard.setNextCard(cardCreator);
                cardCreator.setPreviousCard(newLastCard);
            }
            else {
                newFirstCard = cardCreator;
            }
            newLastCard = cardCreator;

        }

        firstCard = newFirstCard;
        lastCard = newLastCard;
        deckLength = length;

        System.out.println("[The cards were shuffled successfully]");

    }

    /**
     *  Gets and shows 5 cards from the top of the card deck.
     */
    public void getAHand() {
        if(deckLength > 5) {
            for (int clock = 0; clock < 5; clock++) {
                showCard(getDeckHead());
            }
        }
        else {
            System.out.println("Not enough card for a hand");
        }
    }

    /**
     *  Shows the entire deck of cards.
     */
    public void showDeck() {
        Card cardPointer = firstCard;
        for (int clock = 0; clock < deckLength; clock++) {
            cardPointer.getCard();
            cardPointer = cardPointer.getNextCard();
        }
    }

    /**
     *  Prints the remaining number of cards left int the deck.
     */
    public void getRemainingCards() {
        System.out.println("Remaining cards: " + deckLength);
    }

}
