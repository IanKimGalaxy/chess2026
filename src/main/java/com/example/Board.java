package com.example;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
    // Resource location constants for piece images
    private static final String path = "/src/main/java/com/example/Pictures/";
    private static final String RESOURCES_WBISHOP_PNG = path+"wbishop.png";
    private static final String RESOURCES_BBISHOP_PNG = path+"bbishop.png";
    private static final String RESOURCES_WKNIGHT_PNG = path+"wknight.png";
    private static final String RESOURCES_BKNIGHT_PNG = path+"bknight.png";
    private static final String RESOURCES_WROOK_PNG = path+"wrook.png";
    private static final String RESOURCES_BROOK_PNG = path+"brook.png";
    private static final String RESOURCES_WKING_PNG = path+"wking.png";
    private static final String RESOURCES_BKING_PNG = path+"bking.png";
    private static final String RESOURCES_BQUEEN_PNG = path+"bqueen.png";
    private static final String RESOURCES_WQUEEN_PNG = path+"wqueen.png";
    private static final String RESOURCES_WPAWN_PNG = path+"wpawn.png";
    private static final String RESOURCES_BPAWN_PNG = path+"bpawn.png";




   
    // Logical and graphical representations of board
    private final Square[][] board;
    private final GameWindow g;
 
    //contains true if it's white's turn.
    private boolean whiteTurn;


    //if the player is currently dragging a piece this variable contains it.
    Piece currPiece;
    private Square fromMoveSquare;
   
    //used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;
   


   
    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));


        this.addMouseListener(this);
        this.addMouseMotionListener(this);


        //TO BE IMPLEMENTED FIRST
     
      //for (.....)  
      //populate the board with squares here. Note that the board is composed of 64 squares alternating from
      //white to black.


        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                board[row][col] = new Square(this, (row + col) % 2 == 0, row, col);
                //Board b, boolean isWhite, int row, int col
                this.add(board[row][col]);
            }
        }


       
       
        initializePieces();
       


        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));


        whiteTurn = true;


    }
 
   
    //set up the board such that the black pieces are on one side and the white pieces are on the other.
    //since we only have one kind of piece for now you need only set the same number of pieces on either side.
    //it's up to you how you wish to arrange your pieces.
    void initializePieces() {
       
        // board[0][0].put(new Piece(true, path+ RESOURCES_WKING_PNG));


        // Using the same model of the regular knight for the super knight
        // White pieces
        board[7][1].put(new SuperKnight(true, RESOURCES_WKNIGHT_PNG));
        board[7][6].put(new SuperKnight(true, RESOURCES_WKNIGHT_PNG));
        board[7][4].put(new King(true, RESOURCES_WKING_PNG));
        board[7][3].put(new Queen(true, RESOURCES_WQUEEN_PNG));
        board[7][0].put(new Rook(true, RESOURCES_WROOK_PNG));
        board[7][7].put(new Rook(true, RESOURCES_WROOK_PNG));


        // Black pieces
        board[0][1].put(new SuperKnight(false, RESOURCES_BKNIGHT_PNG));
        board[0][6].put(new SuperKnight(false, RESOURCES_BKNIGHT_PNG));
        board[0][4].put(new King(false, RESOURCES_BKING_PNG));
        board[0][3].put(new Queen(false, RESOURCES_BQUEEN_PNG));
        board[0][0].put(new Rook(false, RESOURCES_BROOK_PNG));
        board[0][7].put(new Rook(false, RESOURCES_BROOK_PNG));
    }




   




    public Square[][] getSquareArray() {
        return this.board;
    }


    public boolean getTurn() {
        return whiteTurn;
    }


    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }


    public Piece getCurrPiece() {
        return this.currPiece;
    }


        @Override
    public void paintComponent(Graphics g) {
     Image backgroundImage = null;
     URL imageUrl = null;
     if (currPiece != null) {
      imageUrl = getClass().getResource("/src/main/java/com/example/"+currPiece.getImage());
     }


     if (imageUrl != null) {
            // This is the cleanest way to get an AWT Image object from a URL
            backgroundImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
        }
   


        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[x][y].paintComponent(g);
            }
        }


        if (fromMoveSquare != null) fromMoveSquare.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLUE));


        if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn) || (!currPiece.getColor()&& !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }
    }
       

    // precondition - the board is initialized and contains a king of either color. The boolean kingColor corresponds to the color of the king we wish to know the status of.
    // postcondition - returns true of the king is in check and false otherwise.
    public boolean isInCheck(boolean kingColor) {
        Square kingSquare = null;

        // 1. Find the King of the specified color
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece p = board[row][col].getOccupyingPiece();
                if (p instanceof King && p.getColor() == kingColor) {
                    kingSquare = board[row][col];
                    break;
                }
            }
        }

        // 2. Check if any enemy piece controls the king's square
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece p = board[row][col].getOccupyingPiece();
                if (p != null && p.getColor() != kingColor) {
                    // Get all squares this enemy piece attacks
                    ArrayList<Square> attackedSquares = p.getControlledSquares(board, board[row][col]);
                    if (attackedSquares.contains(kingSquare)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();


        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));


        if (sq.isOccupied() && sq.getOccupyingPiece().getColor() == whiteTurn) {
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;


            for (Square s : currPiece.getLegalMoves(this, fromMoveSquare)) {
                s.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
            }


            if (currPiece.getColor() != whiteTurn)
                return;
            sq.setDisplay(false);
        }
        repaint();
    }


    //TO BE IMPLEMENTED!
    //should move the piece to the desired location only if this is a legal move.
    //use the pieces "legal move" function to determine if this move is legal, then complete it by
    //moving the new piece to it's new board location.


    // pre-condition: The mouse is released.
    // post-condition: If the user moved the piece to a valid square, the selected piece moves to the selected square. The turn will also change once the piece arrives to that square.



@Override
public void mouseReleased(MouseEvent e) {
    Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

    // Making sure we dropped a piece on a square
    if (fromMoveSquare != null && currPiece != null && endSquare instanceof Square) {
        
        // 2Check if move is possible
        if (currPiece.getLegalMoves(this, fromMoveSquare).contains(endSquare)) {
            
            // Simulate and save that position
            Piece capturedPiece = endSquare.getOccupyingPiece();
            endSquare.put(currPiece);
            fromMoveSquare.removePiece();

            // Undoes the move if illegal, but if not changes turn.
            if (isInCheck(whiteTurn)) {
                fromMoveSquare.put(currPiece);
                endSquare.put(capturedPiece);
            } else {
                whiteTurn = !whiteTurn;
            }
        }
    }

    if (fromMoveSquare != null) fromMoveSquare.setDisplay(true);
    currPiece = null;
    fromMoveSquare = null; // Reset this so the next drag starts fresh

    for (Square[] row : board) {
        for (Square s : row) s.setBorder(null);
    }
    
    repaint();
}


    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 24;
        currY = e.getY() - 24;


        repaint();
    }


    @Override
    public void mouseMoved(MouseEvent e) {
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }


    @Override
    public void mouseEntered(MouseEvent e) {
    }


    @Override
    public void mouseExited(MouseEvent e) {
    }


}

