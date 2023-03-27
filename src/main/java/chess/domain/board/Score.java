package chess.domain.board;

import static chess.domain.piece.PieceType.BISHOP;
import static chess.domain.piece.PieceType.KING;
import static chess.domain.piece.PieceType.KNIGHT;
import static chess.domain.piece.PieceType.PAWN;
import static chess.domain.piece.PieceType.QUEEN;
import static chess.domain.piece.PieceType.ROOK;

import chess.domain.piece.PieceType;
import java.util.Map;

public final class Score {

    private static final Map<PieceType, Double> scores;
    public static final double PAWN_NON_DEFAULT_SCORE = 0.5;

    static {
        scores = Map.of(
                KING, 0.0,
                QUEEN, 9.0,
                ROOK, 5.0,
                BISHOP, 3.0,
                KNIGHT, 2.5,
                PAWN, 1.0
        );
    }

    public static double of(PieceType pieceType, boolean isNonDefault) {
        if (pieceType == PAWN && isNonDefault) {
            return PAWN_NON_DEFAULT_SCORE;
        }
        return scores.get(pieceType);
    }
}
