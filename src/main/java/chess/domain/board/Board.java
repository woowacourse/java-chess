package chess.domain.board;

import static chess.domain.piece.type.PieceType.KING;

import chess.domain.board.setting.BoardSetting;
import chess.domain.piece.Piece;
import chess.domain.piece.type.PieceWithColorType;
import chess.domain.player.Players;
import chess.domain.player.score.Scores;
import chess.domain.player.type.TeamColor;
import chess.domain.position.MoveRoute;
import chess.domain.position.Position;
import chess.domain.position.cache.PositionsCache;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Players players = new Players();
    private final Map<Position, Cell> cells = new HashMap<>();

    public Board(BoardSetting boardSetting) {
        List<PieceWithColorType> piecesSetting = boardSetting.piecesSetting();
        for (int index = 0; index < piecesSetting.size(); index++) {
            setBoard(piecesSetting, index);
        }
    }

    private void setBoard(List<PieceWithColorType> piecesSetting, int index) {
        Position position = PositionsCache.get(index);
        Cell cell = new Cell(piecesSetting.get(index));
        cells.put(position, cell);
        if (!cell.isEmpty()) {
            players.give(cell.piece(), position);
        }
    }

    public Cell find(Position position) {
        return cells.get(position);
    }

    public void move(MoveRoute moveRoute, TeamColor currentTurnTeamColor) {
        Cell startPositionCell = cells.get(moveRoute.startPosition());
        validateOwnPiece(currentTurnTeamColor, startPositionCell);
        validateDestination(moveRoute, startPositionCell);

        Cell destinationCell = cells.get(moveRoute.destination());
        if (!destinationCell.isEmpty()) {
            Piece deadPiece = destinationCell.piece();
            removeDeadPieceOfPlayer(deadPiece, moveRoute.destination());
        }
        startPositionCell.movePieceTo(destinationCell);
    }


    private void validateOwnPiece(TeamColor currentTurnTeamColor, Cell startPositionCell) {
        if (startPositionCell.isEmpty()) {
            throw new IllegalArgumentException("출발 위치에 기물이 존재하지 않습니다.");
        }
        if (!(startPositionCell.teamColor() == currentTurnTeamColor)) {
            throw new IllegalArgumentException("자신의 기물이 아닙니다.");
        }
    }

    private void validateDestination(MoveRoute moveRoute, Cell startPositionCell) {
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
        Cell cell = find(position);
        return !cell.isEmpty();
    }

    public boolean isEnemyExists(Position position, TeamColor teamColor) {
        Cell cell = find(position);
        return !cell.isEmpty() && cell.teamColor() != teamColor;
    }

    public boolean isCellEmptyOrEnemyExists(Position position, TeamColor teamColor) {
        Cell cell = find(position);
        return cell.isEmpty() || cell.teamColor() != teamColor;
    }

    private void removeDeadPieceOfPlayer(Piece deadPiece, Position position) {
        players.remove(deadPiece, position);
    }

    public boolean isKingDead() {
        return cells.values().stream()
            .filter(cell -> !cell.isEmpty() && cell.pieceType() == KING)
            .count() == 1;
    }

    public Scores scores() {
        return players.scores();
    }
}

