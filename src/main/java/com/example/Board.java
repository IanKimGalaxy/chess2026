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
<<<<<<< HEAD
=======
import java.io.File;
import java.util.ArrayList;
>>>>>>> b24b0875fc0124a4e093bb6aa4b06bfa883fe0a2
import java.net.URL;

<<<<<<< HEAD
import javax.swing.BorderFactory;
import javax.swing.JPanel;
=======
import javax.imageio.ImageIO;
import javax.swing.*;
>>>>>>> b24b0875fc0124a4e093bb6aa4b06bfa883fe0a2

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
    // Resource location constants for piece images
    public static final String PICTURE_PATH = "/src/main/java/com/example/Pictures/";
    private static final String RESOURCES_WBISHOP_PNG = PICTURE_PATH + "wbishop.png";
    private static final String RESOURCES_BBISHOP_PNG = PICTURE_PATH + "bbishop.png";
    private static final String RESOURCES_WKNIGHT_PNG = PICTURE_PATH + "wknight.png";
    private static final String RESOURCES_BKNIGHT_PNG = PICTURE_PATH + "bknight.png";
    private static final String RESOURCES_WROOK_PNG = PICTURE_PATH + "wrook.png";
    private static final String RESOURCES_BROOK_PNG = PICTURE_PATH + "brook.png";
    private static final String RESOURCES_WKING_PNG = PICTURE_PATH + "wking.png";
    private static final String RESOURCES_BKING_PNG = PICTURE_PATH + "bking.png";
    private static final String RESOURCES_BQUEEN_PNG = PICTURE_PATH + "bqueen.png";
    private static final String RESOURCES_WQUEEN_PNG = PICTURE_PATH + "wqueen.png";
    private static final String RESOURCES_WPAWN_PNG = PICTURE_PATH + "wpawn.png";
    private static final String RESOURCES_BPAWN_PNG = PICTURE_PATH + "bpawn.png";

<<<<<<< HEAD

	
	// Logical and graphical representations of board
	private final Square[][] board;
=======
    //constant used to keep track of where the piece should be drawn when the user is dragging it
    private static final int PIECE_OFFSET = 24;

    // Logical and graphical representations of board
    private final Square[][] board;
>>>>>>> b24b0875fc0124a4e093bb6aa4b06bfa883fe0a2
    private final GameWindow g;

    // contains true if it's white's turn.
    private boolean whiteTurn;

    // if the player is currently dragging a piece this variable contains it.
    Piece currPiece;
    //the square your piece came from when the user tries to move it.
    private Square fromMoveSquare;
    //the square your piece tries to go to when the user tries to move it.
    private Square endSquare;

    // used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;

    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

<<<<<<< HEAD
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

=======
        // TO BE IMPLEMENTED FIRST

        // for (.....)
        // populate the board with squares here. Note that the board is composed of 64
        // squares alternating from white to black.
        //IMPORTANT**** : please note for each square you create you HAVE to do "this.add(<your new square here>)" 
        //the reason this is required has to do with how visual components are rendered, so if you neglect to do this
        //you will not see any of your squares show up on the board!
        // Where's the "add" method? Stay tuned for next unit where we discover where it is and why we can do this action.
>>>>>>> b24b0875fc0124a4e093bb6aa4b06bfa883fe0a2
        
        
        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }
<<<<<<< HEAD
 
    
	//set up the board such that the black pieces are on one side and the white pieces are on the other.
	//since we only have one kind of piece for now you need only set the same number of pieces on either side.
	//it's up to you how you wish to arrange your pieces.
    void initializePieces() {
    	
    	// board[0][0].put(new Piece(true, path+ RESOURCES_WKING_PNG));
=======

    // set up the board such that the black pieces are on one side and the white
    // pieces are on the other.
    // since we only have one kind of piece for now you need only set the same
    // number of pieces on either side.
    // it's up to you how you wish to arrange your pieces.
    void initializePieces() {

        board[0][0].put(new Piece(true, RESOURCES_WKING_PNG));
>>>>>>> b24b0875fc0124a4e093bb6aa4b06bfa883fe0a2

        // Using the same model of the regular knight for the super knight
        // White pieces
    	board[7][1].put(new Piece(true, RESOURCES_WKNIGHT_PNG));
        board[7][6].put(new Piece(true, RESOURCES_WKNIGHT_PNG));


        // Black pieces
        board[0][1].put(new Piece(false, RESOURCES_BKNIGHT_PNG));
        board[0][6].put(new Piece(false, RESOURCES_BKNIGHT_PNG));
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
<<<<<<< HEAD
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
=======
        super.paintComponent(g);
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col].draw(g);
            }
        }

        if (currPiece != null) {
            if (currPiece.getColor() == whiteTurn) {
>>>>>>> b24b0875fc0124a4e093bb6aa4b06bfa883fe0a2
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied() && sq.getOccupyingPiece().getColor() == whiteTurn) {
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;
<<<<<<< HEAD

            for (Square s : currPiece.getLegalMoves(this, fromMoveSquare)) {
                s.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.RED));
            }

            if (currPiece.getColor() != whiteTurn)
                return;
=======
>>>>>>> b24b0875fc0124a4e093bb6aa4b06bfa883fe0a2
            sq.setDisplay(false);
        }
        repaint();
    }

<<<<<<< HEAD
    //TO BE IMPLEMENTED!
    //should move the piece to the desired location only if this is a legal move.
    //use the pieces "legal move" function to determine if this move is legal, then complete it by
    //moving the new piece to it's new board location. 

    // pre-condition: The mouse is released.
    // post-condition: If the user moved the piece to a valid square, the selected piece moves to the selected square. The turn will also change once the piece arrives to that square.

    @Override
    public void mouseReleased(MouseEvent e) {
        Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        
         if (fromMoveSquare != null && currPiece != null) { 
            if (currPiece.getLegalMoves(this, fromMoveSquare).contains(endSquare)) { 
                endSquare.put(currPiece);
                fromMoveSquare.removePiece();
                whiteTurn = !whiteTurn;
            }
        }

        fromMoveSquare.setDisplay(true);
=======
    // TO BE IMPLEMENTED!
    // should move the piece to the desired location only if this is a legal move.
    // use the pieces "legal move" function to determine if this move is legal, then
    // complete it by moving the new piece to it's new board location.
    @Override
    public void mouseReleased(MouseEvent e) {
        endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        // using currPiece
        if(fromMoveSquare!= null){
            fromMoveSquare.setDisplay(true);
        }
>>>>>>> b24b0875fc0124a4e093bb6aa4b06bfa883fe0a2
        currPiece = null;

        for (Square[] row : board) {
            for (Square s : row) s.setBorder(null);
        }

        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - PIECE_OFFSET;
        currY = e.getY() - PIECE_OFFSET;

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