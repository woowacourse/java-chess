package chess.view;

import chess.domain.piece.Piece;

public enum PieceSymbolMapper {
    PAWN("P", "p"),
    ROOK("R", "r"),
    BISHOP("B", "b"),
    KNIGHT("N", "n"),
    KING("K", "k"),
    QUEEN("Q", "q"),
    BLANK(".", "."),
    ;

    private final String blackSymbol;
    private final String whiteSymbol;

    PieceSymbolMapper(String blackSymbol, String whiteSymbol) {
        this.blackSymbol = blackSymbol;
        this.whiteSymbol = whiteSymbol;
    }

    public static String getSymbol(Piece piece) {
        if (piece.isBlackTeam()) {
            return valueOf(piece.getName().toUpperCase()).getBlackSymbol();
        }
        return valueOf(piece.getName().toUpperCase()).getWhiteSymbol();
    }

    public String getBlackSymbol() {
        return blackSymbol;
    }

    public String getWhiteSymbol() {
        return whiteSymbol;
    }
}
