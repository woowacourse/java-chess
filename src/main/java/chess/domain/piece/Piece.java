package chess.domain.piece;

import chess.domain.piece.position.Position;

public abstract class Piece {

    protected static final String INVALID_MOVABLE_POSITION_EXCEPTION_MESSAGE = "이동할 수 없는 위치입니다.";

    protected final Color color;
    protected Position position;

    protected Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    abstract public void move(Position position);

    abstract public String display();

    public Position getPosition() {
        return position;
    }
}
