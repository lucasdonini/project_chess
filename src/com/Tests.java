package com;

import com.pieces.Piece;

public class Tests {
    public static void main(String[] args) {
        Piece pawn = Piece.blackPawn("a7");
        Piece king = Piece.blackKing();

        System.out.println(pawn.getPossibleMovements());
        System.out.println(king.getPossibleMovements());
    }
}
