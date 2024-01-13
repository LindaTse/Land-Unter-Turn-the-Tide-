/**
 * @author: ______Tse Yuk Ling (22233091)_________
 * <p>
 * For the instruction of the assignment please refer to the assignment
 * GitHub.
 * <p>
 * Plagiarism is a serious offense and can be easily detected. Please
 * don't share your code to your classmate even if they are threatening
 * you with your friendship. If they don't have the ability to work on
 * something that can compile, they would not be able to change your
 * code to a state that we can't detect the act of plagiarism. For the
 * first commit of plagiarism, regardless you shared your code or
 * copied code from others, you will receive 0 with an addition of 5
 * mark penalty. If you commit plagiarism twice, your case will be
 * presented in the exam board and you will receive a F directly.
 * <p>
 * Terms about generative AI:
 * You are not allowed to use any generative AI in this assignment.
 * The reason is straight forward. If you use generative AI, you are
 * unable to practice your coding skills. We would like you to get
 * familiar with the syntax and the logic of the Java programming.
 * We will examine your code using detection software as well as
 * inspecting your code with our eyes. Using generative AI tool
 * may fail your assignment.
 * <p>
 * If you cannot work out the logic of the assignment, simply contact
 * us on Discord. The teaching team is more the eager to provide
 * you help. We can extend your submission due if it is really
 * necessary. Just please, don't give up.
 */

import java.util.Scanner;

/**
 * Table class is the main class of the game. It is responsible for the game
 * logic. Complete all TODO parts below
 */
public class Table {
    /**
     * The total number of turns in one round.
     */
    private static final int TOTAL_TURN = 12;
    /**
     * The total number of tide cards. Totally 24.
     */
    private static final int TOTAL_TIDE_CARDS = TOTAL_TURN * 2;
    /**
     * The total number of weather cards. Totally 60.
     */
    private static final int TOTAL_WEATHER_CARD = 60;
    /**
     * The tide cards. Each card has two.
     */
    private int[] tide = new int[TOTAL_TIDE_CARDS];
    /**
     * The weather decks. We divide all 60 Weather cards into several decks.
     * Each deck has 12 cards. There are maximum 5 decks. In each round, a
     * player takes one deck. The decks are shuffled in a deterministic way.
     * After each round, they give the decks to the next player.
     * <p>
     * If there are three players in the game, there should be only three decks.
     * If there are four players in the game, there should be only four decks.
     * If there are five players in the game, there should be five decks.
     * The game does not support two players or less, six players or more.
     */
    private Weather[][] decks;
    /**
     * The players array.
     */
    private Player[] players;

