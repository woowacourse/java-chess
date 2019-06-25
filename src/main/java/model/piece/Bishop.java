package model.piece;

import model.board.Direction;
import model.game.Player;
import model.board.Position;

import java.util.Iterator;
import java.util.stream.Stream;

public class Bishop extends Piece {
    private static final double SCORE = 3.0;

    public Bishop(Player player, Position position) {
        super(player, position);
    }

    public Bishop(Piece copyFrom) {
        super(copyFrom);
    }

    @Override
    public Stream<Iterator<Position>> findPossiblePositions() {
        return Direction.diagonal()
                        .map(super::proceedUntilBlocked);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String toString() {
        return (owner == Player.BLACK) ? "♝" : "♗" ;
    }
}