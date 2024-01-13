import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a player in the game.
 * You are not allowed to add any private or public attributes to this class.
 * You are not allowed to add any public methods to this class.
 * You can, however, add private methods to this class.
 */
public class Player {
    /**
     * The number of AI players created so far.
     */
    private static int aiCount = 0;
    /**
     * The number of cards in a player's hand.
     */
    private static final int HAND_SIZE = 12;
    /**
     * The cards in the player's hand.
     */
    private Weather[] cards = new Weather[HAND_SIZE];
    /**
     * The number of life preservers the player has remaining.
     */
    private int remainingLifePreservers;
    /**
     * Whether the player has been eliminated from that round.
     */
    private boolean eliminated;
    /**
     * The tide level of the player.
     */
    private int tideLevel;
    /**
     * The player's game score (accumminated from round 1).
     */
    private int score;
    /**
     * Whether the player is human or AI.
     */
    private boolean isHuman;
    /**
     * The name of the player.
     */
    private String name;

    /**
     * Returns the name of the player.
     *
     * @return the name of the player.
     */
    public String getName() {
        //TODO
        return name;
    }

    /**
     * Returns the tide level of the player.
     *
     * @return the tide level of the player.
     */
    public int getTideLevel() {
        //TODO
        return tideLevel;
    }

    /**
     * Constructor Creates a new player with the given name.
     *
     * @param name the name of the player.
     */
    public Player(String name) {
        //TODO
        this.name = name;
        isHuman = true;
        score = 0;
    }

    /**
     * Creates a new AI player with a default name.
     * The name of AI is "AI 1", "AI 2", "AI 3", etc.
     * Their name does not repeat.
     */
    public Player() {
        //TODO
        aiCount++;
        this.name = "AI " + aiCount;
        score = 0;
    }

    /**
     * Returns whether the player is human or AI.
     *
     * @return whether the player is human or AI.
     */
    public boolean isHuman() {
        //TODO
        this.isHuman = true;

        for (int i = 1; i < aiCount; i++) {
            if (getName().equalsIgnoreCase("AI " + i)) {
                return !isHuman;
            }
        }
        return isHuman;
    }

    /**
     * Resets the player for a new round.
     * In each new round they should have reset their attributes except
     * their name and score.
     */
    public void resetForNewRound() {
        //TODO
        remainingLifePreservers = 0;
        eliminated = false;
        tideLevel = 0;
        cards = new Weather[HAND_SIZE];
    }

    /**
     * Sets the tide level of the player. It will be called if the player
     * needs to take a tide card in a turn.
     *
     * @param tideLevel the tide level of the player.
     */
    public void setTideLevel(int tideLevel) {
        //TODO
        this.tideLevel = tideLevel;
    }

    /**
     * This method is to deal a weather card to the player
     *
     * @param c the card to be added.
     */
    public void addCard(Weather c) {
        //TODO
        for (int i = 0; i < HAND_SIZE; i++) {
            if (cards[i] == null) {
                cards[i] = c;
                break;
            }
        }
    }

    /**
     * This method is to play a card from the player's hand.
     *
     * @param index the index of the card to be played.
     * @return the card to be played.
     */
    public Weather playCard(int index) {
        //TODO
        Weather card = cards[index];
        Weather[] newSize= new Weather[cards.length - 1];

        if (index >= 0 && index < cards.length) {
            for (int i = 0; i < index; i++)
                newSize[i] = cards[i];
            for (int i = index; i < newSize.length; i++)
                newSize[i] = cards[i+1];
            cards = newSize;
        }

        return card;
    }

    /**
     * Returns the number of cards in the player's hand.
     *
     * @return the number of cards in the player's hand.
     */
    public int getCardCount() {
        //TODO
        int count = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns a random card from the player's hand.
     *
     * @return the card to be played.
     */
    public Weather playRandomCard() {
        //TODO
        int cardIndex = ThreadLocalRandom.current().nextInt(0, getCardCount());
        return cards[cardIndex];
    }

    /**
     * To compute the initialize life preservers for the player.
     */
    public void calcLifePreservers() {
        //TODO
        int count = 0;
        for (int i = 0; i < getCardCount(); i++) {
            count = cards[i].getLifePreserver() + count;
        }
        remainingLifePreservers = count/2;
    }

    /**
     * Returns the number of life preservers the player has remaining.
     */
    public int getLifePreservers() {
        //TODO
        if (remainingLifePreservers < 0)
            return 0;
        return remainingLifePreservers;
    }

    /**
     * Decreases the number of life preservers the player has remaining.
     */
    public void decreaseLifePreservers() {
        //TODO
        remainingLifePreservers--;
    }

    /**
     * Returns whether the player has been eliminated from that round.
     */
    public boolean isEliminated() {
        //TODO
        this.eliminated = true;

        if (remainingLifePreservers < 0) {
            return eliminated;
        } else {
            return !eliminated;
        }
    }

    /**
     * Returns the player's game score (accumminated from round 1).
     */
    public int getScore() {
        //TODO
        return score;
    }

    /**
     * Set the player's game score (accumminated from round 1).
     */
    public void setScore(int score) {
        //TODO
        this.score += score;
    }

    /**
     * Prints the player's hand.
     * Please refer to the sample program for the proper format.
     * The order of the card will not affect the correctness of the program.
     * <p>
     * It prints nothing if the player is AI.
     */
    public void printHand() {
        //TODO
        if (isHuman) {
            for (int i = 0; i < cards.length; i++) {
                System.out.print(cards[i].toString()+" ");
            }
        }
    }
}


