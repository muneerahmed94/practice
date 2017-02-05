package other.chessengine;

import javafx.geometry.Pos;

import java.util.ArrayList;

/**
 * Created by Muneer on 31-12-2016.
 */
class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isValid() {
        if (x >= 0 && x < 8 && y >= 0 && y < 8)
            return true;
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        Position pos = (Position) obj;
        return (x == pos.getX() && y == pos.getY());
    }
}

class Move {
    private Position oldPosition;
    private Position newPosition;
    private Piece captured;

    public Move(Position oldPosition, Position newPosition, Piece captured) {
        this.oldPosition = oldPosition;
        this.newPosition = newPosition;
        this.captured = captured;
    }

    public Position getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(Position oldPosition) {
        this.oldPosition = oldPosition;
    }

    public Position getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(Position newPosition) {
        this.newPosition = newPosition;
    }

    public Piece getCaptured() {
        return captured;
    }

    public void setCaptured(Piece captured) {
        this.captured = captured;
    }
}


abstract class Piece {
    private boolean isWhite;
    private char type;

    public Piece(boolean isWhite, char type) {
        this.isWhite = isWhite;
        this.type = type;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public char getType() {
        return type;
    }

    public abstract ArrayList<Move> getMoves(ChessBoard chessBoard, Position position);
}

class King extends Piece {
    public King(boolean isWhite, char type) {
        super(isWhite, type);
    }

    @Override
    public ArrayList<Move> getMoves(ChessBoard chessBoard, Position currPos) {
        ArrayList<Move> movesList = new ArrayList<>();

        Position newPos;
        Piece capturedPiece, currPiece;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                newPos = new Position(currPos.getX() + i, currPos.getY() + j);
                if (newPos.isValid() && !newPos.equals(currPos)) {

                    if (chessBoard.getPiece(newPos) == null || chessBoard.getPiece(newPos).isWhite() != chessBoard.getPiece(currPos).isWhite()) {

                        capturedPiece = chessBoard.getPiece(newPos);
                        currPiece = chessBoard.getPiece(currPos);

                        //remove the currPiece
                        chessBoard.setPiece(null, currPos);
                        //put the currPiece at newPos
                        chessBoard.setPiece(currPiece, newPos);
                        //update the kingPos
                        if (currPiece.isWhite()) chessBoard.setWhiteKingPos(newPos);
                        else chessBoard.setBlackKingPos(newPos);

                        //check if this is the safe position for king
                        if (chessBoard.isKingSafe(currPiece.isWhite())) {
                            Move move = new Move(currPos, newPos, capturedPiece);
                            movesList.add(move);
                        }

                        //put all the pieces where they were
                        chessBoard.setPiece(currPiece, currPos);
                        chessBoard.setPiece(capturedPiece, newPos);
                        if (currPiece.isWhite()) chessBoard.setWhiteKingPos(currPos);
                        else chessBoard.setBlackKingPos(currPos);
                    }
                }
            }
        }

        //need to add castling
        return movesList;
    }
}

class Queen extends Piece {

    public Queen(boolean isWhite, char type) {
        super(isWhite, type);
    }

    @Override
    public ArrayList<Move> getMoves(ChessBoard chessBoard, Position currPos) {
        ArrayList<Move> movesList = new ArrayList<>();

        Position newPos;
        Piece capturedPiece, currPiece;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int temp = 1;
                boolean opponentsPieceFound = false;
                newPos = new Position(currPos.getX() + temp * i, currPos.getY() + temp * j);
                while (newPos.isValid() && !newPos.equals(currPos) && (chessBoard.getPiece(newPos) == null || chessBoard.getPiece(newPos).isWhite() != chessBoard.getPiece(currPos).isWhite()) && !opponentsPieceFound) {
                    currPiece = chessBoard.getPiece(currPos);
                    capturedPiece = chessBoard.getPiece(newPos);

                    chessBoard.setPiece(null, currPos);
                    chessBoard.setPiece(currPiece, newPos);

                    if (chessBoard.isKingSafe(currPiece.isWhite())) {
                        Move move = new Move(currPos, newPos, capturedPiece);
                        movesList.add(move);
                    }

                    chessBoard.setPiece(currPiece, currPos);
                    chessBoard.setPiece(capturedPiece, newPos);

                    if(chessBoard.getPiece(newPos) != null)
                        opponentsPieceFound = true;

                    temp++;
                    newPos = new Position(currPos.getX() + temp * i, currPos.getY() + temp * j);
                }
            }
        }
        return movesList;
    }
}

