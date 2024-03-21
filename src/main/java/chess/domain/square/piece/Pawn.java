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

    public static Pawn createOnStart(Color color) {
        return new Pawn(color, false);
    }

    public static Pawn of(Color color, boolean isMoved) {
        return new Pawn(color, isMoved);
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
    public void move() {
        isMoved = true;
    }

    @Override
    protected boolean isValidAttackPath(Path path) {
        if (color == Color.BLACK) {
            return path.subtractRank() == -ATTACKABLE_RANK_DISTANCE && path.calculateFileDistance() == ATTACKABLE_FILE_DISTANCE;
        }
        return path.subtractRank() == ATTACKABLE_RANK_DISTANCE && path.calculateFileDistance() == ATTACKABLE_FILE_DISTANCE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Pawn pawn = (Pawn) o;

        return isMoved == pawn.isMoved;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isMoved ? 1 : 0);
        return result;
    }
}
