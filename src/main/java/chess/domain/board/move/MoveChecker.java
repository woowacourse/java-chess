package chess.domain.board.move;

import static chess.domain.piece.type.PieceType.BISHOP;
import static chess.domain.piece.type.PieceType.KING;
import static chess.domain.piece.type.PieceType.KNIGHT;
import static chess.domain.piece.type.PieceType.QUEEN;
import static chess.domain.piece.type.PieceType.ROOK;

import chess.domain.board.Cell;
import chess.domain.piece.Piece;
import chess.domain.piece.type.Direction;
import chess.domain.piece.type.PieceType;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoveChecker {
    private static final String CANNOT_MOVE_TO_DESTINATION_ERROR_MESSAGE = "이동할 수 없는 도착위치 입니다.";

    public void validateMoving(MoveRequest moveRequest, Map<Position, Cell> cells) {
        PieceType pieceTypeToMove = getPieceTypeToMove(moveRequest, cells);
        if (isPieceTypeMoveByDefaultStrategy(pieceTypeToMove)) {
            validateDefaultMoving(moveRequest, cells);
            return;
        }
        if (isPieceTypeMoveBySpecialStrategy(pieceTypeToMove)) {
            validateMovingByKnightMoveStrategy(moveRequest, cells);
            return;
        }
        validateMovingByPawnMoveStrategy(moveRequest, cells);
    }

    private PieceType getPieceTypeToMove(MoveRequest moveRequest, Map<Position, Cell> cells) {
        Piece startPositionPieceToMove = getStartPositionPiece(cells, moveRequest);
        return startPositionPieceToMove.getPieceType();
    }

    private boolean isPieceTypeMoveByDefaultStrategy(PieceType pieceTypeToMove) {
        return pieceTypeToMove == ROOK
            || pieceTypeToMove == BISHOP
            || pieceTypeToMove == QUEEN;
    }

    private boolean isPieceTypeMoveBySpecialStrategy(PieceType pieceTypeToMove) {
        return pieceTypeToMove == KNIGHT || pieceTypeToMove == KING;
    }

    private void validateDefaultMoving(MoveRequest moveRequest, Map<Position, Cell> cells) {
        Piece startPositionPieceToMove = getStartPositionPiece(cells, moveRequest);
        if (isNotCorrectDirection(moveRequest, startPositionPieceToMove)
            || isAnyPieceExistsOnRouteBeforeDestination(cells, moveRequest)) {
            throw new IllegalArgumentException(CANNOT_MOVE_TO_DESTINATION_ERROR_MESSAGE);
        }
        if (isOwnPieceExistsInDestination(cells, moveRequest)) {
            throw new IllegalArgumentException(CANNOT_MOVE_TO_DESTINATION_ERROR_MESSAGE);
        }
    }

    private void validateMovingByPawnMoveStrategy(MoveRequest moveRequest, Map<Position, Cell> cells) {
        Piece startPositionPieceToMove = getStartPositionPiece(cells, moveRequest);
        if (isNotCorrectDirection(moveRequest, startPositionPieceToMove)) {
            throw new IllegalArgumentException(CANNOT_MOVE_TO_DESTINATION_ERROR_MESSAGE);
        }
        Direction moveRequestDirection = moveRequest.getDirection();
        if (moveRequestDirection.isForward()) {
            validateMovingForward(moveRequest, cells);
            return;
        }
        validateMovingDiagonal(moveRequest, cells);
    }

    private void validateMovingForward(MoveRequest moveRequest, Map<Position, Cell> cells) {
        if (moveRequest.isRankForwardedBy(1)) {
            validateMovingOneRankForward(moveRequest, cells);
            return;
        }
        if (moveRequest.isRankForwardedBy(2)) {
            validateMovingTwoRankForward(moveRequest, cells);
            return;
        }
        throw new IllegalArgumentException(CANNOT_MOVE_TO_DESTINATION_ERROR_MESSAGE);
    }

    private void validateMovingOneRankForward(MoveRequest moveRequest, Map<Position, Cell> cells) {
        if (isAnyPieceExistsAtDestination(cells, moveRequest.getDestination())) {
            throw new IllegalArgumentException(CANNOT_MOVE_TO_DESTINATION_ERROR_MESSAGE);
        }
    }

    private void validateMovingTwoRankForward(MoveRequest moveRequest, Map<Position, Cell> cells) {
        if (!moveRequest.isStartPositionFirstPawnPosition()) {
            throw new IllegalArgumentException(CANNOT_MOVE_TO_DESTINATION_ERROR_MESSAGE);
        }
        if (isAnyPieceExistsOnRouteBeforeDestination(cells, moveRequest)
            || isAnyPieceExistsAtDestination(cells, moveRequest.getDestination())) {
            throw new IllegalArgumentException(CANNOT_MOVE_TO_DESTINATION_ERROR_MESSAGE);
        }
    }

    private void validateMovingDiagonal(MoveRequest moveRequest, Map<Position, Cell> cells) {
        Position nextPosition = moveRequest.getNextPositionOfStartPosition();
        if (!(isEnemyExistsAtDestination(cells, moveRequest)
            && nextPosition.equals(moveRequest.getDestination()))) {
            throw new IllegalArgumentException(CANNOT_MOVE_TO_DESTINATION_ERROR_MESSAGE);
        }
    }

    private void validateMovingByKnightMoveStrategy(MoveRequest moveRequest, Map<Position, Cell> cells) {
        Piece startPositionKnightToMove = getStartPositionPiece(cells, moveRequest);
        if (isNotCorrectDirection(moveRequest, startPositionKnightToMove)) {
            throw new IllegalArgumentException(CANNOT_MOVE_TO_DESTINATION_ERROR_MESSAGE);
        }
        Position nextPosition = moveRequest.getNextPositionOfStartPosition();
        if (!(moveRequest.isDestination(nextPosition)
            && !isOwnPieceExistsInDestination(cells, moveRequest))) {
            throw new IllegalArgumentException(CANNOT_MOVE_TO_DESTINATION_ERROR_MESSAGE);
        }
    }

    private boolean isNotCorrectDirection(MoveRequest moveRequest, Piece startPositionPieceToMove) {
        return !startPositionPieceToMove.isCorrectMoveDirection(moveRequest.getDirection());
    }

    private Piece getStartPositionPiece(Map<Position, Cell> cells, MoveRequest moveRequest) {
        Cell startPositionCell = cells.get(moveRequest.getStartPosition());
        return startPositionCell.getPieceEntity();
    }

    public boolean isOwnPieceExistsInDestination(Map<Position, Cell> cells, MoveRequest moveRequest) {
        Cell destinationCell = findCell(cells, moveRequest.getDestination());
        if (destinationCell.isEmpty()) {
            return false;
        }
        return destinationCell.getTeamColor() == moveRequest.getCurrentTurnTeamColor();
    }

    private Cell findCell(Map<Position, Cell> cells, Position position) {
        return cells.get(position);
    }

    public boolean isAnyPieceExistsOnRouteBeforeDestination(Map<Position, Cell> cells, MoveRequest moveRequest) {
        Position movingPosition = moveRequest.getNextPositionOfStartPosition();
        List<Position> canMovePositions = new ArrayList<>();
        while (!movingPosition.equals(moveRequest.getDestination())) {
            canMovePositions.add(movingPosition);
            movingPosition = movingPosition.moveTo(moveRequest.getDirection());
        }
        return !canMovePositions.stream()
            .map(cells::get)
            .allMatch(Cell::isEmpty);
    }

    public boolean isAnyPieceExistsAtDestination(Map<Position, Cell> cells, Position destination) {
        Cell destinationCell = findCell(cells, destination);
        return !destinationCell.isEmpty();
    }

    public boolean isEnemyExistsAtDestination(Map<Position, Cell> cells, MoveRequest moveRequest) {
        Cell destinationCell = findCell(cells, moveRequest.getDestination());
        return !destinationCell.isEmpty()
            && destinationCell.getTeamColor() != moveRequest.getCurrentTurnTeamColor();
    }
}
