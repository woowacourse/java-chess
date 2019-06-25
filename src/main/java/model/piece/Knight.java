package model.piece;

import model.game.Player;
import model.board.Position;

import java.util.*;
import java.util.stream.Stream;

public class Knight extends Piece {
    private static final double SCORE = 2.5;

    public Knight(Player player, Position position) {
        super(player, position);
    }

    public Knight(Piece copyFrom) {
        super(copyFrom);
    }

    @Override
    public Stream<Iterator<Position>> findPossiblePositions() {
        return Stream.of(
                position.move(-1, 2),
                position.move(-1, -2),
                position.move(-2, -1),
                position.move(-2, 1),
                position.move(1, -2),
                position.move(1, 2),
                position.move(2, 1),
                position.move(2, -1)
        ).flatMap(x -> x.map(Stream::of).orElseGet(Stream::empty))
        .map(this::jumpTo);
    }

    private Iterator<Position> jumpTo(Position dest) {
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
        return SCORE;
    }

    @Override
    public String toString() {
        return (owner == Player.BLACK) ? "♞" : "♘";
    }
}