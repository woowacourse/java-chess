package model.piece;

import model.board.Coordinate;
import model.board.Direction;
import model.board.Position;
import model.game.Player;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class Piece implements Comparable<Piece> {
    final Player owner;
    Position position;
    private int totalMoved = 0;

    protected Piece(final Player owner, final Position position) {
        this.owner = Optional.ofNullable(owner).orElseThrow(IllegalArgumentException::new);
        this.position = Optional.ofNullable(position).orElseThrow(IllegalArgumentException::new);
    }

    protected Piece(final Piece copyFrom) {
        this.owner = copyFrom.owner;
        this.position = copyFrom.position;
        this.totalMoved = copyFrom.totalMoved;
    }

    public Stream<Iterator<Position>> getIteratorsOfPossibleDestinations() {
        return Stream.empty();
    }

    protected Iterator<Position> proceedUntilBlocked(final Direction dir) {
        return new Iterator<Position>() {
            private Position current = position;

            @Override
            public boolean hasNext() {
                return current.testForward(dir);
            }

            @Override
            public Position next() {
                current = current.moveForward(dir);
                return current;
            }
        };
    }

    protected Iterator<Position> proceedSingleStep(final Direction dir) {
        return new Iterator<Position>() {
            boolean hasNotYielded = true;

            @Override
            public boolean hasNext() {
                return hasNotYielded && position.testForward(dir);
            }

            @Override
            public Position next() {
                hasNotYielded = false;
                return position.moveForward(dir);
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

    public Player team() {
        return this.owner;
    }

    public boolean hasNotMoved() {
        return this.totalMoved == 0;
    }

    public abstract double getScore();

    public int get1DCoord() {
        return this.position.get1DCoord();
    }

    public Position position() {
        return this.position;
    }

    public Coordinate x() {
        return this.position.x();
    }

    public Coordinate y() {
        return this.position.y();
    }

    @Override
    public int compareTo(Piece rhs) {
        return this.position.get1DCoord() - rhs.position.get1DCoord();
    }
}