package main.game;

import exception.EmptySelectionException;
import exception.ImpossibleToMoveException;
import main.board.Board;
import main.board.Square;
import main.pieces.King;
import main.pieces.Pawn;

import java.awt.*;
import java.util.List;

public class Game {
    private final Board board;
    private Player player;
    private int turnCount;
    private boolean isGameOver;
    private Player winner;

    // Constructors
    public Game() {
        board = Board.setBoard();
        turnCount = 0;
    }

    // Getters
    public boolean isOver() {
        return isGameOver;
    }

    public Player getWinner() {
        return winner;
    }

    // Other methods
    public void begin() {
        if (turnCount != 0) {
            throw new IllegalStateException("The game has already begun");
        }

        player = Player.WHITE;
        turnCount = 1;
        isGameOver = false;
    }

    public void nextTurn() {
        player = player.opposite();
    }

    public List<String> possibleMoves(String originSquareName) {
        Square origin = board.getSquare(originSquareName);

        if (!origin.getPiece().belongsTo(player)) {
            throw new IllegalStateException("The selected piece doesn't belong to the current player");
        }

        if (origin.isFree()) {
            throw new EmptySelectionException("Empty square selected");
        }

        List<String> squares = origin.getPiece().getPossibleMovements(board);

        if (origin.getPiece() instanceof Pawn pawn) {
            squares.addAll(pawn.getPossibleCaptureMovements(board));
        }

        if (squares.isEmpty()) {
            throw new ImpossibleToMoveException("Cannot move anywhere");
        }

        return squares;
    }

    public void move(String originSquareName, String destinationSquareName, List<String> possibleMoves) {
        Square origin = board.getSquare(originSquareName);
        Square destination = board.getSquare(destinationSquareName);

        if (origin.getPiece() == null) {
            throw new EmptySelectionException("You selected an empty square");
        }

        if (!possibleMoves.contains(destinationSquareName)) {
            throw new IllegalArgumentException("Cannot move to specified square");
        }

        if (destination.getPiece() instanceof King) {
            isGameOver = true;
            winner = player;
        }

        destination.setPiece(origin.getPiece());
        destination.getPiece().moveTo(destinationSquareName);
        origin.setPiece(null);
    }

    @Override
    public String toString() {
        return String.format("%s\n\nCurrent Player: %s\n", board, player);
    }
}
