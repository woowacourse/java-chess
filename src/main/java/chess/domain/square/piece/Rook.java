package chess.domain.square.piece;

import chess.domain.position.Path;
import java.util.Map;

public class Rook extends Piece {
    private static final Map<Color, Rook> ROOK_POOL = Map.of(
            Color.WHITE, new Rook(Color.WHITE),
            Color.BLACK, new Rook(Color.BLACK));

    private Rook(Color color) {
        super(color);
    }

    public static Rook from(Color color) {
        return ROOK_POOL.get(color);
    }

    @Override
    protected boolean isValidMovePath(Path path) {
        return path.isStraight();
    }
}
