package domain.piece.sliding;

import domain.piece.Direction;
import domain.piece.Directions;
import domain.piece.PieceType;
import domain.piece.TeamColor;

public class Queen extends SlidingPiece {

    private static final Directions DIRECTIONS = Directions.Every;

    public Queen(TeamColor teamColor) {
        super(teamColor);
    }

    @Override
    protected Direction findDirection(Direction direction) {
        return DIRECTIONS.findDirection(direction);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.QUEEN;
    }
}
