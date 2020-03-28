package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
    protected Position position;
    protected final String name;

    public Piece(Position position, String name) {
        this.position = position;
        this.name = name;
    }

    public void moveTo(Position destination) {
        if (isNotMovableTo(position, destination)) {
            throw new IllegalArgumentException("기물의 이동 범위에 속하지 않습니다.");
        }
        position = destination;
    }

    protected abstract boolean isNotMovableTo(Position start, Position destination);

    public Position getPosition() {
        return position;
    }

    public String getUpperName() {
        return name.toUpperCase();
    }

    public String getName() {
        return name;
    }
}
