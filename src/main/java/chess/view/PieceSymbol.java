package chess.view;

import chess.domain.piece.*;

public enum PieceSymbol {
    EMPTY("."),
    PAWN("P"),
    ROOK("R"),
    KNIGHT("N"),
    QUEEN("Q"),
    KING("K"),
    BISHOP("B");

    private final String symbol;

    PieceSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public static String symbol(final Piece piece) {
        if (piece instanceof Empty) {
            return EMPTY.symbol;
        }

        if (piece instanceof Pawn) {
            return decideUpperOrLower(PAWN.symbol, piece.owner());
        }

        if (piece instanceof King) {
            return decideUpperOrLower(KING.symbol, piece.owner());
        }

        if (piece instanceof Knight) {
            return decideUpperOrLower(KNIGHT.symbol, piece.owner());
        }

        if (piece instanceof Queen) {
            return decideUpperOrLower(QUEEN.symbol, piece.owner());
        }

        if (piece instanceof Bishop) {
            return decideUpperOrLower(BISHOP.symbol, piece.owner());
        }

        if (piece instanceof Rook) {
            return decideUpperOrLower(ROOK.symbol, piece.owner());
        }

        throw new IllegalArgumentException("존재하지 않는 기물 정보입니다.");
    }

    private static String decideUpperOrLower(final String symbol, final Owner owner) {
        if (owner.isBlack()) {
            return symbol.toUpperCase();
        }

        return symbol.toLowerCase();
    }
}
