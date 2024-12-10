//have to add imports
import javafx.application.Application;
import javafx.scene.Scene ;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;




    public class GameApp extends Application { 

        //9 buttons
        Private Buttons[][] = new Buttons[3][3];

        //Restart Game Button
        Button restartButton = new Button("Restart Game");


        public void start(Stage primaryStage) {
            primaryStage.setTitle("Tic-Tac-Toe Game");
            GridPane gridPane = new GridPane();  //create grid


        //create buttons - to form a loop here for 9 buttons
        //loop must also assign an action (hence setOnAction)
            for(int i = 0; i < rows; i++) {
	            for(int j = 0; j < columns; j++) {
                    buttons[i][j] = new Button("");
                    buttons[i][j].setMinSize(50,50);
            //set button size to 50/50 just to see
                gridPane.add(buttons[i][j], j, i);

                }
            }    

            //Add restart game button
                restartButton.setOnAction(e -> restartGame());
                gridPane.add(restartButton, 1, 3, 2, 1);

                buttons[i][j].setOnAction(e-> handleMove(i, j));

            //should use the methods in Game class
                Game game = new Game();
                game.TicTacToe();
                game.CheckWin();
//end for

            //space buttons - just setting to 6 to see not sure if //needed
                gridPane.setHgap(6);
                gridPane.setvgap(6);

            //add grid pane and setting up stage
                Scene scene = new Scene(grid, 200, 250);
            //adding VBox for title
                VBox root = new VBox(10);
            // pane size - not set yet
                primaryStage.setScene(scene);
                primaryStage.show();
}


            //need a method here to update the moves visually on the board ( X or O )
                private void handleMove(int i, int j) {
                    if (buttons[i][j].getText().isEmpty()) {
                        String currentPlayer = game.getCurrentPlayer();
                        buttons[i][j].setText(currentPlayer);
                        game.makeMove(i, j);
                    }
                }
            //Display status messages for game
                    if (game.CheckWin()) {
                        game.setText("Player X wins!");
                    } else if (!game.CheckWin()) {
                        game.SetText("Player O wins!");
                    } else {
                        game.setText("It's a draw!");
                    }
 

//end start


// main to run the javaFx application 
    public static void main(String[] args) { 
        Application.launch(args); 

    }
}

//end GameApp
