package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;

public class Knight extends Piece {
    private static final String NAME = "N";
    private static final double SCORE = 2.5;

    public Knight(TeamType teamType) {
        super(teamType, NAME, SCORE, Direction.getKnightDirections());
    }

    @Override
    public boolean isMovableTo(Board board, Coordinate currentCoordinate,
        Coordinate targetCoordinate) {
        Direction moveCommandDirection = currentCoordinate.calculateDirection(targetCoordinate);
        if (!isCorrectDirection(moveCommandDirection)) {
            return false;
        }
        Coordinate nextCoordinate = currentCoordinate.move(moveCommandDirection);
        return nextCoordinate.equals(targetCoordinate) && board
            .isMovable(nextCoordinate, getTeamType());
    }
}
