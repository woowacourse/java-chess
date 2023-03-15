package chess.view;

import chess.domain.Color;
import chess.domain.Piece;
import chess.domain.PieceType;

import java.util.Arrays;

public enum PieceViewForm {
    PAWN(PieceType.PAWN, 'P'),
    ROOKS(PieceType.ROOKS, 'R'),
    KNIGHT(PieceType.KNIGHT, 'N'),
    BISHOP(PieceType.BISHOP, 'B'),
    QUEEN(PieceType.QUEEN, 'Q'),
    KING(PieceType.KING, 'K'),
    EMPTY(PieceType.EMPTY, '.');

    private final PieceType type;
    private final char name;

    PieceViewForm(final PieceType type, final char name) {
        this.type = type;
        this.name = name;
    }

    public static String parseToName(final Piece piece) {
        final PieceViewForm findPieceForm = Arrays.stream(PieceViewForm.values())
                .filter(pieceForm -> pieceForm.type == piece.getType())
                .findFirst()
                .orElseThrow();
        if (piece.getColor() == Color.BLACK) {
            return String.valueOf(findPieceForm.name);
        }
        return String.valueOf(Character.toLowerCase(findPieceForm.name));
    }
}
