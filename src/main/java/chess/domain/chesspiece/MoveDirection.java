package chess.domain.chesspiece;

public enum MoveDirection {
    DIAGONAL("diagonal"),
    HORIZONTAL("horizontal"),
    VERTICAL("vertical"),
    PAWN_MOVING("pawn"),
    KNIGHT_MOVING("knight"),
    FOUR_DIRECTION_MOVING("fourDirections");

    private final String movingDirection;

    MoveDirection(String movingDirection) {
        this.movingDirection = movingDirection;
    }

    public String getMovingDirection() {
        return movingDirection;
    }
}
