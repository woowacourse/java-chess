package model.piece;

import model.board.Direction;
import model.game.Player;
import model.board.Position;

import java.util.Iterator;
import java.util.stream.Stream;

public class Queen extends Piece {
    private static final double SCORE = 9.0;

    public Queen(final Player player, final Position position) {
        super(player, position);
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
        return SCORE;
    }

    @Override
    public String toString() {
        return (this.owner == Player.BLACK) ? "♛" : "♕";
    }
}