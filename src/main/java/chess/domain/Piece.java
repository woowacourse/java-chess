package chess.domain;

import chess.PieceColor;

public enum Piece {
    KING("K", "k"),
    QUEEN("Q", "q"),
    ROOK("R", "r"),
    BISHOP("B", "b"),
    NIGHT("N", "n"), // TODO: knight로 바꾸기
    EMPTY(".", "."),
    PAWN("P", "p");

    private final String whiteSymbol;
    private final String blackSymbol;

    Piece(String whiteSymbol, String blackSymbol) {
        this.whiteSymbol = whiteSymbol;
        this.blackSymbol = blackSymbol;
    }

    public String applyColor(final PieceColor color) {
        if (color.equals(PieceColor.BLACK)) {
            return blackSymbol;
        }
        return whiteSymbol;
    }

    public String getWhiteSymbol() {
        return whiteSymbol;
    }

    public String getBlackSymbol() {
        return blackSymbol;
    }
}

