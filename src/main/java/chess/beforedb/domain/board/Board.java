package chess.beforedb.domain.board;

import static chess.beforedb.domain.piece.type.PieceType.KING;

import chess.beforedb.domain.piece.Piece;
import chess.beforedb.domain.player.type.TeamColor;
import chess.beforedb.domain.position.MoveRoute;
import chess.beforedb.domain.position.Position;
import chess.beforedb.domain.position.type.File;
import chess.beforedb.domain.position.type.Rank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private static final int NUMBER_OF_ALL_KINGS = 2;

    private final Map<Position, Cell> cells = new HashMap<>();

    public void setPiece(Position position, Piece piece) {
        Cell cell = new Cell(piece);
        cells.put(position, cell);
    }

    public Cell findCell(Position position) {
        return cells.get(position);
    }

    public Piece findPiece(Position position) {
        return findCell(position).getPiece();
    }

    public void move(MoveRoute moveRoute) {
        Cell startPositionCell = cells.get(moveRoute.startPosition());
        Cell destinationCell = cells.get(moveRoute.destination());
        startPositionCell.movePieceTo(destinationCell);
    }

    public void validateRoute(MoveRoute moveRoute, TeamColor teamColor) {
        Cell startPositionCell = cells.get(moveRoute.startPosition());
        validateOwnPiece(startPositionCell, teamColor);
        validateMoveRoute(startPositionCell, moveRoute);
    }

    private void validateOwnPiece(Cell startPositionCell, TeamColor teamColor) {
        if (startPositionCell.isEmpty()) {
            throw new IllegalArgumentException("출발 위치에 기물이 존재하지 않습니다.");
        }
        if (startPositionCell.teamColor() != teamColor) {
            throw new IllegalArgumentException("자신의 기물이 아닙니다.");
        }
    }

    private void validateMoveRoute(Cell startPositionCell, MoveRoute moveRoute) {
        if (!startPositionCell.canMoveTo(moveRoute, this)) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
    }

    public boolean isAnyPieceExistsOnRouteBeforeDestination(MoveRoute moveRoute) {
        Position movingPosition = moveRoute.nextPositionOfStartPosition();
        List<Position> canMovePositions = new ArrayList<>();
        while (!movingPosition.equals(moveRoute.destination())) {
            canMovePositions.add(movingPosition);
            movingPosition = movingPosition.move(moveRoute.direction());
        }
        return canMovePositions.stream()
            .map(cells::get)
            .anyMatch(cell -> !cell.isEmpty());
    }

    public boolean isAnyPieceExistsInCell(Position position) {
        Cell cell = findCell(position);
        return !cell.isEmpty();
    }

    public boolean isEnemyExists(Position position, TeamColor teamColor) {
        Cell cell = findCell(position);
        return !cell.isEmpty() && cell.teamColor() != teamColor;
    }

    public boolean isOwnPieceExistsInCell(Position position, TeamColor teamColor) {
        Cell cell = findCell(position);
        if (cell.isEmpty()) {
            return false;
        }
        return cell.teamColor() == teamColor;
    }

    public boolean isCellEmptyOrEnemyExists(Position position, TeamColor teamColor) {
        Cell cell = findCell(position);
        return cell.isEmpty() || cell.teamColor() != teamColor;
    }

    public boolean isKingDead() {
        return cells.values().stream()
            .filter(cell -> !cell.isEmpty() && cell.pieceType() == KING)
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
            Cell cell = cells.get(Position.of(file, rank));
            cellsStatus.add(cell.status());
        }
    }
}