package chess.domain.state;

import chess.domain.PieceColor;

public enum Turn {
    WHITE(PieceColor.WHITE),
    BLACK(PieceColor.BLACK)
    ;

    private PieceColor pieceColor;

    Turn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public static Turn from() {
        return WHITE;
    }

    public static Turn from(String turn) {
        if (PieceColor.WHITE == PieceColor.valueOf(turn)) {
            return WHITE;
        }
        return BLACK;
    }

    public Turn change() {
        if (this == Turn.WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }
}
