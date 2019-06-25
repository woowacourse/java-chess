package model.piece;

import model.board.Direction;
import model.game.Player;
import model.board.Position;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class King extends Piece {
    private static final double SCORE = .0;

    public King(Player player, Position position) {
        super(player, position);
    }

    @Override
    public Stream<Iterator<Position>> findPossiblePositions() {
        List<Iterator<Position>> candidates = Stream.of(Direction.values())
                                                    .map(super::proceedOnlyOneStep)
                                                    .collect(Collectors.toList());

//        if (hasNotMoved()) {
//            candidates.add((owner == Player.WHITE) ? yieldSinglePositionOf(Position.of("b1")) : yieldSinglePositionOf(Position.of("b8")));
//            candidates.add((owner == Player.WHITE) ? yieldSinglePositionOf(Position.of("g1")) : yieldSinglePositionOf(Position.of("g8")));
//        }
        return candidates.stream();
    }

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
        return (owner == Player.BLACK) ? "♚" : "♔";
    }
}