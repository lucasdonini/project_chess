package main.game;

import main.board.Board;
import main.pieces.King;
import main.pieces.Piece;
import utils.SquareUtils;

import java.util.List;

public class CheckMateController {
    private final Game game;
    private final Player player;
    private final Board board;
    private King king;
    private String kingLocation;

    public CheckMateController(Game game) {
        this.game = game;
        player = game.getPlayer();
        board = game.getBoard();


        for (Piece p : game.getPiecesPerPlayer().get(player)) {
            if (p instanceof King k) {
                king = k;
                kingLocation = SquareUtils.getName(king.getCoordinate());
                break;

            } else {
                king = null;
                kingLocation = null;
            }
        }
    }

    public boolean isCheck() {
        List<Piece> enemyPieces = game.getPiecesPerPlayer().get(player.opposite());
        String kingLocation = SquareUtils.getName(king.getCoordinate());


        for (Piece p : enemyPieces) {
            if (p.getPossibleMovements(board).contains(kingLocation)) {
                return true;
            }
        }

        return false;
    }

    public boolean isCheckMate() {
        int possibleMoveCount = 0;
        List<String> possibilities = king.getPossibleMovements(board);

        for (String move : possibilities) {
            Game innerGame = game.copy();
            innerGame.move(kingLocation, move, possibilities);

            if (!new CheckMateController(innerGame).isCheck()) {
                possibleMoveCount++;
            }
        }

        return isCheck() && possibleMoveCount <= 0;
    }
}
