package domain.piece.sliding;

import domain.piece.Direction;
import domain.piece.Directions;
import domain.piece.PieceInfo;
import domain.piece.TeamColor;

public class Queen extends SlidingPiece {

    private static final Directions DIRECTIONS = Directions.Every;

    public Queen(TeamColor teamColor) {
        super(teamColor, PieceInfo.QUEEN);
    }

    @Override
    protected Direction findDirection(Direction direction) {
        return DIRECTIONS.findDirection(direction);
    }
}
