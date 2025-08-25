package utils;

import java.util.List;

public class PiecePositioningUtils {
    public static final List<String> whiteRookSquares = List.of("a1", "h1");
    public static final List<String> blackRookSquares = List.of("a8", "h8");

    public static final String whiteKingSquare = "e1";
    public static final String blackKingSquare = "e8";

    public static final List<String> whitePawnSquares = List.of("a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2");
    public static final List<String> blackPawnSquares = List.of("a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7");
    public static final int whitePawnInitialRow = 6;
    public static final int blackPawnInitialRow = 1;

    public static final List<String> whiteBishopSquares = List.of("c1", "f1");
    public static final List<String> blackBishopSquares = List.of("c8", "f8");
}
