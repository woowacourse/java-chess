package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;

public abstract class Piece {

    protected static final String INVALID_MOVABLE_POSITION_EXCEPTION_MESSAGE = "이동할 수 없는 위치입니다.";
    private static final String INVALID_ATTACK_TARGET_EXCEPTION_MESSAGE = "이동하려는 위치에 아군이 있습니다.";

    protected final Color color;
    protected Position position;

    protected Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public void kill(Piece targetPiece) {
        if (isFriendly(targetPiece)) {
            throw new IllegalArgumentException(INVALID_ATTACK_TARGET_EXCEPTION_MESSAGE);
        }
        attack(targetPiece.position);
    }

    public boolean isAt(Position targetPosition) {
        return position.isSamePosition(targetPosition);
    }

    public boolean isSameColor(Color targetColor) {
        return color.isSameColor(targetColor);
    }

    public boolean isFriendly(Piece targetPiece) {
        return color.isSameColor(targetPiece.color);
    }

    abstract public List<Position> getPositionsInPath(Position position);

    abstract public void move(Position position);

    abstract public void validateMovable(Position toPosition);

    abstract protected void attack(Position enemyPosition);

    abstract public boolean isPawn();

    abstract public boolean isKing();

    abstract public double getScore();

    abstract public String getName();

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

}
