package model.piece;

import model.board.Direction;
import model.board.Position;
import model.game.Color;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class King extends Piece {
    public King(final Color owner, final Position position) {
        super(owner, position);
    }

    @Override
    public Stream<Iterator<Position>> getIteratorsOfPossibleDestinations() {
        List<Iterator<Position>> candidates = Direction.every()
                                                        .map(this::proceedSingleStep)
                                                        .collect(Collectors.toList());

        return candidates.stream();
    }

    private Iterator<Position> proceedSingleStep(final Direction dir) {
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

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double getScore() {
        return Score.KING.val();
    }

    @Override
    public String toString() {
        return (this.owner == Color.BLACK) ? "♚" : "♔";
    }
}