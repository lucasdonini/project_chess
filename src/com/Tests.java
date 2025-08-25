package com;

import com.board.Board;
import com.pieces.Piece;

public class Tests {
    public static void main(String[] args) {
        Board board = Board.setBoard();
        System.out.println(board.getPiece("e2").getPossibleMovements());
    }
}
