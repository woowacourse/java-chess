package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;
import chess.domain.player.TeamType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends Piece {
    private static final String NAME = "N";

    public Knight(TeamType teamType) {
        super(teamType, NAME, Arrays.asList(Direction.LEFT_LEFT_DOWN, Direction.LEFT_LEFT_UP, Direction.LEFT_UP_UP, Direction.LEFT_DOWN_DOWN, Direction.RIGHT_DOWN_DOWN, Direction.RIGHT_UP_UP, Direction.RIGHT_RIGHT_UP, Direction.RIGHT_RIGHT_DOWN));
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
        Piece piece = board.find(movingCoordinate);
        if (piece == null || !piece.isTeamOf(this.getTeamType())) {
            possibleCoordinates.add(movingCoordinate);
        }
        return possibleCoordinates.contains(targetCoordinate);
    }
}
