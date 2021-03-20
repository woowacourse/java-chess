package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;
import chess.domain.player.TeamType;
import java.util.ArrayList;
import java.util.List;

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
