package com;

import com.board.Board;

public class Tests {
    public static void main(String[] args) {
        Board board = Board.setBoard();
        System.out.println(board.getPiece("d1").getPossibleMovements());
    }
}
