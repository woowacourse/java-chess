package chess.db.domain.board;

import static chess.beforedb.domain.piece.type.PieceType.KING;

import chess.beforedb.domain.player.type.TeamColor;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.MoveRequestForDB;
import chess.db.domain.position.MoveRouteForDB;
import chess.db.domain.position.PositionEntity;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardForDB {
    private static final int NUMBER_OF_ALL_KINGS = 2;

    private final PiecesPositionsForDB piecesPositionsForDB;
    private final MovableChecker movableChecker;
    private final Map<PositionEntity, CellForDB> cellsForDB = new HashMap<>();

    public BoardForDB() {
        piecesPositionsForDB = new PiecesPositionsForDB();
        movableChecker = new MovableChecker();
    }

    public void validateRoute(Long gameId, MoveRequestForDB moveRequestForDB) throws SQLException {
        Map<PositionEntity, CellForDB> cells = new HashMap<>();
        piecesPositionsForDB.setCellsStatusByGameId(gameId, cells);

        CellForDB startPositionCell = cells.get(moveRequestForDB.getStartPosition());

        validateOwnPiece(startPositionCell, moveRequestForDB.getCurrentTurnTeamColor());
        validateMoveRoute(cells, moveRequestForDB);
    }

    public List<String> getStatus(Long gameId) throws SQLException {
        return piecesPositionsForDB.getCellsStatusByGameIdInOrderAsString(gameId);
    }

    public CellForDB findCell(PositionEntity positionEntity) {
        return cellsForDB.get(positionEntity);
    }

    public PieceEntity findPiece(PositionEntity positionEntity) {
        return findCell(positionEntity).getPieceEntity();
    }

    public void move(MoveRouteForDB moveRouteForDB) {
        CellForDB startPositionCell = cellsForDB.get(moveRouteForDB.getStartPosition());
        CellForDB destinationCell = cellsForDB.get(moveRouteForDB.getDestination());
        startPositionCell.movePieceTo(destinationCell);
    }

    private void validateOwnPiece(CellForDB startPositionCell, TeamColor teamColor) {
        if (startPositionCell.isEmpty()) {
            throw new IllegalArgumentException("출발 위치에 기물이 존재하지 않습니다.");
        }
        if (startPositionCell.teamColor() != teamColor) {
            throw new IllegalArgumentException("자신의 기물이 아닙니다.");
        }
    }

    private void validateMoveRoute(Map<PositionEntity, CellForDB> cells,
        MoveRequestForDB moveRequestForDB) {

        CellForDB startPositionCell = cells.get(moveRequestForDB.getStartPosition());
        PieceEntity startPositionPiece = startPositionCell.getPieceEntity();
        boolean canMove = startPositionPiece.canMoveTo(moveRequestForDB, this);
        if (!canMove) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
    }

    public boolean isAnyPieceExistsOnRouteBeforeDestination(MoveRequestForDB moveRequestForDB) {
        PositionEntity movingPosition = moveRequestForDB.getNextPositionOfStartPosition();
        List<PositionEntity> canMovePositions = new ArrayList<>();
        while (!movingPosition.equals(moveRequestForDB.getDestination())) {
            canMovePositions.add(movingPosition);
            movingPosition = movingPosition.move(moveRequestForDB.getDirection());
        }
        return canMovePositions.stream()
            .map(cellsForDB::get)
            .anyMatch(cell -> !cell.isEmpty());
    }

    public boolean isAnyPieceExistsOnRouteBeforeDestination(MoveRouteForDB moveRouteForDB) {
        PositionEntity movingPosition = moveRouteForDB.getNextPositionOfStartPosition();
        List<PositionEntity> canMovePositions = new ArrayList<>();
        while (!movingPosition.equals(moveRouteForDB.getDestination())) {
            canMovePositions.add(movingPosition);
            movingPosition = movingPosition.move(moveRouteForDB.getDirection());
        }
        return canMovePositions.stream()
            .map(cellsForDB::get)
            .anyMatch(cell -> !cell.isEmpty());
    }

    public boolean isAnyPieceExistsInCell(PositionEntity positionEntity) {
        CellForDB cell = findCell(positionEntity);
        return !cell.isEmpty();
    }

    public boolean isEnemyExists(PositionEntity positionEntity, TeamColor teamColor) {
        CellForDB cell = findCell(positionEntity);
        return !cell.isEmpty() && cell.teamColor() != teamColor;
    }

    public boolean isOwnPieceExistsInCell(PositionEntity positionEntity, TeamColor teamColor) {
        CellForDB cell = findCell(positionEntity);
        if (cell.isEmpty()) {
            return false;
        }
        return cell.teamColor() == teamColor;
    }

    public boolean isCellEmptyOrEnemyExists(PositionEntity positionEntity, TeamColor teamColor) {
        CellForDB cell = findCell(positionEntity);
        return cell.isEmpty() || cell.teamColor() != teamColor;
    }

    public boolean isKingDead() {
        return cellsForDB.values().stream()
            .filter(cell -> !cell.isEmpty() && cell.getPieceType() == KING)
            .count() < NUMBER_OF_ALL_KINGS;
    }

}

