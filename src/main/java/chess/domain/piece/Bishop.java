package chess.domain.piece;

import static chess.domain.board.Direction.LEFT_DOWN;
import static chess.domain.board.Direction.LEFT_UP;
import static chess.domain.board.Direction.RIGHT_DOWN;
import static chess.domain.board.Direction.RIGHT_UP;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;
import chess.domain.player.TeamType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bishop extends Piece {
    private static final String NAME = "B";

    public Bishop(TeamType teamType) {
        super(teamType, NAME, Arrays.asList(LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN));
    }

    @Override
    public boolean isMovableTo(Board board, Coordinate currentCoordinate, Coordinate targetCoordinate) {
        Direction moveCommandDirection = currentCoordinate.calculateDirection(targetCoordinate);
        List<Coordinate> possibleCoordinates = new ArrayList<>();
        List<Direction> directions = getDirections();
        if (!directions.contains(moveCommandDirection)) {
            return false;
        }
        Coordinate movingCoordinate = currentCoordinate.move(moveCommandDirection);
        while (!movingCoordinate.equals(targetCoordinate)) {
            Piece piece = board.find(movingCoordinate);
            if (piece != null) {
                break;
            }
            possibleCoordinates.add(movingCoordinate);
            movingCoordinate = movingCoordinate.move(moveCommandDirection);
        }
        Piece piece = board.find(movingCoordinate);
        if (piece == null || !piece.isTeamOf(this.getTeamType())) {
            possibleCoordinates.add(movingCoordinate);
        }
        return possibleCoordinates.contains(targetCoordinate);
    }
}
