package chess.domain.square.piece;

import chess.domain.position.Path;
import java.util.Map;

public class Bishop extends Piece {
    private static final Map<Color, Bishop> BISHOP_POOL = Map.of(
            Color.WHITE, new Bishop(Color.WHITE),
            Color.BLACK, new Bishop(Color.BLACK));

    private Bishop(Color color) {
        super(color);
    }

    public static Bishop from(Color color) {
        return BISHOP_POOL.get(color);
    }

    @Override
    protected boolean isValidMovePath(Path path) {
        return path.isDiagonal();
    }
}
