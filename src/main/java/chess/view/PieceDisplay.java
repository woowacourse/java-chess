package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;

public enum PieceDisplay {
    PAWN(PieceType.PAWN, "P"),
    KING(PieceType.KING, "K"),
    QUEEN(PieceType.QUEEN, "Q"),
    ROOK(PieceType.ROOK, "R"),
    KNIGHT(PieceType.KNIGHT, "N"),
    BISHOP(PieceType.BISHOP, "B");

    private final PieceType pieceType;
    private final String viewName;

    PieceDisplay(PieceType pieceType, String viewName) {
        this.pieceType = pieceType;
        this.viewName = viewName;
    }

    public static String convert(Piece piece) {
        PieceDisplay pieceDisplay = PieceDisplay.from(piece);
        String viewName = pieceDisplay.viewName;

        if (piece.isWhite()) {
            return viewName.toLowerCase();
        }

        return viewName;
    }

    private static PieceDisplay from(Piece piece) {
        return Arrays.stream(values())
                .filter(pieceDisplay -> isSamePieceType(piece, pieceDisplay))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물 타입니다."));
    }

    private static boolean isSamePieceType(Piece piece, PieceDisplay pieceDisplay) {
        PieceType pieceType = piece.getPieceType();

        return pieceType.equals(pieceDisplay.pieceType);
    }
}
