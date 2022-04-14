package chess.domain.piece;

import chess.domain.game.Color;

import java.util.LinkedHashMap;
import java.util.Map;

public class PieceFactory {
    private static final Map<String, MovingPattern> MOVING_PATTERN_MAP = new LinkedHashMap<>();

    static {
        MOVING_PATTERN_MAP.put("BISHOP", new BishopMovingPattern());
        MOVING_PATTERN_MAP.put("ROOK", new RookMovingPattern());
        MOVING_PATTERN_MAP.put("PAWN", new PawnMovingPattern());
        MOVING_PATTERN_MAP.put("KNIGHT", new KnightMovingPattern());
        MOVING_PATTERN_MAP.put("KING", new KingMovingPattern());
        MOVING_PATTERN_MAP.put("QUEEN", new QueenMovingPattern());
        MOVING_PATTERN_MAP.put("BLANK", new BlankPattern());
    }

    public static Piece of(String pieceType, String color, int moveCount) {
        return new Piece(Color.of(color), PieceType.of(pieceType), MOVING_PATTERN_MAP.get(pieceType), moveCount);
    }
}
