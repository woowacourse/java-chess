package model.piece;

import model.board.Direction;
import model.game.Player;
import model.board.Position;

import java.util.*;
import java.util.stream.Stream;

public class Pawn extends Piece {
    private static final double SCORE = 1.0;

    public Pawn(Player player, Position position) {
        super(player, position);
    }

    public Stream<Position> possibleDiagonalPositions() {
        return Stream.of(forward().rotateClockwise(1), forward().rotateCounterClockwise(1))
                    .map(position::tryToMoveForward)
                    .flatMap(x -> x.map(Stream::of).orElseGet(Stream::empty));
    }

    public Stream<Position> possibleForwardPositions() {
        Stream<Optional<Position>> result = Stream.of(position.tryToMoveForward(forward()));
        if (hasNotMoved()) {
            result = Stream.concat(result, Stream.of(position.tryToMoveForward(forward(), 2)));
        }
        return result.flatMap(x -> x.map(Stream::of).orElseGet(Stream::empty));
    }

    private Direction forward() {
        return (owner == Player.WHITE) ? Direction.NORTH : Direction.SOUTH;
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