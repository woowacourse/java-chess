package chess2.domain2.board2.piece2;

import static chess2.domain2.board2.piece2.PieceType.BISHOP;
import static chess2.domain2.board2.piece2.PieceType.KING;
import static chess2.domain2.board2.piece2.PieceType.KNIGHT;
import static chess2.domain2.board2.piece2.PieceType.PAWN;
import static chess2.domain2.board2.piece2.PieceType.QUEEN;
import static chess2.domain2.board2.piece2.PieceType.ROOK;

import java.util.HashMap;
import java.util.Map;

public class PieceScoreUtil {

    private PieceScoreUtil() {
    }

    public static double toScore(Piece piece) {
        PieceType pieceType = piece.type();
        return PieceScoreMap.scoreOf(pieceType);
    }

    private static class PieceScoreMap {

        static final double PAWN_DEFAULT_SCORE = 1;
        static final double KNIGHT_SCORE = 2.5;
        static final double BISHOP_SCORE = 3;
        static final double ROOK_SCORE = 5;
        static final double QUEEN_SCORE = 9;
        static final double KING_SCORE = 0;

        private static final Map<PieceType, Double> pieceScoreMap;

        static {
            Map<PieceType, Double> scoreMap = new HashMap<>();
            scoreMap.put(PAWN, PAWN_DEFAULT_SCORE);
            scoreMap.put(KNIGHT, KNIGHT_SCORE);
            scoreMap.put(BISHOP, BISHOP_SCORE);
            scoreMap.put(ROOK, ROOK_SCORE);
            scoreMap.put(QUEEN, QUEEN_SCORE);
            scoreMap.put(KING, KING_SCORE);
            pieceScoreMap = scoreMap;
        }

        public static double scoreOf(PieceType pieceType) {
            return pieceScoreMap.get(pieceType);
        }
    }
}