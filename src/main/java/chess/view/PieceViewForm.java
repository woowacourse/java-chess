package chess.view;

import chess.domain.Color;
import chess.domain.PieceType;
import chess.domain.piece.Piece;

import java.util.Arrays;

public enum PieceViewForm {
    PAWN(PieceType.PAWN, 'P'),
    ROOKS(PieceType.ROOK, 'R'),
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
                .filter(pieceForm -> pieceForm.type == piece.getPieceType())
                .findFirst()
                .orElseThrow();
        if (piece.isSameColor(Color.BLACK)) {
            return String.valueOf(findPieceForm.name);
        }
        return String.valueOf(Character.toLowerCase(findPieceForm.name));
    }
}
