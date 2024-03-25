package chess.domain.square.piece.divided;

import chess.domain.position.PathFinder;
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
    protected boolean canMove(PathFinder pathFinder) {
        if (isColor(Color.BLACK)) {
            return pathFinder.isDown(maxDistance(pathFinder, BLACK_START_RANK));
        }
        return pathFinder.isUp(maxDistance(pathFinder, WHITE_START_RANK));
    }

    private int maxDistance(PathFinder pathFinder, int startRank) {
        if (pathFinder.isStartRank(startRank)) {
            return FIRST_MOVABLE_MAX_DISTANCE;
        }
        return NORMAL_MOVABLE_DISTANCE;
    }

    @Override
    protected boolean canAttack(PathFinder pathFinder) {
        int attackableRankDiff = ATTACKABLE_RANK_DISTANCE;
        if (isColor(Color.BLACK)) {
            attackableRankDiff *= DOWN_DIRECTION;
        }
        return (pathFinder.subtractRank() == attackableRankDiff) &&
                (pathFinder.fileDistance() == ATTACKABLE_FILE_DISTANCE);
    }
}
