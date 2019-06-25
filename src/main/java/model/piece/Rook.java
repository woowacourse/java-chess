package model.piece;

import model.board.Direction;
import model.game.Player;
import model.board.Position;

import java.util.Iterator;
import java.util.stream.Stream;

public class Rook extends Piece {
    private static final double SCORE = 5.0;

    public Rook(Player player, Position position) {
        super(player, position);
    }

    public Rook(Piece copyFrom) {
        super(copyFrom);
    }

    @Override
    public Stream<Iterator<Position>> findPossiblePositions() {
        return Direction.orthogonal()
                        .map(super::proceedUntilBlocked);
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String toString() {
        return (owner == Player.BLACK) ? "♜" : "♖";
    }
}