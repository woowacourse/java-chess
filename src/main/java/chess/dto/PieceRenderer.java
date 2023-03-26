package chess.dto;

import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.Arrays;

public enum PieceRenderer {
    PAWN("p", PieceType.PAWN),
    ROOK("r", PieceType.ROOK),
    KNIGHT("n", PieceType.KNIGHT),
    BISHOP("b", PieceType.BISHOP),
    QUEEN("q", PieceType.QUEEN),
    KING("k", PieceType.KING),
    EMPTY(".", PieceType.EMPTY);

    private final String value;
    private final PieceType pieceType;

    PieceRenderer(String value, PieceType pieceType) {
        this.value = value;
        this.pieceType = pieceType;
    }

    public static Piece render(String input) {
        PieceRenderer pieceRenderer = Arrays.stream(values())
                .filter(it -> it.value.equals(input.toLowerCase()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        if (pieceRenderer.value.equals(input)) {
            return pieceRenderer.pieceType.createPiece(Camp.WHITE);
        }

        return pieceRenderer.pieceType.createPiece(Camp.BLACK);
    }

    public static String render(Piece piece) {
        PieceRenderer renderedPiece = Arrays.stream(values())
                .filter(value -> value.pieceType == piece.getPieceType())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        if (piece.isWhite()) {
            return renderedPiece.value;
        }

        return renderedPiece.value.toUpperCase();
    }
}
