package main;

import exception.EmptySelectionException;
import exception.ImpossibleToMoveException;
import main.game.Game;
import main.pieces.PieceType;
import utils.SquareUtils;

import java.util.List;

import static utils.InputUtils.input;
import static utils.TerminalUtils.*;

public class Main {
    private static final List<String> squares = SquareUtils.getAllSquares();

    public static void main(String[] args) {
        // Data Objects
        Game game = new Game();

        // Game loop
        game.begin();
        do {
            try {
                // Variables
                String originSquareName, destinationSquareName;

                System.out.println('\n' + game.toString());

                originSquareName = input("Entry the location of the piece you want to move: ", squares::contains);

                List<String> possibleMoves = game.possibleMoves(originSquareName);
                String possibleMovesVisualization = possibleMoves.toString().replaceAll("[\\[\\]]", "");

                System.out.printf("Possible destinations: %s\n", possibleMovesVisualization);
                destinationSquareName = input(
                        "Entry the destination square name: ",
                        "Cannot move to specified square.",
                        possibleMoves::contains
                );

                if (game.isMovementIllegal(originSquareName, destinationSquareName, possibleMoves)) {
                    System.out.println(inRed("Illegal movement."));
                    System.out.println("Action cancelled. Restarting turn.");
                    continue;
                }

                game.move(originSquareName, destinationSquareName, possibleMoves);

                if (game.canPromote(destinationSquareName)) {
                    List<String> options = List.of("Q", "N", "B", "R");
                    System.out.println("\nPromotion options: Q = Queen; N = Knight; B = Bishop; R = Rook; P = Pawn");
                    String promotionCode = input("Insert the desired piece for promotion: ", options::contains);

                    PieceType type = switch (promotionCode) {
                        case "Q" -> PieceType.QUEEN;
                        case "N" -> PieceType.KNIGHT;
                        case "B" -> PieceType.BISHOP;
                        case "R" -> PieceType.ROOK;
                        default -> null;
                    };

                    if (type == null) throw new RuntimeException("Something went wrong with promotion");

                    game.promote(destinationSquareName, type);
                }

                game.nextTurn();

            } catch (ImpossibleToMoveException e) {
                System.out.println(inRed("This piece is stuck. Try to move another one."));

            } catch (IllegalStateException e) {
                System.out.println(inRed(e.getMessage()));

            } catch (EmptySelectionException e) {
                System.out.println(inRed("You selected an empty square. Select another one."));

            }
        } while (!game.isOver());

        System.out.println('\n' + game.toString());
        System.out.println(inRed("Check Mate"));
        System.out.println(inRed("Game over"));
        System.out.println(inGreen("Winner: %s", game.getWinner()));
    }
}
