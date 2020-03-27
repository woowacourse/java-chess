package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
    protected Position position;
    protected final String name;
    protected final MoveStrategy moveStrategy;

    public Piece(Position position, String name, MoveStrategy moveStrategy) {
        this.position = position;
        this.name = name;
        this.moveStrategy = moveStrategy;
    }

    public void moveTo(Position destination) {
        if (moveStrategy.isNotMovableTo(position, destination)) {
            throw new IllegalArgumentException("기물의 이동 범위에 속하지 않습니다.");
        }
        position = destination;
    }

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
