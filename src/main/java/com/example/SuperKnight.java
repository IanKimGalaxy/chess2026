package com.example;
import java.util.ArrayList;

// name: Ian Kim
// piece: SuperKnight
/*The SuperKnight moves similarly like the regular knight
but jumps over 3 squares forward rather than two.
The knight can jump over pieces of either color, and
can only capture pieces of the opposing color. */
public class SuperKnight extends Piece {
   
    public SuperKnight (boolean isWhite, String img_file) {
        super(isWhite, img_file);
    }
   
 


    // pre-conditions: board is a valid, fully initialized 8x8 2D array of Squares. start is a valid Square object located somewhere on that board.
    // post-conditions: Returns a list of all squares the Super Knight can physically reach in a 3x1 jump without going off the edges of the board. Does NOT check if those squares are blocked by friendly pieces.The board itself and the piece's location remain completely unchanged.
 
	
    @Override public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
        int col = start.getCol();
        int row = start.getRow();


        ArrayList<Square> controlledSquares = new ArrayList<Square>();


        // All 8 possible moves for a 3x1 SuperKnight
        int[] rowOffsets = {3,3,-3,-3,1,1,-1,-1};
        int[] colOffsets = {1,-1,1,-1,3,-3,3,-3};


        for (int i = 0; i < 8; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];


            // Check if the new coordinates are safely within the 8x8 board boundaries
            if (newRow >= 0 && newRow <= 7 && newCol >= 0 && newCol <= 7) {
                // Access the board using [row][col] to match your Board.java initialization
                controlledSquares.add(board[newRow][newCol]);
            }
        }
        return controlledSquares;
    }


    // pre-conditions: 'b' (the Board) and 'start' (the Square) are valid and not null. The piece calling this method actually exists on the 'start' square.
    // post-conditions: Returns a filtered list of squares that the piece is legally allowed to move to. Guarantees that none of the returned squares contain a piece of the same color. The board state and piece positions remain completely unchanged.


    @Override public ArrayList<Square> getLegalMoves(Board b, Square start){
        // Steals list of squares that are on the board that the knight can move to from the previous method
        ArrayList<Square> controlled = getControlledSquares(b.getSquareArray(), start);


        // Return list of valid moves the knight can make
        ArrayList<Square> valid = new ArrayList<Square>();


        // Iterates through array of the squares
        for (Square s : controlled) {
            // Makes sure that the square does not contain a piece of the same color as the knight
            if (!s.isOccupied() || s.getOccupyingPiece().getColor() != this.color) {
                valid.add(s); // Adds the square to the return list if valid
            }
        }


        return valid;
   
    }




    
   
@Override public String toString() {
        String placeholder = "This is a ";
        if (this.getColor()) placeholder += "white SuperKnight";
        else placeholder += "black SuperKnight";

        return placeholder;
    }
}




