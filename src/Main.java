import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        // Game's deck.
        Deck deck = new Deck();
        // User's chosen menu option.
        int userInput = 0;
        // "exit" tracks if the user wants to end the app.
        // "error" tracks if there was an input error.
        boolean exit = false, error;

        System.out.println("\nWelcome to Poker!");

        do {
            printOptionMenu();

            // Does the process till the user inputs valid menu option.
            do {
                try {
                    // Gets user input.
                    userInput = userInputReader();
                    // Invalid menu option.
                    if (userInput > 4 || userInput < 0) {
                        throw new Exception();
                    }
                    // No error was detected.
                    error = false;
                } catch (Exception e) {
                    // Error was detected.
                    System.out.print("Invalid input!\n>>>\s");
                    error = true;
                }
            } while (error);

            System.out.println("\n---------------------------------------");
            // Checks which option the user has chosen.
            switch (userInput) {
                case 1 -> { // Shuffle action.
                    System.out.println("The deck was shuffled successfully!");
                    deck.shuffle();
                }
                case 2 -> { // Get first card action.
                    deck.showCard(deck.getDeckHead());
                    deck.getRemainingCards();
                }
                case 3 -> { // Get random card action.
                    deck.showCard(deck.pickACard());
                    deck.getRemainingCards();
                }
                case 4 -> { // Get a 5 card hand action.
                    deck.getAHand();
                    deck.getRemainingCards();
                }
                case 0 -> { // Exit the app action.
                    exit = true;
                    System.out.println("The app was closed successfully!");
                }
            }
            System.out.println("---------------------------------------");

            // Deck has run out of cards.
            if(deck.getDeckLength() == 0){
                // Game is finished.
                exit = true;
                System.out.println(">>> The deck has run out of cards. Thanks for playing.");
            }

        } while (!exit);
    }

    /**
     * Prints the game's menu.
     */
    public static void printOptionMenu() {
        System.out.print("""

                Select one of the following options:
                [1] Shuffle the deck
                [2] Pick a card form the top of the deck
                [3] Pick a card from the deck randomly
                [4] Get a 5 card hand from the deck
                [0] Exit the program
                
                >>>\s""");
    }

    /**
     * Reads and returns the user's keyboard input.
     * @return The user's input.
     * @throws IOException I/O transaction got interrupted.
     */
    public static int userInputReader() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((System.in)));
        return Integer.parseInt(bufferedReader.readLine());
    }

}