package model.piece;

import model.board.Direction;
import model.game.Color;
import model.board.Position;

import java.util.Iterator;
import java.util.stream.Stream;

public class Queen extends Piece {
    public Queen(final Color owner, final Position position) {
        super(owner, position);
    }

    public Queen(final Piece copyFrom) {
        super(copyFrom);
    }

    @Override
    public Stream<Iterator<Position>> getIteratorsOfPossibleDestinations() {
        return Direction.every()
                        .map(super::proceedUntilBlocked);
    }

    @Override
    public double getScore() {
        return Score.QUEEN.val();
    }

    @Override
    public String toString() {
        return (this.owner == Color.BLACK) ? "♛" : "♕";
    }
}