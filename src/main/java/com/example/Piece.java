package com.example;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
         
        try {
            if (this.img == null) {
                this.img = ImageIO.read(new File(System.getProperty("user.dir")+img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    
    // TO BE IMPLEMENTED!
    // return a list of every square that is "controlled" by this piece. A square is controlled
    // if the piece capture into it legally.

    // pre-conditions: board is a valid, fully initialized 8x8 2D array of Squares. start is a valid Square object located somewhere on that board.
    // post-conditions: Returns a list of all squares the Super Knight can physically reach in a 3x1 jump without going off the edges of the board. Does NOT check if those squares are blocked by friendly pieces.The board itself and the piece's location remain completely unchanged.
 

    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
        int col = start.getCol();
        int row = start.getRow();

        ArrayList<Square> controlledSquares = new ArrayList<Square>();

        // All 8 possible moves for a 3x1 Super Knight
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


     //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.



    // pre-conditions: 'b' (the Board) and 'start' (the Square) are valid and not null. The piece calling this method actually exists on the 'start' square.
    // post-conditions: Returns a filtered list of squares that the piece is legally allowed to move to. Guarantees that none of the returned squares contain a piece of the same color. The board state and piece positions remain completely unchanged.

    public ArrayList<Square> getLegalMoves(Board b, Square start){
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


}    
   

