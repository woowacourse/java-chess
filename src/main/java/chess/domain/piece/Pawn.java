package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;

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
        if (!isCorrectDirection(moveCommandDirection)) {
            return false;
        }
        if (!moveCommandDirection.isDiagonal()) {
            return isCanMoveForward(board, currentCoordinate, targetCoordinate);
        }
        Coordinate nextCoordinate = currentCoordinate.move(moveCommandDirection);
        return isCanMoveDiagonal(board, targetCoordinate, nextCoordinate);
    }

    private boolean isCanMoveForward(Board board, Coordinate currentCoordinate,
        Coordinate targetCoordinate) {

        if (currentCoordinate.isTwoRankForward(targetCoordinate)) {
            return isCanMoveTwoRankForward(board, currentCoordinate, targetCoordinate);
        }
        return board.find(targetCoordinate).isEmpty();
    }

    private boolean isCanMoveTwoRankForward(Board board,
        Coordinate currentCoordinate, Coordinate targetCoordinate) {
        if (!currentCoordinate.isFirstPawnRank(getTeamType())) {
            return false;
        }
        Direction direction = currentCoordinate.calculateDirection(targetCoordinate);
        boolean hasPieceOnRoute = board
            .hasPieceOnRouteBeforeDestination(currentCoordinate, targetCoordinate, direction);
        return !hasPieceOnRoute && board.find(targetCoordinate).isEmpty();
    }

    private boolean isCanMoveDiagonal(Board board, Coordinate targetCoordinate,
        Coordinate nextCoordinate) {
        return nextCoordinate.equals(targetCoordinate) &&
            board.find(targetCoordinate).hasEnemy(getTeamType());
    }
}
