package model.piece;

import model.board.Coord;
import model.board.Direction;
import model.game.Player;
import model.board.Position;

import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class Piece implements Comparable<Piece> {
    final Player owner;
    Position position;
    private int totalMoved = 0;

    protected Piece(Player owner, Position position) {
        this.owner = Optional.ofNullable(owner).orElseThrow(IllegalArgumentException::new);
        this.position = Optional.ofNullable(position).orElseThrow(IllegalArgumentException::new);
    }

    protected Piece(Piece copyFrom) {
        this.owner = copyFrom.owner;
        this.position = copyFrom.position;
        this.totalMoved = copyFrom.totalMoved;
    }

    public Stream<Iterator<Position>> findPossiblePositions() {
        return Stream.empty();
    }

    protected Iterator<Position> proceedUntilBlocked(Direction dir) {
        return new Iterator<Position>() {
            private Position _position = position;

            @Override
            public boolean hasNext() {
                return _position.testForward(dir);
            }

            @Override
            public Position next() {
                _position = _position.moveForward(dir);
                return _position;
            }
        };
    }

    protected Iterator<Position> proceedOnlyOneStep(Direction dir) {
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

    public boolean move(Position to) {
        this.position = Optional.ofNullable(to).orElseThrow(IllegalArgumentException::new);
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

    public Coord x() {
        return this.position.x();
    }

    public Coord y() {
        return this.position.y();
    }

    @Override
    public int compareTo(Piece rhs) {
        return this.position.get1DCoord() - rhs.position.get1DCoord();
    }
}