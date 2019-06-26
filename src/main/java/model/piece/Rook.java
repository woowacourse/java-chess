package model.piece;

import model.board.Direction;
import model.game.Player;
import model.board.Position;

import java.util.Iterator;
import java.util.stream.Stream;

public class Rook extends Piece {
    private static final double SCORE = 5.0;

    public Rook(final Player player, final Position position) {
        super(player, position);
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
        return SCORE;
    }

    @Override
    public String toString() {
        return (this.owner == Player.BLACK) ? "♜" : "♖";
    }
}