class Bishop extends Piece {
    public Bishop(boolean isWhite, char type) {
        super(isWhite, type);
    }

    @Override
    public ArrayList<Move> getMoves(ChessBoard chessBoard, Position currPos) {
        ArrayList<Move> movesList = new ArrayList<>();

        Position newPos;
        Piece capturedPiece, currPiece;

        for (int i = -1; i <= 1; i = i + 2) {
            for (int j = -1; j <= 1; j = j + 2) {
                int temp = 1;
                boolean opponentsPieceFound = false;
                newPos = new Position(currPos.getX() + temp * i, currPos.getY() + temp * j);
                while (newPos.isValid() && !newPos.equals(currPos) && (chessBoard.getPiece(newPos) == null || chessBoard.getPiece(newPos).isWhite() != chessBoard.getPiece(currPos).isWhite()) && !opponentsPieceFound) {
                    currPiece = chessBoard.getPiece(currPos);
                    capturedPiece = chessBoard.getPiece(newPos);

                    chessBoard.setPiece(null, currPos);
                    chessBoard.setPiece(currPiece, newPos);

                    if (chessBoard.isKingSafe(currPiece.isWhite())) {
                        Move move = new Move(currPos, newPos, capturedPiece);
                        movesList.add(move);
                    }

                    chessBoard.setPiece(currPiece, currPos);
                    chessBoard.setPiece(capturedPiece, newPos);

                    if(chessBoard.getPiece(newPos) != null)
                        opponentsPieceFound = true;

                    temp++;
                    newPos = new Position(currPos.getX() + temp * i, currPos.getY() + temp * j);
                }
            }
        }
        return movesList;
    }
}

class Knight extends Piece {
    public Knight(boolean isWhite, char type) {
        super(isWhite, type);
    }

    @Override
    public ArrayList<Move> getMoves(ChessBoard chessBoard, Position currPos) {
        ArrayList<Move> movesList = new ArrayList<>();

        Position newPos;
        Piece capturedPiece, currPiece;

        int[][] directions = new int[][] {{-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}};
        for(int[] direction: directions) {
            int i = direction[0];
            int j = direction[1];

            newPos = new Position(currPos.getX() + i, currPos.getY() + j);
            if (newPos.isValid() && (chessBoard.getPiece(newPos) == null || chessBoard.getPiece(newPos).isWhite() != chessBoard.getPiece(currPos).isWhite())) {
                currPiece = chessBoard.getPiece(currPos);
                capturedPiece = chessBoard.getPiece(newPos);

                chessBoard.setPiece(null, currPos);
                chessBoard.setPiece(currPiece, newPos);

                if (chessBoard.isKingSafe(currPiece.isWhite())) {
                    Move move = new Move(currPos, newPos, capturedPiece);
                    movesList.add(move);
                }

                chessBoard.setPiece(currPiece, currPos);
                chessBoard.setPiece(capturedPiece, newPos);
            }
        }

        return movesList;
    }
}

class Rook extends Piece {
    public Rook(boolean isWhite, char type) {
        super(isWhite, type);
    }

