package chess.piece;

import chess.Color;

public enum PieceType {
    KING("♔", "♚"),
    QUEEN("♕", "♛"),
    ROOK("♖", "♜"),
    KNIGHT("♘", "♞"),
    PAWN("♙", "♟"),
    BISHOP("♗", "♝"),
    EMPTY("＿", "＿");

    private final String white;
    private final String black;

    PieceType(final String white, final String black) {
        this.white = white;
        this.black = black;
    }

    public String getTeam(Color color) {
        if (color.isWhite()) {
            return white;
        }
        return black;
    }
}
