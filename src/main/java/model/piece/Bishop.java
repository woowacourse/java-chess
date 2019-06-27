package model.piece;

import model.board.Direction;
import model.board.Position;
import model.game.Color;

import java.util.Iterator;
import java.util.stream.Stream;

public class Bishop extends Piece {
    public Bishop(final Color owner, final Position position) {
        super(owner, position);
    }

    public Bishop(final Piece copyFrom) {
        super(copyFrom);
    }

    @Override
    public Stream<Iterator<Position>> getIteratorsOfPossibleDestinations() {
        return Direction.diagonal()
                        .map(super::proceedUntilBlocked);
    }

    @Override
    public double getScore() {
        return Score.BISHOP.val();
    }

    @Override
    public String toString() {
        return (this.owner == Color.BLACK) ? "♝" : "♗" ;
    }
}