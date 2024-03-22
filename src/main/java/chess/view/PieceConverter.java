package chess.view;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Map;

public class PieceConverter {
    private static final Map<PieceType, String> PIECE_DISPLAYS = Map.of(
            PieceType.PAWN, "P",
            PieceType.KING, "K",
            PieceType.QUEEN, "Q",
            PieceType.ROOK, "R",
            PieceType.KNIGHT, "N",
            PieceType.BISHOP, "B"
    );

    private PieceConverter() {
    }

    public static String convert(Piece piece) {
        PieceType pieceType = piece.getPieceType();
        String pieceDisplay = PIECE_DISPLAYS.get(pieceType);

        if (piece.isWhite()) {
            return pieceDisplay.toLowerCase();
        }

        return pieceDisplay;
    }
}
