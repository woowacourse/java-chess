package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece implements Chessmen {

    protected static final String INVALID_MOVABLE_POSITION_EXCEPTION_MESSAGE = "이동할 수 없는 위치입니다.";
    private static final String INVALID_ATTACK_TARGET_EXCEPTION_MESSAGE = "이동하려는 위치에 아군이 있습니다.";

    protected final Color color;
    protected Position position;

    protected Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public void kill(Chessmen targetPiece) {
        if (color == targetPiece.getColor()) {
            throw new IllegalArgumentException(INVALID_ATTACK_TARGET_EXCEPTION_MESSAGE);
        }
        attack(targetPiece.getPosition());
    }

    abstract protected void attack(Position enemyPosition);

    @Override
    public final Position getPosition() {
        return position;
    }

    @Override
    public final Color getColor() {
        return color;
    }
}
