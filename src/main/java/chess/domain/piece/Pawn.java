package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Coordinate;

public class Pawn extends Piece {
    private static final String NAME = "P";
    private static final double SCORE = 1;

    public Pawn(TeamType teamType) {
        super(NAME, teamType, SCORE, Direction.getPawnDirections(teamType));
    }

    @Override
    public boolean isMovable(ChessBoard chessBoard, Coordinate current, Coordinate destination) {
        Direction direction = current.evaluateDirection(destination);
        if (!isCorrectDirection(direction)) {
            return false;
        }
        if (!direction.isDiagonal()) {
            return isMovableForward(chessBoard, current, destination);
        }
        Coordinate nextCoordinate = current.move(direction);
        return isMovableDiagonal(chessBoard, nextCoordinate, destination);
    }

    private boolean isMovableForward(ChessBoard chessBoard, Coordinate current, Coordinate destination) {
        if (destination.isTwoRankForwardFrom(current)) {
            return isMovableTwoRankForward(chessBoard, current, destination);
        }
        Direction direction = current.evaluateDirection(destination);
        Coordinate nextCoordinate = current.move(direction);
        return nextCoordinate.equals(destination) && chessBoard.isEmptyOn(destination);
    }

    private boolean isMovableTwoRankForward(ChessBoard chessBoard, Coordinate current, Coordinate destination) {
        if (!current.isDefaultPawnRank(getTeamType())) {
            return false;
        }
        Direction direction = current.evaluateDirection(destination);
        boolean hasPieceOnRouteBeforeDestination = chessBoard.hasPieceOnRouteBeforeDestination(current, destination, direction);
        return !hasPieceOnRouteBeforeDestination && chessBoard.isEmptyOn(destination);
    }

    private boolean isMovableDiagonal(ChessBoard chessBoard, Coordinate nextCoordinate, Coordinate destination) {
        return nextCoordinate.equals(destination) && chessBoard.hasEnemyOn(destination, getTeamType());
    }
}
