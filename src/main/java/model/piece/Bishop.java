package model.piece;

import model.board.Direction;
import model.board.Position;
import model.game.Player;

import java.util.Iterator;
import java.util.stream.Stream;

public class Bishop extends Piece {
    private static final double SCORE = 3.0;

    public Bishop(final Player player, final Position position) {
        super(player, position);
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
        return SCORE;
    }

    @Override
    public String toString() {
        return (this.owner == Player.BLACK) ? "♝" : "♗" ;
    }
}