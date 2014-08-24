import boggle.BoggleImpl;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by spandan on 8/20/14.
 */
public class Boggle extends Application {

    static int num_players;
    static final String dict_path = "./data/dictionary.txt";

    public static void main(String[] args) {

        while (true) {
            System.out.print("Enter the number of players:");
            Scanner in = new Scanner(System.in);

            try {
                num_players = in.nextInt();
                if (num_players <= 0)
                    System.out.println("num_players should be +ve .. enter again");
                else
                    break;
            } catch (InputMismatchException e) {
                System.out.println("Enter a +ve integer .. try again ..");
            }
        }

        // start a new boggle game

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        assert stage != null;

        BoggleImpl boggle = new BoggleImpl(num_players, dict_path);
    }
}