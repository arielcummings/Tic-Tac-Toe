package com.example.gameapp;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class GameApp extends Application {

    // 9 buttons
    private Button[][] buttons = new Button[3][3]; // Corrected "Private" to "private" and defined "buttons"

    // Restart Game Button
    Button restartButton = new Button("Restart Game");

    // Game instance and computer instance
    private Game game;
    private Computer computer;

    // shows status messages
    private Label statusLabel = new Label("Player X's turn"); //start with "X"

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tic-Tac-Toe Game");
        GridPane gridPane = new GridPane();  // create grid

        //  loop here to create 9 buttons
        // loop also assigns action (hence setOnAction)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button("");
                buttons[i][j].setMinSize(50, 50); // set button size to 50/50 just to see
                gridPane.add(buttons[i][j], j, i);
                final int row = i, col = j;
                buttons[i][j].setOnAction(e -> handleMove(row, col));
            }
        }

        // add restart game button
        restartButton.setOnAction(e -> restartGame());
        gridPane.add(restartButton, 1, 3, 2, 1); // This places the button in the grid

        // set grid spacing
        gridPane.setHgap(6);
        gridPane.setVgap(6);

        // create a VBox for layout (including GridPane and restart button)
        VBox root = new VBox(10);
        root.getChildren().addAll(gridPane, statusLabel, restartButton); // Add status label and grid to VBox

        // create and set up the scene
        Scene scene = new Scene(root, 200, 250); // Use VBox as the root for the scene
        primaryStage.setScene(scene);
        primaryStage.show();

        // initialize game and computer
        game = new Game();
        computer = new Computer(game);
        game.startGame();
    }

    // Method to handle player moves
    private void handleMove(int i, int j) {
        if (buttons[i][j].getText().isEmpty()) {
            String currentPlayer = game.getCurrentPlayerSym();
            buttons[i][j].setText(currentPlayer);
            game.placeMarker(i, j); // Place marker on game board

            // Check if the game has been won after player's move
            if (game.checkScore()) {
                statusLabel.setText("Player " + currentPlayer + " wins!");
            } else {
                // Change to next player (computer's move if it's "O")
                game.nextPlayer();

                // If it's the computer's turn, let the computer make its move
                if (game.getCurrentPlayerSym().equals("O")) {
                    statusLabel.setText("Player O's turn");
                    computerMove(); // Let the computer make its move
                } else {
                    statusLabel.setText("Player X's turn");
                }
            }
        }
    }

    // method for the computer to make its move
    private void computerMove() {
        // let the computer make a move
        computer.move();

        // update buttons with the computer's move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (game.getBoard()[i][j].equals("O")) {
                    buttons[i][j].setText("O");
                }
            }
        }

        // check if the computer wins after it moves
        if (game.checkScore()) {
            statusLabel.setText("Player O wins!");
        } else {
            game.nextPlayer(); // switch back to player X
            statusLabel.setText("Player X's turn");
        }
    }

    // restart the game
    private void restartGame() {
        game.startGame();
        // clear board
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        // reset status label
        statusLabel.setText("Player X's turn");
    }

    // main to run javafx app
    public static void main(String[] args) {
        Application.launch(args);
    }
}





