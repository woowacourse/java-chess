package model.piece;

import model.board.Direction;
import model.game.Player;
import model.board.Position;

import java.util.Iterator;
import java.util.stream.Stream;

public class Queen extends Piece {
    private static final double SCORE = 9.0;

    public Queen(Player player, Position position) {
        super(player, position);
    }

    public Queen(Piece copyFrom) {
        super(copyFrom);
    }

    @Override
    public Stream<Iterator<Position>> findPossiblePositions() {
        return Stream.of(Direction.values())
                    .map(super::proceedUntilBlocked);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String toString() {
        return (owner == Player.BLACK) ? "♛" : "♕";
    }
}