package boggle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Set;

/**
 * Created by spandan on 8/21/14.
 */
public class Player implements Runnable {

    Integer id = null;
    Set<String> words = null;

    public Player(Integer id, Set<String> words) {
        this.id = id;
        this.words = words;
    }

    @Override
    public void run() {
        System.out.println("Player id = " + id + ": game begins .. start working");

        Stage stage = new Stage();
        final TextField txtfield = new TextField();
        txtfield.setPrefColumnCount(5 * 75);
        Group group = new Group(txtfield);
        Scene scene = new Scene(group);
        stage.setScene(scene);

        txtfield.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                words.add(txtfield.getText().trim());
                txtfield.clear();
            }
        });

        stage.setTitle("Player id = " + id);
        stage.setWidth(5 * 75);
        Random rand = new Random();
        int max = 1000;
        int min = 100;
        int X = rand.nextInt((max - min) + 1) + min;
        int Y = rand.nextInt((max - min) + 1) + min;
        stage.setX(X);
        stage.setY(Y);
        stage.show();
    }
}