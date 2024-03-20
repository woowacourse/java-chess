package chess.domain.square.piece;

import chess.domain.position.Path;
import chess.domain.position.Position;
import chess.domain.square.Square;

import java.util.Map;

public class Pawn extends Piece {
    public static final int ATTACKABLE_FILE_DISTANCE = 1;
    public static final int MOVED_MAX_DISTANCE = 1;
    public static final int NOT_MOVED_MAX_DISTANCE = 2;
    public static final int ATTACKABLE_RANK_DISTANCE = 1;
    private boolean isMoved;

    private Pawn(Color color, boolean isMoved) {
        super(color);
        this.isMoved = isMoved;
    }

    public static Pawn from(Color color) {
        return new Pawn(color, false);
    }

    @Override
    protected boolean isValidMovePath(Path path) {
        if (color == Color.BLACK) {
            return path.isDown(maxDistance());
        }
        return path.isUp(maxDistance());
    }

    private int maxDistance() {
        if (isMoved) {
            return MOVED_MAX_DISTANCE;
        }
        return NOT_MOVED_MAX_DISTANCE;
    }

    @Override
    protected boolean isNotObstructed(Path path, Map<Position, Square> board) {
        return true;
    }

    @Override
    protected void move() {
        isMoved = true;
    }

    @Override
    protected boolean isValidAttackPath(Path path) {
        if (color == Color.BLACK) {
            return path.subtractRank() == -ATTACKABLE_RANK_DISTANCE && path.calculateFileDistance() == ATTACKABLE_FILE_DISTANCE;
        }
        return path.subtractRank() == ATTACKABLE_RANK_DISTANCE && path.calculateFileDistance() == ATTACKABLE_FILE_DISTANCE;
    }
}
