package chess.domain.piece;

import chess.controller.direction.Direction;
import chess.domain.board.position.Position;

import java.util.Arrays;
import java.util.List;

public class Knight extends Piece{

    private static final List<Direction> DIRECTIONS = Arrays.asList(
            Direction.KNIGHT_DOWN_LEFT,
            Direction.KNIGHT_DOWN_RIGHT,
            Direction.KNIGHT_LEFT_DOWN,
            Direction.KNIGHT_LEFT_UP,
            Direction.KNIGHT_UP_LEFT,
            Direction.KNIGHT_UP_RIGHT,
            Direction.KNIGHT_RIGHT_DOWN,
            Direction.KNIGHT_RIGHT_UP
    );

    private static final int ABLE_DISTANCE_TO_MOVE = 1;

    public Knight(Owner owner) {
        super(owner);
    }

    @Override
    public void validateMove(Position source, Position target, Piece targetPiece) {
        int horizontalDifferent = source.getHorizontalDistance(target);
        int verticalDifferent = source.getVerticalDistance(target);

        if(!((horizontalDifferent == 2 && verticalDifferent == 1)
                || (horizontalDifferent ==1 && verticalDifferent ==2))){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "N";
    }

    @Override
    public List<Direction> getDirections() {
        return DIRECTIONS;
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}
