package chess.domain.piece;

import chess.controller.direction.Direction;
import chess.domain.board.position.Position;

import java.util.Arrays;
import java.util.List;

public class Queen extends Piece{
    private static final List<Direction> DIRECTIONS = Arrays.asList(
            Direction.LEFT,
            Direction.DOWN,
            Direction.UP,
            Direction.RIGHT,
            Direction.DOWN_LEFT,
            Direction.DOWN_RIGHT,
            Direction.UP_LEFT,
            Direction.UP_RIGHT
    );

    private static final int ABLE_DISTANCE_TO_MOVE = 7;

    public Queen(Owner owner) {
        super(owner);
    }

    @Override
    public void validateMove(Position source, Position target, Piece targetPiece) {
        if(!(source.isStraight(target) || source.isDiagonal(target))){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "Q";
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
