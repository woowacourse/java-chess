package model.piece;

import model.board.Direction;
import model.board.Position;
import model.game.Color;

import java.util.Iterator;
import java.util.stream.Stream;

public class Pawn extends Piece {
    public Pawn(final Color owner, final Position position) {
        super(owner, position);
    }

    public Stream<Iterator<Position>> getIteratorsOfPossibleDestinations() {
        return Stream.of(new Iterator<Position>() {
            Position current = position;
            int count = 0;

            @Override
            public boolean hasNext() {
                return position.testForward(forward()) && (hasNotMoved()) ? count < 2 : count < 1;
            }

            @Override
            public Position next() {
                count++;
                return current = current.moveForward(forward());
            }
        });
    }

    public Stream<Position> possibleDiagonalDestinations() {
        return Stream.of(forward().rotateClockwise(1), forward().rotateCounterClockwise(1))
                    .map(this.position::moveForwardSafe)
                    .flatMap(x -> x.map(Stream::of).orElseGet(Stream::empty));
    }

    private Direction forward() {
        return (this.owner == Color.WHITE) ? Direction.NORTH : Direction.SOUTH;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double getScore() {
        return Score.PAWN.val();
    }

    public double getHalfScore() {
        return Score.PAWN_HALF.val();
    }

    @Override
    public String toString() {
        return (owner == Color.BLACK) ? "♟" : "♙";
    }
}