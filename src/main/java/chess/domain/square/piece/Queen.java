package chess.domain.square.piece;

import chess.domain.position.Path;
import java.util.Map;

public class Queen extends Piece {
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
    protected boolean isValidMovePath(Path path) {
        return path.isStraight() || path.isDiagonal();
    }
}
