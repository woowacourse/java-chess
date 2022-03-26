package chess.domain.piece;

import chess.domain.piece.position.Position;
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
        if (color == targetPiece.color) {
            throw new IllegalArgumentException(INVALID_ATTACK_TARGET_EXCEPTION_MESSAGE);
        }
        attack(targetPiece.position);
    }

    public boolean isAt(Position position) {
        return this.position == position;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isFriendly(Piece targetPiece) {
        return this.color == targetPiece.color;
    }

    abstract public void checkReachable(Position toPosition);

    abstract public List<Position> getPositionsInPath(Position position);

    abstract public void move(Position position);

    abstract protected void attack(Position enemyPosition);

    abstract public boolean isPawn();

    abstract public boolean isKing();

    abstract public double score();

    abstract public String display();

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

}
