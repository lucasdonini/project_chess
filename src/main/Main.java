package main;

import main.game.Game;
import utils.SquareUtils;

import java.util.List;

import static utils.InputUtils.input;

public class Main {
    private static final List<String> squares = SquareUtils.getAllSquares();

    public static void main(String[] args) {
        // Data Objects
        Game game = new Game();

        // Game loop
        game.begin();
        do {
            // Variables
            String originSquareName, destinationSquareName;

            System.out.println(game);

            originSquareName = input("Entry the location of the piece you want to move: ", squares::contains);

            List<String> possibleMoves = game.possibleMoves(originSquareName);
            String possibleMovesVisualization = possibleMoves.toString().replaceAll("[\\[\\]]", "");

            System.out.printf("Possible destinations: %s\n", possibleMovesVisualization);
            destinationSquareName = input(
                    "Entry the destination square name: ",
                    "Cannot move to specified square.",
                    possibleMoves::contains
            );

            game.move(originSquareName, destinationSquareName, possibleMoves);

            game.nextTurn();
        } while (!game.isOver());

        System.err.println("Game over");
    }
}
