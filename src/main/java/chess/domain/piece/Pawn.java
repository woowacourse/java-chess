package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;
import chess.domain.player.TeamType;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private static final String NAME = "P";
    private static final double SCORE = 1;

    public Pawn(TeamType teamType) {
        super(teamType, NAME, SCORE, Direction.getPawnDirections(teamType));
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
        if (currentCoordinate.isTwoRankForward(targetCoordinate)) {
            if (!currentCoordinate.isFirstPawnRank(getTeamType())) {
                return false;
            }
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
        if (moveCommandDirection.isDiagonal()) {
            if (piece != null && !piece.isTeamOf(this.getTeamType())) {
                possibleCoordinates.add(movingCoordinate);
            }
        }
        if (!moveCommandDirection.isDiagonal()) {
            if (piece == null || !piece.isTeamOf(this.getTeamType())) {
                possibleCoordinates.add(movingCoordinate);
            }
        }
        return possibleCoordinates.contains(targetCoordinate);
    }
}