    @Override
    public ArrayList<Move> getMoves(ChessBoard chessBoard, Position currPos) {
        ArrayList<Move> movesList = new ArrayList<>();

        Position newPos;
        Piece capturedPiece, currPiece;

        int[][] directions = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for(int[] direction: directions) {
            int i = direction[0];
            int j = direction[1];

            int temp = 1;
            boolean opponentsPieceFound = false;
            newPos = new Position(currPos.getX() + temp * i, currPos.getY() + temp * j);
            while (newPos.isValid() && !newPos.equals(currPos) && (chessBoard.getPiece(newPos) == null || chessBoard.getPiece(newPos).isWhite() != chessBoard.getPiece(currPos).isWhite()) && !opponentsPieceFound) {
                currPiece = chessBoard.getPiece(currPos);
                capturedPiece = chessBoard.getPiece(newPos);

                chessBoard.setPiece(null, currPos);
                chessBoard.setPiece(currPiece, newPos);

                if (chessBoard.isKingSafe(currPiece.isWhite())) {
                    Move move = new Move(currPos, newPos, capturedPiece);
                    movesList.add(move);
                }

                chessBoard.setPiece(currPiece, currPos);
                chessBoard.setPiece(capturedPiece, newPos);

                if(chessBoard.getPiece(newPos) != null)
                    opponentsPieceFound = true;

                temp++;
                newPos = new Position(currPos.getX() + temp * i, currPos.getY() + temp * j);
            }
        }

        return movesList;
    }
}

class Pawn extends Piece {
    public Pawn(boolean isWhite, char type) {
        super(isWhite, type);
    }

    @Override
    public ArrayList<Move> getMoves(ChessBoard chessBoard, Position position) {
        return null;
    }
}

class ChessBoard {

    final static char KING = 'K';
    final static char QUEEN = 'Q';
    final static char BISHOP = 'B';
    final static char KNIGHT = 'N';
    final static char ROOK = 'R';
    final static char PAWN = 'P';

    private Piece[][] pieces;
    private int size;
    private Position whiteKingPos, blackKingPos;

    public ChessBoard(int size) {
        this.size = size;
        pieces = new Piece[size][size];
        whiteKingPos = new Position(size - 1, 4);
        blackKingPos = new Position(0, 4);
    }

    public void setPiece(Piece piece, Position position) {
        pieces[position.getX()][position.getY()] = piece;
    }

    public void setPiece(Piece piece, int x, int y) {
        pieces[x][y] = piece;
    }

    public Piece getPiece(Position position) {
        return pieces[position.getX()][position.getY()];
    }

    public Piece getPiece(int x, int y) {
        return pieces[x][y];
    }

    public ArrayList<Move> getAllPossibleMoves(boolean isWhitesTurn) {
        ArrayList<Move> allPossibleMovesList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (pieces[i][j].isWhite() == isWhitesTurn) {
                    allPossibleMovesList.addAll(pieces[i][j].getMoves(this, new Position(i, j)));
                }
            }
        }

        return allPossibleMovesList;
    }

    public Position getWhiteKingPos() {
        return whiteKingPos;
    }

    public void setWhiteKingPos(Position whiteKingPos) {
        this.whiteKingPos = whiteKingPos;
    }

    public Position getBlackKingPos() {
        return blackKingPos;
    }

    public void setBlackKingPos(Position blackKingPos) {
        this.blackKingPos = blackKingPos;
    }

    public boolean isKingSafe(boolean isWhite) {

        ArrayList<Move> movesList = new ArrayList<>();

        Position newPos;
        Piece pieceAtNewPos = null, currPiece;

        Position currPos = (isWhite? whiteKingPos : blackKingPos);

        //Bishop or Queen
        for (int i = -1; i <= 1; i = i + 2) {
            for (int j = -1; j <= 1; j = j + 2) {
                int temp = 1;
                newPos = new Position(currPos.getX() + temp * i, currPos.getY() + temp * j);
                while (newPos.isValid() && this.getPiece(newPos) == null) {
                    temp++;
                    newPos = new Position(currPos.getX() + temp * i, currPos.getY() + temp * j);
                }

                if(newPos.isValid()) {
                    pieceAtNewPos = this.getPiece(newPos);
                    if(pieceAtNewPos != null && pieceAtNewPos.isWhite() != isWhite && pieceAtNewPos.getType() == BISHOP || pieceAtNewPos.getType() == QUEEN) {
                        return false;
                    }
                }
            }
        }

        //Rook or Queen
        int[][] directions = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for(int[] direction: directions) {
            int i = direction[0];
            int j = direction[1];

            int temp = 1;
            newPos = new Position(currPos.getX() + temp * i, currPos.getY() + temp * j);
            while (newPos.isValid() && this.getPiece(newPos) == null) {
                temp++;
                newPos = new Position(currPos.getX() + temp * i, currPos.getY() + temp * j);
            }

            if(newPos.isValid()) {
                pieceAtNewPos = this.getPiece(newPos);
                if(pieceAtNewPos != null && pieceAtNewPos.isWhite() != isWhite && pieceAtNewPos.getType() == BISHOP || pieceAtNewPos.getType() == QUEEN) {
                    return false;
                }
            }
        }

        return true;
    }
}

