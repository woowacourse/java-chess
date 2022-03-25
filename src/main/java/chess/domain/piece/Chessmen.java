package chess.domain.piece;

import chess.domain.position.Position;
import chess.strategy.OccupiedChecker;
import java.util.List;

public abstract class Chessmen implements Piece {

    protected static final String INVALID_MOVABLE_POSITION_EXCEPTION_MESSAGE = "이동할 수 없는 위치입니다.";
    private static final String BLOCKED_PATH_EXCEPTION_MESSAGE = "다른 말이 가로막고 있습니다.";
    private static final String INVALID_ATTACK_TARGET_EXCEPTION_MESSAGE = "이동하려는 위치에 아군이 있습니다.";

    protected final Color color;
    protected Position position;

    protected Chessmen(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public void kill(Piece targetPiece, OccupiedChecker isOccupied) {
        validateIsEnemy(targetPiece);
        attack(targetPiece.position(), isOccupied);
    }

    private void validateIsEnemy(Piece targetPiece) {
        if (targetPiece.hasColorOf(color)) {
            throw new IllegalArgumentException(INVALID_ATTACK_TARGET_EXCEPTION_MESSAGE);
        }
    }

    abstract protected void attack(Position enemyPosition, OccupiedChecker isOccupied);

    protected void validateClearPathTo(Position targetPosition, OccupiedChecker isOccupied) {
        boolean isClear = positionsToPass(targetPosition).stream()
                .noneMatch(isOccupied::test);

        if (!isClear) {
            throw new IllegalArgumentException(BLOCKED_PATH_EXCEPTION_MESSAGE);
        }
    }

    abstract protected List<Position> positionsToPass(Position targetPosition);

    @Override
    public final boolean hasColorOf(Color color) {
        return this.color == color;
    }

    @Override
    public final boolean isAt(Position position) {
        return this.position == position;
    }

    @Override
    public final boolean isAtFileOrColumnIdxOf(int idx) {
        int fileOrColumnIdx = position.getFileIdx();
        return fileOrColumnIdx == idx;
    }

    @Override
    public final boolean isAtRowIdxOf(int rowIdx) {
        int displayRowIdx = position.toDisplayRowIndex();
        return displayRowIdx == rowIdx;
    }

    @Override
    public final Position position() {
        return position;
    }
}
