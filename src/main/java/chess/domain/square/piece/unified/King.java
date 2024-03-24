package chess.domain.square.piece.unified;

import chess.domain.position.Path;
import chess.domain.square.piece.Color;
import java.util.Map;

public class King extends UnifiedArriveWay {
    private static final int MOVABLE_DISTANCE = 1;
    private static final Map<Color, King> KING_POOL = Map.of(
            Color.WHITE, new King(Color.WHITE),
            Color.BLACK, new King(Color.BLACK));

    private King(Color color) {
        super(color);
    }

    public static King from(Color color) {
        return KING_POOL.get(color);
    }

    @Override
    protected boolean canMove(Path path) {
        return path.isStraight(MOVABLE_DISTANCE) || path.isDiagonal(MOVABLE_DISTANCE);
    }
}
