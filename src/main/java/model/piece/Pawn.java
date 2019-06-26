package model.piece;

import model.board.Direction;
import model.board.Position;
import model.game.Player;

import java.util.Iterator;
import java.util.stream.Stream;

public class Pawn extends Piece {
    private static final double SCORE = 1.0;

    public Pawn(final Player player, final Position position) {
        super(player, position);
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
        return (this.owner == Player.WHITE) ? Direction.NORTH : Direction.SOUTH;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    public double getHalfScore() {
        return SCORE * .5;
    }

    @Override
    public String toString() {
        return (owner == Player.BLACK) ? "♟" : "♙";
    }
}