package boggle;

/**
 * This interface specifies the interactions between the boggle game and the
 * valid words - as specified by the board and the dictionary.
 */
public interface MoveChecker {
  /* You will have to take in things like a dictionary, board array in
     constructor or using mutators */

    /**
     * isValidMove  checks if a given word is valid
     *
     * @param word The string entered by the user
     * @return A boolean representing whether the word fits the following:
     * - Is found on the board
     * - Is in the dictionary
     */
    public boolean isValidMove(String word);
}