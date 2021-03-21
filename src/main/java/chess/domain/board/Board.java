package chess.domain.board;

import static chess.domain.piece.type.PieceType.KING;

import chess.domain.game.MoveCommand;
import chess.domain.piece.Piece;
import chess.domain.player.type.TeamColor;
import chess.domain.position.MoveRoute;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Cell> cells = new HashMap<>();

    public void setPiece(Position position, Piece piece) {
        Cell cell = new Cell(piece);
        cells.put(position, cell);
    }

    public Cell findCell(Position position) {
        return cells.get(position);
    }

    public Piece findPiece(Position position) {
        return findCell(position).piece();
    }

    public void move(MoveCommand moveCommand) {
        Cell startPositionCell = cells.get(moveCommand.startPosition());
        validateOwnPiece(startPositionCell, moveCommand.teamColor());
        validateMoveRoute(startPositionCell, moveCommand.moveRoute());
        Cell destinationCell = cells.get(moveCommand.destination());
        startPositionCell.movePieceTo(destinationCell);
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
        if (!startPositionCell.isMovableTo(moveRoute, this)) {
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

    public boolean isNotCellEmpty(Position position) {
        Cell cell = findCell(position);
        return !cell.isEmpty();
    }

    public boolean isEnemyExists(Position position, TeamColor teamColor) {
        Cell cell = findCell(position);
        return !cell.isEmpty() && cell.teamColor() != teamColor;
    }

    public boolean isCellEmptyOrEnemyExists(Position position, TeamColor teamColor) {
        Cell cell = findCell(position);
        return cell.isEmpty() || cell.teamColor() != teamColor;
    }

    public boolean isKingDead() {
        return cells.values().stream()
            .filter(cell -> !cell.isEmpty() && cell.pieceType() == KING)
            .count() == 1;
    }
}

