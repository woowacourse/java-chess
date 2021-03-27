package chess.db.domain.board;

import static chess.domain.piece.type.PieceType.KING;

import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.MoveRouteForDB;
import chess.db.domain.position.PositionEntity;
import chess.domain.player.type.TeamColor;
import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardForDB {
    private static final int NUMBER_OF_ALL_KINGS = 2;

    private final Map<PositionEntity, CellForDB> cellsForDB = new HashMap<>();

    public void setPiece(PositionEntity positionEntity, PieceEntity pieceEntity) {
        CellForDB cellForDB = new CellForDB(pieceEntity);
        cellsForDB.put(positionEntity, cellForDB);

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

    public void validateRoute(MoveRouteForDB moveRouteForDB, TeamColor teamColor) {
        CellForDB startPositionCell = cellsForDB.get(moveRouteForDB.getStartPosition());
        validateOwnPiece(startPositionCell, teamColor);
        validateMoveRoute(startPositionCell, moveRouteForDB);
    }

    private void validateOwnPiece(CellForDB startPositionCell, TeamColor teamColor) {
        if (startPositionCell.isEmpty()) {
            throw new IllegalArgumentException("출발 위치에 기물이 존재하지 않습니다.");
        }
        if (startPositionCell.teamColor() != teamColor) {
            throw new IllegalArgumentException("자신의 기물이 아닙니다.");
        }
    }

    private void validateMoveRoute(CellForDB startPositionCell, MoveRouteForDB moveRouteForDB) {
        if (!startPositionCell.canMoveTo(moveRouteForDB, this)) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
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

    public List<String> status() {
        List<String> cellsStatus = new ArrayList<>();
        List<Rank> reversedRanks = Rank.reversedRanks();
        for (Rank rank : reversedRanks) {
            addCellsStatusOnRank(cellsStatus, rank);
        }
        return cellsStatus;
    }

    private void addCellsStatusOnRank(List<String> cellsStatus, Rank rank) {
        for (File file : File.values()) {
            CellForDB cell = cellsForDB.get(PositionEntity.of(file, rank));
            cellsStatus.add(cell.status());
        }
    }
}

