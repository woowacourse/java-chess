package chess.domain.piece;

import chess.strategy.OccupiedChecker;

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
    public final void move(Position position, OccupiedChecker isOccupied) {
        validateMovable(position);
        validateClearPathTo(position, isOccupied);
        this.position = position;
    }

    private void validateMovable(Position targetPosition) {
        if (!canMoveTo(targetPosition)) {
            throw new IllegalArgumentException(INVALID_MOVABLE_POSITION_EXCEPTION_MESSAGE);
        }
    }

    abstract protected boolean canMoveTo(Position targetPosition);

    private void validateClearPathTo(Position targetPosition, OccupiedChecker isOccupied) {
        boolean isClear =  position.positionsBetween(targetPosition)
                .stream()
                .noneMatch(isOccupied::test);

        if (!isClear) {
            throw new IllegalArgumentException(BLOCKED_PATH_EXCEPTION_MESSAGE);
        }
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
    public final Position position() {
        return position;
    }
}
