import java.util.Arrays;

public class Computer {

    Boolean turn = false;

    private Game game;
    //constructor that assigns game instance
    public Computer(Game game){

        this.game = game;

    }

    public void move(){
        //gets the board
        String board[][];
        board = game.getBoard();
        turn = false;
        

        ///////////////////////////////////////////////////////////////////////
        //first turn decisions

        String temp[][] = {{"", "", ""}, {"", "X", ""}, {"", "", ""}};

        //picks the center square if available
        if (board[1][1].equals("")){ 
            game.placeMarker(1,1);
            turn = true;
        }
        //takes top left square if the center is taken
        if (Arrays.deepEquals(board, temp)){
            game.placeMarker(0,0);
            turn = true;
        }

        //////////////////////////////////////////////////////////////////////////
        //column checks
        if (turn == false){
            
            //cycles through each column to find 2/3 spots filled by computer moves
            for (int x = 0; x < 3; x++) {
                int u = 0;
                int c = 0;
                for (int y = 0; y < 3; y++) {
                   if (board[x][y].equals("X")){u++;}
                   if (board[x][y].equals("O")){c++;}
    
                }
                //if theres 2 in a row the computer will complete it
                if ((u == 0) && (c == 2)){
                    if (game.placeMarker(x, 0)){turn = true;}
                    if (game.placeMarker(x, 1)){turn = true;}
                    if (game.placeMarker(x, 2)){turn = true;} 
                }
            }
        }
        if (turn == false){

            //cycles through each column to find 2/3 spots filled by the user moves
            for (int x = 0; x < 3; x++) {
                int u = 0;
                int c = 0;
                for (int y = 0; y < 3; y++) {
                   if (board[x][y].equals("X")){u++;}
                   if (board[x][y].equals("O")){c++;}
                }
                //if theres 2 in a row the computer will block it
                if ((u == 2) && (c == 0)){
                    if (game.placeMarker(x, 0)){turn = true;}
                    if (game.placeMarker(x, 1)){turn = true;}
                    if (game.placeMarker(x, 2)){turn = true;}
                }
            }     
        }
        ///////////////////////////////////////////////////////////////
        //row checks
        if (turn == false){

            //cycles through each row to find 2/3 spots filled by computer moves
            for (int y = 0; y < 3; y++) {
                int u = 0;
                int c = 0;
                for (int x = 0; x < 3; x++) {
                   if (board[x][y].equals("X")){u++;}
                   if (board[x][y].equals("O")){c++;}
                //if theres 2 in a row the computer will complete it
                }
                if ((u == 0) && (c == 2)){
                    if (game.placeMarker(0, y)){turn = true;}
                    if (game.placeMarker(1, y)){turn = true;}
                    if (game.placeMarker(2, y)){turn = true;}         
                }
            }
        }
        if (turn == false){

             //cycles through each row to find 2/3 spots filled by user moves
            for (int y= 0; y < 3; y++) {
                int u = 0;
                int c = 0;
                for (int x = 0; x < 3; x++) {
                   if (board[x][y].equals("X")){u++;}
                   if (board[x][y].equals("O")){c++;}
                }
                //if theres 2 in a row the computer will block it
                if ((u == 2) && (c == 0)){
                    if (game.placeMarker(0, y)){turn = true;}
                    if (game.placeMarker(1, y)){turn = true;}
                    if (game.placeMarker(2, y)){turn = true;}
                }
            }
            
        }
        //////////////////////////////////////////////////////////////////////
        //diagonal checks 
        if (turn == false){
            int u = 0;
            int c = 0;
            //counts number of entries on the first diagonal
            if (board[0][2].equals("X"))   {u++;}
            if (board[1][1].equals("X"))   {u++;}
            if (board[2][0].equals("X"))   {u++;}
            if (board[0][2].equals("O"))   {c++;}
            if (board[1][1].equals("O"))   {c++;}
            if (board[2][0].equals("O"))   {c++;}

            //plays to either win or block a win
            if ((u == 2 && c == 0) || (c == 2 && u == 0))    {
                if (game.placeMarker(0, 2)){turn = true;}
                if (game.placeMarker(2, 0)){turn = true;}
            }

        }

        if (turn == false){
            int u = 0;
            int c = 0;
            //counts number of entries on the first diagonal
            if (board[0][0].equals("X"))   {u++;}
            if (board[1][1].equals("X"))   {u++;}
            if (board[2][2].equals("X"))   {u++;}
            if (board[0][0].equals("O"))   {c++;}
            if (board[1][1].equals("O"))   {c++;}
            if (board[2][2].equals("O"))   {c++;}

            //plays a move to either win or block a win
            if ((u == 2 && c == 0) || (c == 2 && u == 0))    {
                if (game.placeMarker(0, 0)){turn = true;}
                if (game.placeMarker(2, 2)){turn = true;}
            }
            //////////////////////////////////////////////////////////////////////////////
            //edge cases

            //saves an edge case with the two corner start
            if ((turn == false) && ((board[0][0].equals("X") && board[2][2].equals("X") || board[0][2].equals("X") && board[2][0].equals("X")))){
                if (game.placeMarker(2,1)){turn = true;}
            }

            //edge case fixes a two center edge start
            if ((turn == false) && ((board[2][1].equals("X") && board[1][2].equals("X")))){
                if (game.placeMarker(2,2)){turn = true;}
            }
            //same case different side
            if ((turn == false) && ((board[0][1].equals("X") && board[1][2].equals("X")))){
                if (game.placeMarker(0,2)){turn = true;}
            }
            //same case different side
            if ((turn == false) && ((board[1][0].equals("X") && board[2][1].equals("X")))){
                if (game.placeMarker(2,0)){turn = true;}
            }

            //fixes an edge case with a corner and opposite edge start
            if ((turn == false) && ((board[0][2].equals("X") && (board[1][0].equals("X") || board[2][1].equals("X"))))){
                if (game.placeMarker(2,0)){turn = true;}
            }
            //same case different corner
            if ((turn == false) && ((board[2][2].equals("X") && (board[1][0].equals("X") || board[0][1].equals("X"))))){
                if (game.placeMarker(0,0)){turn = true;}
            }
            //same case different corner
            if ((turn == false) && ((board[2][0].equals("X") && (board[0][1].equals("X") || board[1][2].equals("X"))))){
                if (game.placeMarker(0,2)){turn = true;}
            }
            //same case different corner
            if ((turn == false) && ((board[0][0].equals("X") && (board[2][1].equals("X") || board[1][2].equals("X"))))){
                if (game.placeMarker(2,2)){turn = true;}
            }

            //saves a start where you put two behind and already blocked lane
            if ((turn == false) && ((board[2][2].equals("X") && board[1][1].equals("X")))){
                if (game.placeMarker(0,2)){turn = true;}
            }
  

                       
            //plays a diagonal filler move since there are no game winning or blocking moves available
            if (turn == false){
                if ((u == 0 && c == 1))    {
                    if (game.placeMarker(0, 0)){turn = true;}
                    if (turn == false){
                        if (game.placeMarker(2, 2)){turn = true;}
                    }
                }

            }

        }      
        //////////////////////////////////////////////////////////////////////////////
        //plays a filler move in a row or column where only it has played 

        if (turn == false){

            //cycles through each row to find a row where only it has played once
            for (int y = 0; y < 3; y++) {
                int u = 0;
                int c = 0;
                for (int x = 0; x < 3; x++) {
                   if (board[x][y].equals("X")){u++;}
                   if (board[x][y].equals("O")){c++;}
                    
                }
                //plays move if found
                if ((u == 0) && (c == 1)){
                    if (game.placeMarker(0, y)){turn = true;}
                    if (turn == false){
                        if (game.placeMarker(1, y)){turn = true;}
                    }
                    if (turn == false){
                        if (game.placeMarker(2, y)){turn = true;}
                    }
                    
                }
            }
        }
        if (turn == false){
            
            ////cycles through each column to find a column where only it has played once
            for (int x = 0; x < 3; x++) {
                int u = 0;
                int c = 0;
                for (int y = 0; y < 3; y++) {
                   if (board[x][y].equals("X")){u++;}
                   if (board[x][y].equals("O")){c++;}
                }
                //plays the move if found
                if ((u == 0) && (c == 1)){
                    if (game.placeMarker(x, 0)){turn = true;}
                    if (turn == false){
                        if (game.placeMarker(x, 1)){turn = true;}
                    }
                    
                }
            }
        }
        if (turn == false){

            //when the game is unwinnable and there are no meaningful moves left
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (turn == false){
                        if (game.placeMarker(x, y)){turn = true;}
                     }
        
                }

            }
        }
    }
}
