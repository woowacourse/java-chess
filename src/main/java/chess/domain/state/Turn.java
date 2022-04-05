package chess.domain.state;

import chess.domain.piece.PieceColor;

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
