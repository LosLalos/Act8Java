import java.io.BufferedReader;
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
                    // Gets and checks user input.
                    userInput = userInputReader();
                    // No error was detected.
                    error = false;
                } catch (Exception e) {
                    // Error was detected.
                    System.out.print("\n[!] \"Invalid input! Please try again.\"\n>>>\s");
                    error = true;
                }
            } while (error);

            System.out.println("\n\n---------------------------------------");
            // Checks which option the user has chosen.
            try {
                switch (userInput) {
                    case 1 -> // Shuffle action.
                            deck.shuffle();
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
                        System.out.println("[!] \"The app was closed successfully!\"");
                    }
                }
            }
            // Deck has run out of cards.
            catch (Exception e) {
                // Game is finished.
                System.out.println("\n[!] \"The deck has run out of cards. Thanks for playing.\"\n");
                exit = true;
            }
            System.out.println("---------------------------------------");

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
     * Reads, checks, and returns the user's keyboard input.
     * @return A valid user input.
     * @throws Exception User's keyboard input is not an integer.
     */
    public static int userInputReader() throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((System.in)));
        int userInput = Integer.parseInt(bufferedReader.readLine());

        // Invalid menu option.
        if (userInput > 4 || userInput < 0) {
            throw new Exception();
        }

        return userInput;
    }

}