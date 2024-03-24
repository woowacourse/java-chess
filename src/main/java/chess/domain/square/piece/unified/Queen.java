package chess.domain.square.piece.unified;

import chess.domain.position.Path;
import chess.domain.square.piece.Color;
import java.util.Map;

public class Queen extends UnifiedArriveWay {
    private static final Map<Color, Queen> QUEEN_POOL = Map.of(
            Color.WHITE, new Queen(Color.WHITE),
            Color.BLACK, new Queen(Color.BLACK));

    private Queen(Color color) {
        super(color);
    }

    public static Queen from(Color color) {
        return QUEEN_POOL.get(color);
    }

    @Override
    protected boolean canMove(Path path) {
        return path.isStraight() || path.isDiagonal();
    }
}