public class Solution {

    final static char KING = 'K';
    final static char QUEEN = 'Q';
    final static char BISHOP = 'B';
    final static char KNIGHT = 'N';
    final static char ROOK = 'R';
    final static char PAWN = 'P';

    final static int BOARD_SIZE = 8;

    Position whiteKingPosition, blackKingPosition;

    public static void main(String[] args) {

        ChessBoard chessBoard = new ChessBoard(BOARD_SIZE);
        initializeChessBoardTest(chessBoard);
        printChessBoard(chessBoard);

        //System.out.println(chessBoard.getPiece(7,7).getMoves(chessBoard, new Position(7,7)).size());
        System.out.println(chessBoard.isKingSafe(true));;
    }

    private static void printChessBoard(ChessBoard chessBoard) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (chessBoard.getPiece(i, j) != null) {
                    System.out.print(chessBoard.getPiece(i, j).isWhite() ? chessBoard.getPiece(i, j).getType() + " " : Character.toLowerCase(chessBoard.getPiece(i, j).getType()) + " ");
                } else {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
    }

    private static void initializeChessBoard(ChessBoard chessBoard) {
        char[][] board = new char[][]{
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = null;
                if (board[i][j] != ' ') {
                    boolean isWhite = Character.isUpperCase(board[i][j]);
                    switch (Character.toUpperCase(board[i][j])) {
                        case ROOK:
                            piece = new Rook(isWhite, ROOK);
                            break;
                        case KNIGHT:
                            piece = new Knight(isWhite, KNIGHT);
                            break;
                        case BISHOP:
                            piece = new Bishop(isWhite, BISHOP);
                            break;
                        case QUEEN:
                            piece = new Queen(isWhite, QUEEN);
                            break;
                        case KING:
                            piece = new King(isWhite, KING);
                            break;
                        case PAWN:
                            piece = new Pawn(isWhite, PAWN);
                            break;
                    }
                }
                chessBoard.setPiece(piece, i, j);
            }
        }
    }

    private static void initializeChessBoardTest(ChessBoard chessBoard) {
        char[][] board = new char[][]{
                {'b', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'K'}
        };

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = null;
                if (board[i][j] != ' ') {
                    boolean isWhite = Character.isUpperCase(board[i][j]);
                    switch (Character.toUpperCase(board[i][j])) {
                        case ROOK:
                            piece = new Rook(isWhite, ROOK);
                            break;
                        case KNIGHT:
                            piece = new Knight(isWhite, KNIGHT);
                            break;
                        case BISHOP:
                            piece = new Bishop(isWhite, BISHOP);
                            break;
                        case QUEEN:
                            piece = new Queen(isWhite, QUEEN);
                            break;
                        case KING:
                            piece = new King(isWhite, KING);
                            break;
                        case PAWN:
                            piece = new Pawn(isWhite, PAWN);
                            break;
                    }
                }
                chessBoard.setPiece(piece, i, j);
            }
        }
    }
}
