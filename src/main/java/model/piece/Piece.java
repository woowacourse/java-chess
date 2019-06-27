package model.piece;

import model.board.Coord;
import model.board.Direction;
import model.board.Position;
import model.game.Color;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class Piece implements Comparable<Piece> {
    final Color owner;
    Position position;
    int totalMoved = 0;

    protected Piece(final Color owner, final Position position) {
        this.owner = Optional.ofNullable(owner).orElseThrow(IllegalArgumentException::new);
        this.position = Optional.ofNullable(position).orElseThrow(IllegalArgumentException::new);
    }

    protected Piece(final Piece copyFrom) {
        this.owner = copyFrom.owner;
        this.position = copyFrom.position;
        this.totalMoved = copyFrom.totalMoved;
    }

    public abstract Stream<Iterator<Position>> getIteratorsOfPossibleDestinations();

    protected Iterator<Position> proceedUntilBlocked(final Direction dir) {
        return new Iterator<Position>() {
            private Position current = position;

            @Override
            public boolean hasNext() {
                return current.testForward(dir);
            }

            @Override
            public Position next() {
                return current = current.moveForward(dir);
            }
        };
    }

    public boolean move(final Position dest) {
        this.position = dest;
        this.totalMoved++;
        return true;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public Color team() {
        return this.owner;
    }

    public boolean hasNotMoved() {
        return this.totalMoved == 0;
    }

    public abstract double getScore();

    public int ordinal() {
        return this.position.ordinal();
    }

    public Position position() {
        return this.position;
    }

    public Coord x() {
        return this.position.x();
    }

    public Coord y() {
        return this.position.y();
    }

    @Override
    public int compareTo(Piece rhs) {
        return this.position.ordinal() - rhs.position.ordinal();
    }
}