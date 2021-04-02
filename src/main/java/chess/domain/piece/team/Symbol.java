package chess.domain.piece.team;

import chess.domain.piece.*;

public enum Symbol {
    KING("k", "K"),
    QUEEN("q", "Q"),
    BISHOP("b", "B"),
    KNIGHT("n", "N"),
    ROOK("r", "R"),
    PAWN("p", "P"),
    EMPTY(".", ".");

    private final String white;
    private final String black;

    Symbol(String white, String black) {
        this.white = white;
        this.black = black;
    }

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }

    public static Piece getPiece(String symbol) {
        if (symbol.equals(KING.black)) {
            return King.createBlack();
        }
        if (symbol.equals(KING.white)) {
            return King.createWhite();
        }

        if (symbol.equals(QUEEN.black)) {
            return Queen.createBlack();
        }
        if (symbol.equals(QUEEN.white)) {
            return Queen.createWhite();
        }

        if (symbol.equals(BISHOP.black)) {
            return Bishop.createBlack();
        }
        if (symbol.equals(BISHOP.white)) {
            return Bishop.createWhite();
        }

        if (symbol.equals(KNIGHT.black)) {
            return Knight.createBlack();
        }
        if (symbol.equals(KNIGHT.white)) {
            return Knight.createWhite();
        }

        if (symbol.equals(ROOK.black)) {
            return Rook.createBlack();
        }
        if (symbol.equals(ROOK.white)) {
            return Rook.createWhite();
        }

        if (symbol.equals(PAWN.black)) {
            return Pawn.createBlack();
        }
        if (symbol.equals(PAWN.white)) {
            return Pawn.createWhite();
        }

        return Empty.create();
    }
}
