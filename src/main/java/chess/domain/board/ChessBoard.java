package chess.domain.board;

import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.TeamType;
import chess.domain.result.Scores;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessBoard {
    private static final int CHESS_BOARD_SIZE = 64;
    private static final int KING_COUNTS_FOR_CHECKMATE_CONDITION = 1;

    private final Map<Coordinate, Cell> cells;

    public ChessBoard(Map<Coordinate, Cell> cells) {
        validateChessBoard(cells);
        this.cells = new HashMap<>(cells);
    }

    private void validateChessBoard(Map<Coordinate, Cell> cells) {
        if (cells.size() != CHESS_BOARD_SIZE) {
            throw new IllegalArgumentException("유효하지 않은 크기의 체스보드입니다.");
        }
    }

    public void move(Coordinate current, Coordinate destination, TeamType teamType) {
        Cell currentCell = cells.get(current);
        if (!currentCell.isTeamOf(teamType)) {
            throw new IllegalStateException("조작할 수 있는 기물이 없습니다.");
        }
        if (!currentCell.hasMovablePiece(this, current, destination)) {
            throw new IllegalStateException("이동할 수 없는 도착 위치 입니다.");
        }
        Cell targetCell = cells.get(destination);
        currentCell.movePieceTo(targetCell);
    }

    public Scores calculateScores() {
        Map<Coordinate, Piece> blackTeamPieces = filterPiecesByTeam(TeamType.BLACK);
        Map<Coordinate, Piece> whiteTeamPieces = filterPiecesByTeam(TeamType.WHITE);
        return Scores.generateResult(blackTeamPieces, whiteTeamPieces);
    }

    private Map<Coordinate, Piece> filterPiecesByTeam(TeamType teamType) {
        return cells.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isTeamOf(teamType))
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getPiece()));
    }

    public TeamType findWinnerTeam() {
        if (!isKingCheckmate()) {
            throw new IllegalStateException("승리한 팀을 찾을 수 없습니다.");
        }
        return filterKingsOnChessBoard()
                .map(Cell::getTeamType)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("승리한 팀을 찾을 수 없습니다."));
    }

    private Stream<Cell> filterKingsOnChessBoard() {
        return cells.values()
                .stream()
                .filter(cell -> cell.hasPieceOf(King.class));
    }

    public boolean isKingCheckmate() {
        return filterKingsOnChessBoard()
                .count() == KING_COUNTS_FOR_CHECKMATE_CONDITION;
    }

    public boolean hasPieceOnRouteBeforeDestination(Coordinate current, Coordinate destination) {
        return current.findRouteCoordinatesTo(destination)
                .stream()
                .map(cells::get)
                .anyMatch(cell -> !cell.isEmpty());
    }

    public boolean isEmptyOrHasEnemyOn(Coordinate coordinate, TeamType teamType) {
        return cells.get(coordinate).isEmptyOrHasEnemy(teamType);
    }

    public boolean isEmptyOn(Coordinate coordinate) {
        return cells.get(coordinate).isEmpty();
    }

    public boolean hasEnemyOn(Coordinate coordinate, TeamType teamType) {
        return cells.get(coordinate).hasEnemy(teamType);
    }

    public Map<Coordinate, Cell> getCells() {
        return Collections.unmodifiableMap(cells);
    }
}

