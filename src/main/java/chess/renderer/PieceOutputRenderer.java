package chess.renderer;


import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.Arrays;

public enum PieceOutputRenderer {
    PAWN("p"),
    ROOK("r"),
    KNIGHT("n"),
    BISHOP("b"),
    QUEEN("q"),
    KING("k"),
    EMPTY(".");

    private final String value;

    PieceOutputRenderer(final String value) {
        this.value = value;
    }

    public static String getPieceName(final Piece piece) {
        final PieceOutputRenderer renderedPiece = renderPiece(piece.getPieceType());

        if (piece.isWhite()) {
            return renderedPiece.value;
        }

        return renderedPiece.value.toUpperCase();
    }

    private static PieceOutputRenderer renderPiece(final PieceType pieceType) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(pieceType.name()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
