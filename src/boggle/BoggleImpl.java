package boggle;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by spandan on 8/20/14.
 */
public class BoggleImpl {
    Set<String> dictionary = null;
    char[][] grid = null;
    Map<Integer, Set<String>> table = null;

    public BoggleImpl(int num_players, String dict) {

        // construct the grid

        genGrid();

        // construct the dictionary

        this.dictionary = new HashSet<String>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(dict));

            assert br != null;

            String word = null;

            while ((word = br.readLine()) != null) {
                this.dictionary.add(word.trim());
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // display the grid

        System.out.println("---GRID---");
        displayGrid();
        System.out.println("--GRID--");


        // create gui elements for each player. Also setup other data structures at this point.

        this.table = new HashMap<Integer, Set<String>>();

        for (int i = 0; i < num_players; i++) {
            this.table.put(new Integer(i), new HashSet<String>());

            // construct gui element for this player.

            Platform.runLater(new Player(i, this.table.get(i)));
        }

        // start Timer thread.

        new Thread(new Control(this.table, this.grid, this.dictionary)).start();
    }


    private void genGrid() {
        int max = 32;
        int min = 3;

        Random rand = new Random();

        int N = rand.nextInt((max - min) + 1) + min;

        this.grid = new char[N][N];

        char[] alphabets = new char[26];

        int i = 0;

        for (char ch = 'a'; ch <= 'z'; ch++) {
            alphabets[i++] = ch;
        }

        for (i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.grid[i][j] = alphabets[rand.nextInt(26)];
            }
        }
    }

    public void displayGrid() {
        int rows = this.grid.length;
        int cols = this.grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(this.grid[i][j]);
            }

            System.out.println("");
        }
    }
}