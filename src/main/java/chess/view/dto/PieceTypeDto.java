package chess.view.dto;

import static chess.domain.attribute.Color.BLACK;

import chess.domain.attribute.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;

public enum PieceTypeDto {

    KING(PieceType.KING, "k"),
    QUEEN(PieceType.QUEEN, "q"),
    BISHOP(PieceType.BISHOP, "b"),
    KNIGHT(PieceType.KNIGHT, "n"),
    ROOK(PieceType.ROOK, "r"),
    PAWN(PieceType.PAWN, "p");

    private static final String EMPTY = ".";

    private final PieceType pieceType;
    private final String value;

    PieceTypeDto(PieceType pieceType, String value) {
        this.pieceType = pieceType;
        this.value = value;
    }

    public static String of(final Piece piece) {
        if (piece == null) {
            return EMPTY;
        }
        return Arrays.stream(values())
                .filter(pieceTypeDto -> pieceTypeDto.pieceType == piece.getPieceType())
                .map(type -> parseByColor(piece.getColor(), type.value))
                .findFirst()
                .orElse(EMPTY);
    }

    private static String parseByColor(final Color color, final String value) {
        if (color == BLACK) {
            return value.toUpperCase();
        }
        return value.toLowerCase();
    }
}
