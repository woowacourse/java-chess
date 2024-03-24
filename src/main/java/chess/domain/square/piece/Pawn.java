package chess.domain.square.piece;

import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.square.Empty;
import chess.domain.square.Square;
import java.util.Map;

public class Pawn extends Piece {
    private static final int ATTACKABLE_FILE_DISTANCE = 1;
    private static final int MOVED_MAX_DISTANCE = 1;
    private static final int NOT_MOVED_MAX_DISTANCE = 2;
    private static final int ATTACKABLE_RANK_DISTANCE = 1;
    private static final int BLACK_START_RANK = 7;
    private static final int WHITE_START_RANK = 2;

    private static final Map<Color, Pawn> PAWN_POOL = Map.of(
            Color.WHITE, new Pawn(Color.WHITE),
            Color.BLACK, new Pawn(Color.BLACK));

    private Pawn(Color color) {
        super(color);
    }

    public static Pawn from(Color color) {
        return PAWN_POOL.get(color);
    }

    @Override
    public boolean canMove(Path path, Map<Position, Square> board) {
        final Square targetSquare = board.get(path.getTargetPosition());
        if (targetSquare.isColor(getColor())) {
            return false;
        }
        if (targetSquare == Empty.getInstance()) {
            return isValidMovePath(path) && isNotObstructed(path, board);
        }
        return isValidAttackPath(path) && isNotObstructed(path, board);
    }

    @Override
    protected boolean isValidMovePath(Path path) {
        if (isColor(Color.BLACK)) {
            return path.isDown(maxDistance(path, BLACK_START_RANK));
        }
        return path.isUp(maxDistance(path, WHITE_START_RANK));
    }

    private int maxDistance(Path path, int startRank) {
        if (path.isStartRank(startRank)) {
            return NOT_MOVED_MAX_DISTANCE;
        }
        return MOVED_MAX_DISTANCE;
    }

    private boolean isValidAttackPath(Path path) {
        if (isColor(Color.BLACK)) {
            return path.subtractRank() == -ATTACKABLE_RANK_DISTANCE
                    && path.calculateFileDistance() == ATTACKABLE_FILE_DISTANCE;
        }
        return path.subtractRank() == ATTACKABLE_RANK_DISTANCE
                && path.calculateFileDistance() == ATTACKABLE_FILE_DISTANCE;
    }
}
