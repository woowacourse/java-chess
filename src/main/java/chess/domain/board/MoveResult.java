package chess.domain.board;

public enum MoveResult {
    MOVE_SUCCESS(true),
    KILL_ENEMY(true),
    KILL_KING(true),
    HAS_OBSTACLE(false),
    EXISTING_SAME_TEAM(false),
    INVALID_TURN(false),
    EMPTY_CELL(false),
    INVALID_MOVE_STRATEGY(false);

    private final boolean moveSuccess;

    MoveResult(boolean moveSuccess) {
        this.moveSuccess = moveSuccess;
    }

    public boolean isMoveSuccess() {
        return moveSuccess;
    }
}
