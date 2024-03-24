package chess.domain.square.piece;

import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.square.Empty;
import chess.domain.square.Square;

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

    @Override
    protected boolean isNotObstructed(Path path, Map<Position, Square> board) {
        if (path.isStraight()) {
            return path.findStraight().stream()
                    .allMatch(position -> board.get(position) == Empty.getInstance());
        }
        return path.findDiagonal().stream()
                .allMatch(position -> board.get(position) == Empty.getInstance());
    }
}
