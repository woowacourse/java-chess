package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;

public class King extends Piece {
    private static final String NAME = "K";
    private static final double SCORE = 0;

    public King(TeamType teamType) {
        super(teamType, NAME, SCORE, Direction.getKingDirections());
    }

    @Override
    public boolean isMovableTo(Board board, Coordinate currentCoordinate,
        Coordinate targetCoordinate) {
        Direction moveCommandDirection = currentCoordinate.calculateDirection(targetCoordinate);
        if (!isCorrectDirection(moveCommandDirection)) {
            return false;
        }
        Coordinate nextCoordinate = currentCoordinate.move(moveCommandDirection);
        return nextCoordinate.equals(targetCoordinate) && board.find(targetCoordinate)
            .isMovable(getTeamType());
    }
}
