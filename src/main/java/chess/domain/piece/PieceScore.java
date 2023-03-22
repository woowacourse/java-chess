package chess.domain.piece;

import java.util.EnumMap;
import java.util.Map;

public class PieceScore {
    
    private static final Map<PieceType, Score> PIECE_SCORE_MAP = new EnumMap<>(PieceType.class);
    
    static {
        PIECE_SCORE_MAP.put(PieceType.PAWN, Score.from(1));
        PIECE_SCORE_MAP.put(PieceType.KNIGHT, Score.from(2.5));
        PIECE_SCORE_MAP.put(PieceType.BISHOP, Score.from(3));
        PIECE_SCORE_MAP.put(PieceType.ROOK, Score.from(5));
        PIECE_SCORE_MAP.put(PieceType.QUEEN, Score.from(9));
        PIECE_SCORE_MAP.put(PieceType.KING, Score.from(0));
    }
    
    public static Score getScore(PieceType pieceType) {
        return PIECE_SCORE_MAP.get(pieceType);
    }
}
