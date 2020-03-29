package chess.domain.piece;

import chess.domain.position.Position;

public abstract class Piece {
    protected Position position;
    protected final Name name;

    public Piece(Position position, String name) {
        this.position = position;
        this.name = Name.valueOf(name.toUpperCase());
    }

    public Piece(Position position, Name name) {
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
        return name.upperName();
    }

    public String getLowerName() {
        return name.lowerName();
    }
}
