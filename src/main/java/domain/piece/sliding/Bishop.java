package domain.piece.sliding;

import domain.piece.Direction;
import domain.piece.Directions;
import domain.piece.PieceType;
import domain.piece.TeamColor;

public class Bishop extends SlidingPiece {

    private static final Directions DIRECTIONS = Directions.Diagonal;

    public Bishop(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    protected Direction findDirection(Direction direction) {
        return DIRECTIONS.findDirection(direction);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.BISHOP;
    }
}
