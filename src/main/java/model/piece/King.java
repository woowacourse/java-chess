package model.piece;

import model.board.Direction;
import model.board.Position;
import model.game.Player;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class King extends Piece {
    private static final double SCORE = .0;

    public King(final Player player, final Position position) {
        super(player, position);
    }

    @Override
    public Stream<Iterator<Position>> getIteratorsOfPossibleDestinations() {
        List<Iterator<Position>> candidates = Direction.every()
                                                        .map(super::proceedSingleStep)
                                                        .collect(Collectors.toList());

//        if (hasNotMoved()) {
//            candidates.add((owner == Player.WHITE) ? yieldSinglePositionOf(Position.of("b1")) : yieldSinglePositionOf(Position.of("b8")));
//            candidates.add((owner == Player.WHITE) ? yieldSinglePositionOf(Position.of("g1")) : yieldSinglePositionOf(Position.of("g8")));
//        }
        return candidates.stream();
    }

    //// TODO: 2019-06-26 castling

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double getScore() {
        return SCORE;
    }

    @Override
    public String toString() {
        return (this.owner == Player.BLACK) ? "♚" : "♔";
    }
}