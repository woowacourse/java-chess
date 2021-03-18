package chess.domain.board.position;

import chess.controller.direction.Direction;
import chess.domain.piece.Owner;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Position {
    private final Vertical vertical;
    private final Horizontal horizontal;

    public Position(Vertical vertical, Horizontal horizontal){
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public boolean isStraight(Position other) {
        return this.horizontal.equals(other.horizontal) || this.vertical.equals(other.vertical);
    }

    public boolean isDiagonal(Position other) {
        return this.horizontal.getDistance(other.horizontal)
                == this.vertical.getDistance(other.vertical);
    }

    public int getDistance(Position other){
        if(isStraight(other)){
            return this.horizontal.getDistance(other.horizontal)
                    + this.vertical.getDistance(other.vertical);
        }

        if(isDiagonal(other)){
            return this.horizontal.getDistance(other.horizontal);
        }

        throw new IllegalArgumentException();
    }

    public boolean isForward(Owner owner, Position other) {
        return this.horizontal.isForward(owner, other.horizontal);
    }

    public Horizontal getHorizontal(Position target) {
        return horizontal;
    }

    public int getHorizontalDistance(Position other){
        return horizontal.getDistance(other.horizontal);
    }

    public int getVerticalDistance(Position other){
        return vertical.getDistance(other.vertical);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return vertical == position.vertical && horizontal == position.horizontal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertical, horizontal);
    }

    public Position next(Direction direction, int distance) {
        return new Position(vertical.add(direction.getY()+distance), horizontal.add(direction.getX()+distance));
    }
}
