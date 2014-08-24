package boggle;

import javafx.application.Platform;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by spandan on 8/24/14.
 */
public class Control implements Runnable, MoveChecker {

    Map<Integer, Set<String>> table;
    char[][] grid = null;
    Set<String> dictionary = null;

    public Control(Map<Integer, Set<String>> table, char[][] grid, Set<String> dict) {
        this.table = table;
        this.grid = grid;
        this.dictionary = dict;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Game Over !! computing scores ..");
        Platform.exit();

        // compute the scores

        System.out.println("Responses :" + table);

        computeScore();

    }

    private void computeScore() {
        Map<Integer, Integer> scoretable = new TreeMap<>();

        // find all the unique values

        Map<String, Integer> count = new HashMap<>();

        for (Integer player : this.table.keySet()) {
            for (String v : this.table.get(player)) {
                if (!count.containsKey(v)) {
                    count.put(v, 1);
                } else {
                    assert count.get(v) != null;
                    count.put(v, count.get(v) + 1);
                }
            }
        }

        for (Integer player : this.table.keySet()) {

            Set<String> wordslisted = this.table.get(player);

            if (wordslisted.size() == 0) {
                scoretable.put(player, 0);
                continue;
            }

            int score = 0;

            for (String v : wordslisted) {
                if (count.get(v) == 1 && isValidMove(v))
                    score += v.length();

                scoretable.put(player, score);
            }
        }

        System.out.println("Final Scores !");

        for (Integer player : scoretable.keySet()) {
            System.out.println("Player id = " + player + " : score = " + scoretable.get(player));
        }
    }

    public boolean isValidMove(String word) {
        int rows = this.grid.length;
        int cols = this.grid[0].length;

        // search all rows

        for (int r = 0; r < rows; r++) {
            String tmp = "";

            for (int c = 0; c < cols; c++)
                tmp += this.grid[r][c];

            if (tmp.contains(word) && this.dictionary.contains(word))
                return true;
        }

        // search all cols

        for (int c = 0; c < cols; c++) {
            String tmp = "";

            for (int r = 0; r < rows; r++) {
                tmp += this.grid[r][c];
            }

            if (tmp.contains(word) && this.dictionary.contains(word))
                return true;
        }

        return false;
    }
}