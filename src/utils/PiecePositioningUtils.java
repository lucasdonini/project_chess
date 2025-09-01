package utils;

import main.pieces.CastlingType;

import java.util.List;
import java.util.Map;

import static main.pieces.CastlingType.*;

public class PiecePositioningUtils {
    public static final List<String> whiteRookSquares = List.of("a1", "h1");
    public static final List<String> blackRookSquares = List.of("a8", "h8");

    public static final Map<CastlingType, Move> whiteRookCastling = Map.of(
            KINGSIDE, new Move("h1", "f1"),
            QUEENSIDE, new Move("a1", "d1")
    );

    public static final Map<CastlingType, Move> blackRookCastling = Map.of(
            KINGSIDE, new Move("h8", "f8"),
            QUEENSIDE, new Move("a8", "d8")
    );

    public static final List<String> whiteKnightSquares = List.of("b1", "g1");
    public static final List<String> blackKnightSquares = List.of("b8", "g8");

    public static final List<String> whiteBishopSquares = List.of("c1", "f1");
    public static final List<String> blackBishopSquares = List.of("c8", "f8");

    public static final String whiteQueenSquare = "d1";
    public static final String blackQueenSquare = "d8";

    public static final String whiteKingSquare = "e1";
    public static final String blackKingSquare = "e8";

    public static final Map<CastlingType, Move> whiteKingCastling = Map.of(
            KINGSIDE, new Move(whiteKingSquare, "g1"),
            QUEENSIDE, new Move(whiteKingSquare, "c1")
    );

    public static final Map<CastlingType, Move> blackKingCastling = Map.of(
            KINGSIDE, new Move(blackKingSquare, "g8"),
            QUEENSIDE, new Move(blackKingSquare, "c8")
    );

    public static final List<String> whitePawnSquares = List.of("a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2");
    public static final List<String> blackPawnSquares = List.of("a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7");

    public static final int blackExchangingLine = 7;
    public static final int whiteExchangingLine = 0;
}
