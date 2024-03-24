package chess.domain.square.piece.divided;

import chess.domain.position.Path;
import chess.domain.square.piece.Color;
import java.util.Map;

public class Pawn extends DividedArriveWay {
    private static final int ATTACKABLE_FILE_DISTANCE = 1;
    private static final int NORMAL_MOVABLE_DISTANCE = 1;
    private static final int FIRST_MOVABLE_MAX_DISTANCE = 2;
    private static final int ATTACKABLE_RANK_DISTANCE = 1;
    private static final int BLACK_START_RANK = 7;
    private static final int WHITE_START_RANK = 2;
    private static final int DOWN_DIRECTION = -1;
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
    protected boolean canMove(Path path) {
        if (isColor(Color.BLACK)) {
            return path.isDown(maxDistance(path, BLACK_START_RANK));
        }
        return path.isUp(maxDistance(path, WHITE_START_RANK));
    }

    private int maxDistance(Path path, int startRank) {
        if (path.isStartRank(startRank)) {
            return FIRST_MOVABLE_MAX_DISTANCE;
        }
        return NORMAL_MOVABLE_DISTANCE;
    }

    @Override
    protected boolean canAttack(Path path) {
        int attackableRankDiff = ATTACKABLE_RANK_DISTANCE;
        if (isColor(Color.BLACK)) {
            attackableRankDiff *= DOWN_DIRECTION;
        }
        return path.subtractRank() == attackableRankDiff
                && path.fileDistance() == ATTACKABLE_FILE_DISTANCE;
    }
}