    /**
     * Main method, do not change this method.
     */
    public static void main(String[] arg) {
        System.out.println("Welcome to the Turn the Tile game!");
        int player = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Please enter the number of players you want to play:");
            player = scanner.nextInt();
        } while (player < 2 || player > 5);
        new Table(player).run();
    }

    /**
     * Constructor of the Table class.
     * <p>
     * You should initialize the players array, the tide cards, and the weather
     * decks. The first player should be a human player called "Anya Forger"
     * The rest of the players should be AI players called "AI 1", "AI 2", etc.
     *
     * @param player The number of players.
     */
    public Table(int player) {
        // prepare players array
        // TODO
        players = new Player[player];
        players[0] = new Player("Anya Forger"); //human player

        for (int i = 1; i < player; i++) { //AI player
            players[i] = new Player();
        }

        // prepare tide cards, 1 to 12
        // TODO
        for (int i = 0; i < tide.length; i++) {
            tide[i] = i / 2 + 1;
        }

        int[][] MAGIC_NUMBERS = {
                {31, 26, 54, 19, 8, 45, 39, 16, 28, 42, 3, 38},  //deck1
                {30, 60, 58, 56, 51, 10, 41, 48, 14, 9, 32, 44},  //deck2
                {29, 35, 7, 49, 1, 21, 13, 46, 6, 18, 43, 24},   //deck3
                {27, 34, 33, 11, 12, 47, 23, 55, 40, 37, 22, 15}, //deck4, if any
                {57, 36, 50, 20, 53, 52, 59, 4, 25, 2, 17, 5}     //deck5, if any
        };

        // assign weather cards from the array weather to decks according to the
        // MAGIC_NUMBERS
        // TODO
        decks = new Weather[player][];
        int decksCount = player <= 5 ? player : 0;
        for (int i = 0; i < decksCount; i++) {
            decks[i] = new Weather[TOTAL_TURN];
            for (int j = 0; j < TOTAL_TURN; j++) {
                decks[i][j] = new Weather(MAGIC_NUMBERS[i][j]);
            }
        }
    }

    /**
     * This method is to shuffle the tide cards. Before each round, you should
     * call this method exactly once.
     * <p>
     * This method has been written for you.
     * You cannot change this method.
     */
    private void shuffleTideCard() {
        // A deterministic shuffle required by the program
        int[] order = {4, 23, 8, 11, 3, 16, 21, 14, 0, 6, 18, 15, 19, 9, 5, 12, 13, 2, 17, 7, 10, 20, 1, 22};
        int[] newTide = new int[TOTAL_TIDE_CARDS];
        for (int i = 0; i < TOTAL_TIDE_CARDS; i++) {
            newTide[i] = tide[order[i]];
        }
        tide = newTide;
    }

    /**
     * This method is to run the game for N rounds where N is the number of players.
     * Number of players can be retrieved from the size of the array `players`.
     */
    public void run() {
        // TODO
        for (int round = 0; round < players.length; round++) {
            System.out.println();
            System.out.println();
            System.out.println("Round " + (round + 1) + " starts.");
            System.out.println("---------------------------------");
            System.out.println();
            System.out.println();

            tide = new int[TOTAL_TIDE_CARDS];
            for (int i = 0; i < tide.length; i++) {
                tide[i] = i / 2 + 1;
            }
            for (int j = 0; j <= round; j++) {
                shuffleTideCard();
            }

            oneRound(round);
        }
        // The game should be running for N rounds.
        // We write the first round for you to get you started.
        // oneRound(0);
    }

    /**
     * This method is to run one round of the game. In each round we will
     * play at most 12 turns. If there are less than 3 players remaining,
     * the round ends immediately.
     * <p>
     * You will need to deal weather cards to each player from the decks.
     * Remember the decks must be passed to the another player in the next round.
     * Therefore you should not shuffle the decks. It is not required to
     * sort the deck.
     * <p>
     * After each round, the score of the each player needs to be tallied.
     *
     * @param round The round number.
     */
    private void oneRound(int round) {
        // TODO
        // this method should do much more things and run at most 12 turns
        // unless there are less than 3 players remaining.
        // We write the first turn for you to get you started.
        int maxTurns = 12;


        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < decks[i].length; j++) {
                // Remember the decks must be passed to the another player in the next round.
                // r1, p0,d0, p1,d1 p2,d2;; r2, p0,d1,p1,d2,p2,d0
                players[i].addCard(decks[(round + i) % players.length][j]);
            }
        }

        for (int i = 0; i < players.length; i++) {
            players[i].calcLifePreservers();
        }


        for (int i = 1; i <= maxTurns; i++) {
            System.out.println("Turn " + i + " starts.");
            int remainPlayer = 0;
            for (int j = 0; j < players.length; j++) {
                if (players[j].isEliminated() == false)
                    remainPlayer++;
            }
            if (remainPlayer < 3) {
                break;
            }
            oneTurn();
        }


        for (int i = 0; i < players.length; i++) {
            if (players[i].isEliminated()) {
                players[i].setScore(-1);
            } else {
                players[i].setScore(players[i].getLifePreservers());
            }
            System.out.println(players[i].getName() + " has " + players[i].getScore()
                        + " points.");
            players[i].resetForNewRound();
        }

    }

    /**
     * This method is to draw the first tide card from the tide deck.
     * after the card has been drawn, the array should be resized.
     *
     * @return The value of the tide card.
     */
    private int draw() {
        // TODO
        int tideCard = tide[0];
        int[] newTide = new int[tide.length - 1];

        for (int i = 0; i < newTide.length; i++) {
            newTide[i] = tide[i + 1];
        }
        tide = newTide;
        return tideCard;
    }

    /**
     * This method is to run one turn of the game. In each turn, two tide cards
     * will be drawn. All players will play one weather card. A human player is
     * playing the card at his choice. An AI player is playing the card randomly.
     * <p>
     * You should handle which player will need to be drowned and eliminate them
     * if necessary.
     */
    private void oneTurn() {
        // TODO
        Scanner in = new Scanner(System.in);
        int tideCard1 = draw();
        int tideCard2 = draw();
        Weather[] cards = new Weather[players.length];


        if (tideCard1 > tideCard2) {
            System.out.println("The higher tide is " + tideCard1 + " and the lower tide " +
                    "is " + tideCard2);

        } else {
            System.out.println("The higher tide is " + tideCard2 + " and the lower tide " +
                    "is " + tideCard1);
        }

        for (int j = 0; j < players.length; j++) {
            System.out.println(players[j].getName() + " has tide level " + players[j].getTideLevel()
                    + ", " + players[j].getLifePreservers() + " life preservers");
        }

        for (int i = 0; i < players.length; i++) {
            if (players[i].isEliminated()) {
                System.out.println(players[i].getName() + " is eliminated.");
            }
        }

        players[0].printHand();
        System.out.println();
        int choice;

        if (!players[0].isEliminated()) {
            while (true) {
                try {
                    System.out.print("Player " + players[0].getName() + ", you have " + players[0].getLifePreservers()
                            + " life preservers, please select a card to play: ");
                    choice = in.nextInt();
                    if (choice < players[0].getCardCount())
                        break;
                } catch (Exception e) {

                }
            }
            cards[0] = players[0].playCard(choice);
        }

        for (int k = 0; k < players.length; k++) {
            if (!players[k].isHuman() && !players[k].isEliminated()) {
                cards[k] = players[k].playRandomCard();
            }
        }

        if (tideCard1 > tideCard2) {
            players[findIndexWithBiggestCard(cards)].setTideLevel(tideCard1);
            players[findIndexWithSecondBiggestCard(cards)].setTideLevel(tideCard2);
        } else {
            players[findIndexWithBiggestCard(cards)].setTideLevel(tideCard2);
            players[findIndexWithSecondBiggestCard(cards)].setTideLevel(tideCard1);
        }

        drownPlayer();

    }

    /**
     * This method is to find the index of the player who has the biggest card.
     * Because the weather cards are unique, there should be only one player
     * who has the biggest card.
     * <p>
     * Make sure you retain the array `cards` before the method returns.
     *
     * @param cards The cards played by the players. Note, the array may contain
     *              null values. For example, if there are four players while AI 1
     *              has been eliminated, the cards array may looks like
     *              {<34>, null, <12>, <45>}. <34> means the weather card object
     *              with the value 34. In this example, it should return 3.
     * @return The index of the player who has the biggest card.
     */
    private int findIndexWithBiggestCard(Weather[] cards) {
        // TODO
        int maxIndex = -1;
        int max = -1;

        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != null && cards[i].getValue() >= max) {
                maxIndex = i;
                max = cards[i].getValue();
            }
        }
        return maxIndex;
    }

    /**
     * This is very similar to the method findIndexWithBiggestCard. This method
     * is to find the index of player who plays the second biggest card.
     * <p>
     * Make sure you retain the array `cards` before the method returns.
     *
     * @param cards The cards played by the players. Note, the array may contain
     *              null values. For example, if there are four players while AI 1
     *              has been eliminated, the cards array may looks like
     *              {<34>, null, <12>, <45>}. <34> means the weather card object
     *              with the value 34. In this example, it should return 0.
     * @return The index of the player who has the second biggest card.
     */
    private int findIndexWithSecondBiggestCard(Weather[] cards) {
        // TODO
        int maxIndex = findIndexWithBiggestCard(cards);
        int secondMaxIndex = -1;
        int secondMax = -1;

        for (int i = 0; i < cards.length; i++) {
            if (i != maxIndex && cards[i] != null && cards[i].getValue() >= secondMax) {
                secondMaxIndex = i;
                secondMax = cards[i].getValue();
            }
        }
        return secondMaxIndex;
    }

    /**
     * This method is to drown the player who has the highest tide level.
     * If there are more than one player who has the highest tide level,
     * all of them will be drowned.
     * <p>
     * Player that has been eliminated will not be drowned and their tide
     * level will not be used in the comparison.
     */
    private void drownPlayer() {
        // TODO
        int maxTideLevel = -1;


        // Find the maximum tide level among active players
        for (Player player : players) {
            if (player != null && !player.isEliminated()) {
                int tideLevel = player.getTideLevel();
                if (tideLevel > maxTideLevel) {
                    maxTideLevel = tideLevel;
                }
            }
        }

        // Drown the players with the maximum tide level
        for (Player player : players) {
            if (player != null && !player.isEliminated() && player.getTideLevel() == maxTideLevel) {
                player.decreaseLifePreservers();
            }
        }
    }
}


