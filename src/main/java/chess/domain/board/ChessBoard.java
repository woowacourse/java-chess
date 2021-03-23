package chess.domain.board;

import chess.domain.piece.Direction;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.TeamType;
import chess.domain.result.Result;

import java.util.*;

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

    public Result calculateScores() {
        double blackTeamScore = calculatePieceScores(TeamType.BLACK);
        double whiteTeamScore = calculatePieceScores(TeamType.WHITE);
        return Result.generateResult(blackTeamScore, whiteTeamScore);
    }

    private double calculatePieceScores(TeamType teamType) {
        double scoreTotalExceptPawn = calculateScoreTotalExceptPawn(teamType);
        double pawnScoreTotal = 0;
        for (File file : File.values()) {
            int pawnCounts = calculatePawnCounts(teamType, file);
            pawnScoreTotal += accumulatePawnScore(teamType, pawnCounts);
        }
        return scoreTotalExceptPawn + pawnScoreTotal;
    }

    private double calculateScoreTotalExceptPawn(TeamType teamType) {
        return cells.values()
                .stream()
                .filter(cell -> cell.isTeamOf(teamType) && !cell.hasPawn())
                .mapToDouble(Cell::getPieceScore)
                .sum();
    }

    private int calculatePawnCounts(TeamType teamType, File file) {
        return (int) Arrays.stream(Rank.values())
                .map(rank -> new Coordinate(file, rank))
                .map(cells::get)
                .filter(cell -> cell.isTeamOf(teamType) && cell.hasPawn())
                .count();
    }

    private double accumulatePawnScore(TeamType teamType, int pawnCounts) {
        if (pawnCounts == 0) {
            return 0;
        }
        Piece pawn = new Pawn(teamType);
        if (pawnCounts > 1) {
            return (pawn.getScore() / 2) * pawnCounts;
        }
        return pawn.getScore();
    }

    public TeamType findWinnerTeam() {
        if (!isKingCheckmate()) {
            throw new IllegalStateException("승리한 팀을 찾을 수 없습니다.");
        }
        return cells.values()
                .stream()
                .filter(Cell::hasKing)
                .map(Cell::getTeamType)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("승리한 팀을 찾을 수 없습니다."));
    }

    public boolean isKingCheckmate() {
        return cells.values()
                .stream()
                .filter(Cell::hasKing)
                .count() == KING_COUNTS_FOR_CHECKMATE_CONDITION;
    }

    public boolean hasPieceOnRouteBeforeDestination(Coordinate current, Coordinate destination) {
        List<Coordinate> possibleCoordinates = new ArrayList<>();
        Direction direction = current.evaluateDirection(destination);
        Coordinate nextCoordinate = current.move(direction);
        while (!nextCoordinate.equals(destination)) {
            possibleCoordinates.add(nextCoordinate);
            nextCoordinate = nextCoordinate.move(direction);
        }
        return possibleCoordinates.stream()
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

