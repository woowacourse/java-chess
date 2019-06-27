package model.piece;

import model.board.Position;
import model.game.Color;

import java.util.Iterator;
import java.util.stream.Stream;

public class Knight extends Piece {
    public Knight(final Color owner, final Position position) {
        super(owner, position);
    }

    public Knight(final Piece copyFrom) {
        super(copyFrom);
    }

    @Override
    public Stream<Iterator<Position>> getIteratorsOfPossibleDestinations() {
        return Stream.of(
                this.position.move(-1, 2),
                this.position.move(-1, -2),
                this.position.move(-2, -1),
                this.position.move(-2, 1),
                this.position.move(1, -2),
                this.position.move(1, 2),
                this.position.move(2, 1),
                this.position.move(2, -1)
        ).flatMap(x -> x.map(Stream::of).orElseGet(Stream::empty))
        .map(this::jumpTo);
    }

    private Iterator<Position> jumpTo(final Position dest) {
        return new Iterator<Position>() {
            boolean hasYielded = false;

            @Override
            public boolean hasNext() {
                return !hasYielded;
            }

            @Override
            public Position next() {
                hasYielded = true;
                return dest;
            }
        };
    }

    @Override
    public double getScore() {
        return Score.KNIGHT.val();
    }

    @Override
    public String toString() {
        return (this.owner == Color.BLACK) ? "♞" : "♘";
    }
}