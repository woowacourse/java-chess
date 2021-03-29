package chess.db.domain.board;

import static chess.beforedb.domain.piece.type.PieceType.BISHOP;
import static chess.beforedb.domain.piece.type.PieceType.KING;
import static chess.beforedb.domain.piece.type.PieceType.KNIGHT;
import static chess.beforedb.domain.piece.type.PieceType.QUEEN;
import static chess.beforedb.domain.piece.type.PieceType.ROOK;

import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.piece.type.PieceType;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.MoveRequestForDB;
import chess.db.domain.position.PositionEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MoveChecker {

    public boolean canMove(MoveRequestForDB moveRequestForDB,
        Map<PositionEntity, CellForDB> cells) {
        PieceEntity startPositionPieceToMove = getStartPositionPiece(cells, moveRequestForDB);
        PieceType pieceTypeToMove = startPositionPieceToMove.getPieceType();
        if ((pieceTypeToMove == ROOK) || (pieceTypeToMove == BISHOP)
            || (pieceTypeToMove == QUEEN) || (pieceTypeToMove == KING)) {
            return canMoveByDefaultMoveStrategy(moveRequestForDB, cells);
        }
        if (pieceTypeToMove == KNIGHT) {
            return canByKnightMoveStrategy(moveRequestForDB, cells);
        }
        return canMoveByPawnMoveStrategy(moveRequestForDB, cells);
    }

    private boolean canMoveByDefaultMoveStrategy(MoveRequestForDB moveRequestForDB,
        Map<PositionEntity, CellForDB> cells) {

        PieceEntity startPositionPieceToMove = getStartPositionPiece(cells, moveRequestForDB);
        if (isNotCorrectDirection(moveRequestForDB, startPositionPieceToMove)
            || isAnyPieceExistsOnRouteBeforeDestination(cells, moveRequestForDB)) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        if (isOwnPieceExistsInDestination(cells, moveRequestForDB)) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        return true;
    }

    private boolean canMoveByPawnMoveStrategy(MoveRequestForDB moveRequestForDB,
        Map<PositionEntity, CellForDB> cells) {

        PieceEntity startPositionPieceToMove = getStartPositionPiece(cells, moveRequestForDB);
        if (isNotCorrectDirection(moveRequestForDB, startPositionPieceToMove)) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        Direction moveRequestDirection = moveRequestForDB.getDirection();
        if (moveRequestDirection.isForward()) {
            return canMoveForward(moveRequestForDB, cells);
        }
        return canMoveDiagonal(moveRequestForDB, cells);
    }

    private boolean canMoveForward(MoveRequestForDB moveRequestForDB,
        Map<PositionEntity, CellForDB> cells) {

        if (moveRequestForDB.isRankForwardedBy(1)) {
            return canMoveOneRankForward(moveRequestForDB, cells);
        }
        if (moveRequestForDB.isRankForwardedBy(2)) {
            return canMoveTwoRankForward(moveRequestForDB, cells);
        }
        throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
    }


    private boolean canMoveOneRankForward(MoveRequestForDB moveRequestForDB,
        Map<PositionEntity, CellForDB> cells) {
        if (isAnyPieceExistsAtDestination(cells, moveRequestForDB.getDestination())) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        return true;
    }

    private boolean canMoveTwoRankForward(MoveRequestForDB moveRequestForDB,
        Map<PositionEntity, CellForDB> cells) {

        if (!moveRequestForDB.isStartPositionFirstPawnPosition()) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        if (isAnyPieceExistsOnRouteBeforeDestination(cells, moveRequestForDB)
            || isAnyPieceExistsAtDestination(cells, moveRequestForDB.getDestination())) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        return true;
    }

    private boolean canMoveDiagonal(MoveRequestForDB moveRequestForDB,
        Map<PositionEntity, CellForDB> cells) {

        PositionEntity nextPosition = moveRequestForDB.getNextPositionOfStartPosition();
        if (!(isEnemyExistsAtDestination(cells, moveRequestForDB)
            && nextPosition.equals(moveRequestForDB.getDestination()))) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        return true;
    }


    private boolean canByKnightMoveStrategy(MoveRequestForDB moveRequestForDB,
        Map<PositionEntity, CellForDB> cells) {
        PieceEntity startPositionKnightToMove = getStartPositionPiece(cells, moveRequestForDB);
        if (isNotCorrectDirection(moveRequestForDB, startPositionKnightToMove)) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        PositionEntity nextPosition = moveRequestForDB.getNextPositionOfStartPosition();
        if (!(moveRequestForDB.isDestination(nextPosition)
            && !isOwnPieceExistsInDestination(cells, moveRequestForDB))) {
            throw new IllegalArgumentException("이동할 수 없는 도착위치 입니다.");
        }
        return true;
    }

    private boolean isNotCorrectDirection(MoveRequestForDB moveRequestForDB,
        PieceEntity startPositionPieceToMove) {
        return !startPositionPieceToMove.isCorrectMoveDirection(moveRequestForDB.getDirection());
    }

    private PieceEntity getStartPositionPiece(Map<PositionEntity, CellForDB> cells,
        MoveRequestForDB moveRequestForDB) {

        CellForDB startPositionCell = cells.get(moveRequestForDB.getStartPosition());
        return startPositionCell.getPieceEntity();
    }

    public boolean isOwnPieceExistsInDestination(Map<PositionEntity, CellForDB> cells,
        MoveRequestForDB moveRequestForDB) {

        CellForDB destinationCell = findCell(cells, moveRequestForDB.getDestination());
        if (destinationCell.isEmpty()) {
            return false;
        }
        return destinationCell.getTeamColor() == moveRequestForDB.getCurrentTurnTeamColor();
    }

    public boolean isAnyPieceExistsOnRouteBeforeDestination(Map<PositionEntity, CellForDB> cells,
        MoveRequestForDB moveRequestForDB) {

        PositionEntity movingPosition = moveRequestForDB.getNextPositionOfStartPosition();
        List<PositionEntity> canMovePositions = new ArrayList<>();
        while (!movingPosition.equals(moveRequestForDB.getDestination())) {
            canMovePositions.add(movingPosition);
            movingPosition = movingPosition.moveTo(moveRequestForDB.getDirection());
        }
        return !canMovePositions.stream()
            .map(cells::get)
            .allMatch(CellForDB::isEmpty);
    }

    private CellForDB findCell(Map<PositionEntity, CellForDB> cells,
        PositionEntity positionEntity) {
        return cells.get(positionEntity);
    }

    public boolean isAnyPieceExistsAtDestination(Map<PositionEntity, CellForDB> cells,
        PositionEntity destination) {
        CellForDB destinationCell = findCell(cells, destination);
        return !destinationCell.isEmpty();
    }

    public boolean isEnemyExistsAtDestination(Map<PositionEntity, CellForDB> cells,
        MoveRequestForDB moveRequestForDB) {
        CellForDB destinationCell = findCell(cells, moveRequestForDB.getDestination());
        return !destinationCell.isEmpty()
            && destinationCell.getTeamColor() != moveRequestForDB.getCurrentTurnTeamColor();
    }
}
