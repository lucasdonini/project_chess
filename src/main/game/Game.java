package main.game;

import exception.EmptySelectionException;
import exception.ImpossibleToMoveException;
import main.board.Board;
import main.board.Square;
import main.pieces.Pawn;
import main.pieces.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    protected final Board board;
    protected boolean isGameOver;
    protected boolean isInCheck;
    protected Player player;
    protected Player winner;
    protected int turnCount;

    protected Map<Player, List<Piece>> piecesPerPlayer;

    // Constructors
    public Game(Board board) {
        piecesPerPlayer = new HashMap<>();
        this.board = board;
        turnCount = 0;
    }

    public Game() {
        this(Board.setBoard());
    }

    protected Game(Board board, boolean isGameOver, boolean isInCheck, Player player, Player winner, int turnCount, Map<Player, List<Piece>> piecesPerPlayer) {
        this.board = board;
        this.isGameOver = isGameOver;
        this.isInCheck = isInCheck;
        this.player = player;
        this.winner = winner;
        this.turnCount = turnCount;
        this.piecesPerPlayer = piecesPerPlayer;
    }

    // Factory methods
    public Game copy() {
        Board boardCp = board.copy();

        return new Game(boardCp, isGameOver, isInCheck, player, winner, turnCount, boardCp.getPiecesPerPlayer());
    }

    // Getters
    public boolean isOver() {
        return isGameOver;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getWinner() {
        return winner;
    }

    public Board getBoard() {
        return board;
    }

    public Map<Player, List<Piece>> getPiecesPerPlayer() {
        return piecesPerPlayer;
    }

    // Other methods
    public void begin() {
        if (turnCount != 0) {
            throw new IllegalStateException("The game has already begun");
        }

        player = Player.WHITE;
        turnCount = 1;
        isGameOver = false;

        piecesPerPlayer = board.getPiecesPerPlayer();
    }

    public void nextTurn() {
        player = player.opposite();
        turnCount++;

        CheckMateController cmc = new CheckMateController(this);
        isInCheck = cmc.isCheck();
        isGameOver = cmc.isCheckMate();

        if (isGameOver) {
            winner = player.opposite();
        }
    }

    public List<String> possibleMoves(String originSquareName) {
        Square origin = board.getSquare(originSquareName);

        if (origin.isFree()) {
            throw new EmptySelectionException("Empty square selected");
        }

        if (!origin.getPiece().belongsTo(player)) {
            throw new IllegalStateException("The selected piece doesn't belong to the current player");
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

        destination.setPiece(origin.getPiece());
        destination.getPiece().moveTo(destinationSquareName);
        origin.setPiece(null);
    }

    public boolean isMovementIllegal(String origin, String destination, List<String> possibleMoves) {
        Game game = this.copy();

        try {
            game.move(origin, destination, possibleMoves);

            CheckMateController cmc = new CheckMateController(game);
            return cmc.isCheck() || cmc.isCheckMate();

        } catch (ImpossibleToMoveException e) {
            return true;
        }
    }

    @Override
    public String toString() {
        return String.format("%s\n\nCurrent Player: %s\n", board, player);
    }
}
