package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Cell;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;
import java.util.ArrayList;
import java.util.List;

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
        List<Coordinate> possibleCoordinates = new ArrayList<>();
        if (board.isMovable(nextCoordinate, getTeamType())) {
            possibleCoordinates.add(nextCoordinate);
        }
        return possibleCoordinates.contains(targetCoordinate);
    }
}
