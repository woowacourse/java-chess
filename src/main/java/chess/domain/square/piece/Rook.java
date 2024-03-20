package chess.domain.square.piece;

import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.square.Empty;
import chess.domain.square.Square;

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

    @Override
    protected boolean isNotObstructed(Path path, Map<Position, Square> board) {
        return path.findStraight().stream()
                .allMatch(position -> board.get(position) == Empty.getInstance());
    }

    @Override
    public void move() {
    }

    @Override
    protected boolean isValidAttackPath(Path path) {
        return isValidMovePath(path);
    }
}
