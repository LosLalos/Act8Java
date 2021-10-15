public class Card {

    // Card values.
    private final String cardSet, cardColor, cardValue;
    // Next and previous cards.
    private Card nextCard, previousCard;

    // Card constructor.
    public Card(String cardSet, String cardColor, String cardValue) {
        this.cardSet = cardSet;
        this.cardColor = cardColor;
        this.cardValue = cardValue;
    }

    // << Setters >>
    public void setNextCard(Card nextCard) {
        this.nextCard = nextCard;
    }

    public void setPreviousCard(Card previousCard) {
        this.previousCard = previousCard;
    }

    // << Getters >>
    public Card getNextCard() {
        return nextCard;
    }

    public Card getPreviousCard() {
        return previousCard;
    }

    public void getCard() {
        System.out.println("{" + cardSet + "},{" + cardColor + "},{" + cardValue + "}");
    }

    public String getCardSet() {
        return cardSet;
    }

    public String getCardColor() {
        return cardColor;
    }

    public String getCardValue() {
        return cardValue;
    }
}