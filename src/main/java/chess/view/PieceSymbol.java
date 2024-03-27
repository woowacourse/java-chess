package chess.view;

import chess.domain.piece.Piece;

import java.util.Arrays;

public enum PieceSymbol {
    KING("k"),
    QUEEN("q"),
    BISHOP("b"),
    KNIGHT("n"),
    ROOK("r"),
    PAWN("p"),
    EMPTY(".");

    private final String symbol;

    PieceSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static String convertToSymbol(Piece piece) {
        String name = Arrays.stream(values())
                .filter(pieceSymbol -> piece.identifyType().name().equals(pieceSymbol.name()))
                .findFirst()
                .orElseThrow()
                .symbol;

        if (piece.isBlack()) {
            return name.toUpperCase();
        }
        return name;
    }

    public static String printEmptySymbol() {
        return EMPTY.symbol;
    }
}
