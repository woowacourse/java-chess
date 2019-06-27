package model.piece;

import model.board.Direction;
import model.game.Color;
import model.board.Position;

import java.util.Iterator;
import java.util.stream.Stream;

public class Rook extends Piece {
    public Rook(final Color owner, final Position position) {
        super(owner, position);
    }

    public Rook(final Piece copyFrom) {
        super(copyFrom);
    }

    @Override
    public Stream<Iterator<Position>> getIteratorsOfPossibleDestinations() {
        return Direction.orthogonal()
                        .map(super::proceedUntilBlocked);
    }

    @Override
    public double getScore() {
        return Score.ROOK.val();
    }

    @Override
    public String toString() {
        return (this.owner == Color.BLACK) ? "♜" : "♖";
    }
}