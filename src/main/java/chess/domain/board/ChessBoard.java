package chess.domain.board;

import chess.domain.piece.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ChessBoard {
    private static final int CHESS_BOARD_SIZE = 64;

    private final Map<Coordinate, Cell> cells;

    public ChessBoard(Map<Coordinate, Cell> cells) {
        validateChessBoard(cells);
        this.cells = cells;
    }

    private void validateChessBoard(Map<Coordinate, Cell> cells) {
        if (cells.size() != CHESS_BOARD_SIZE) {
            throw new IllegalArgumentException("옳바르지 않은 체스보드 크기입니다.");
        }
    }

    public void initializeDefaultPieces() {
        putPawns(TeamType.BLACK, Rank.SEVEN);
        putPiecesNotPawn(TeamType.BLACK, Rank.EIGHT);
        putPawns(TeamType.WHITE, Rank.TWO);
        putPiecesNotPawn(TeamType.WHITE, Rank.ONE);
    }

    private void putPawns(TeamType teamType, Rank rank) {
        for (File file : File.values()) {
            Coordinate coordinate = new Coordinate(file, rank);
            Cell emptyCell = cells.get(coordinate);
            emptyCell.put(new Pawn(teamType));
        }
    }

    private void putPiecesNotPawn(TeamType teamType, Rank rank) {
        List<Piece> pieces = createPiecesNotPawn(teamType);
        List<File> files = Arrays.asList(File.values());
        for (int i = 0; i < files.size(); i++) {
            Coordinate coordinate = new Coordinate(files.get(i), rank);
            Cell emptyCell = cells.get(coordinate);
            emptyCell.put(pieces.get(i));
        }
    }

    private List<Piece> createPiecesNotPawn(TeamType teamType) {
        return Arrays.asList(
                new Rook(teamType),
                new Knight(teamType),
                new Bishop(teamType),
                new Queen(teamType),
                new King(teamType),
                new Bishop(teamType),
                new Knight(teamType),
                new Rook(teamType)
        );
    }

    public Cell find(Coordinate coordinate) {
        return cells.get(coordinate);
    }

    public void move(Coordinate start, Coordinate destination, TeamType teamType) {
        Cell currentCell = cells.get(start);
        if (!currentCell.isTeamOf(teamType)) {
            throw new IllegalArgumentException("나의 기물이 아닙니다.");
        }
        if (!currentCell.hasMovablePiece(this, start, destination)) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        Cell targetCell = cells.get(destination);
        currentCell.movePieceTo(targetCell);
    }

    public Map<Coordinate, Cell> getCells() {
        return cells;
    }

    public Result calculateScores() {
        double blackTeamScore = calculatePieceScores(TeamType.BLACK);
        double whiteTeamScore = calculatePieceScores(TeamType.WHITE);
        return Result.generateResult(blackTeamScore, whiteTeamScore);
    }

    private double calculatePieceScores(TeamType teamType) {
        double scoreTotalExceptPawn = getScoreTotalExceptPawn(teamType);
        double pawnScores = 0;
        for (File file : File.values()) {
            int pawnCounts = getPawnCounts(teamType, file);
            pawnScores += getPawnScores(teamType, pawnCounts);
        }
        return scoreTotalExceptPawn + pawnScores;
    }

    private double getPawnScores(TeamType teamType, int pawnCounts) {
        if (pawnCounts == 0) {
            return 0;
        }
        if (pawnCounts == 1) {
            return new Pawn(teamType).getScore();
        }
        return (new Pawn(teamType).getScore() / 2) * pawnCounts;
    }

    private int getPawnCounts(TeamType teamType, File file) {
        return (int) Arrays.stream(Rank.values())
                .map(rank -> new Coordinate(file, rank))
                .map(cells::get)
                .filter(cell -> !cell.isEmpty())
                .filter(cell -> cell.isTeamOf(teamType) && cell.hasPawn())
                .count();
    }

    private double getScoreTotalExceptPawn(TeamType teamType) {
        return cells.values()
                .stream()
                .filter(cell -> !cell.isEmpty())
                .filter(cell -> cell.isTeamOf(teamType) && !cell.hasPawn())
                .mapToDouble(Cell::getPieceScore)
                .sum();
    }

    public boolean isKingCheckmate() {
        return cells.values().stream()
                .filter(cell -> !cell.isEmpty())
                .filter(Cell::hasKing)
                .count() == 1L;
    }

    public TeamType winner() {
        return cells.values().stream()
                .filter(Cell::hasKing)
                .findAny()
                .map(Cell::getTeamType)
                .orElseThrow(IllegalStateException::new);
    }

    public boolean isEmptyOrHasEnemyOn(Coordinate coordinate, TeamType teamType) {
        Cell cell = cells.get(coordinate);
        return cell.isEmptyOrHasEnemy(teamType);
    }

    public boolean isEmptyOn(Coordinate coordinate) {
        Cell cell = cells.get(coordinate);
        return cell.isEmpty();
    }

    public boolean hasEnemyOn(Coordinate coordinate, TeamType teamType) {
        Cell cell = cells.get(coordinate);
        return cell.hasEnemy(teamType);
    }

    public boolean hasPieceOnRouteBeforeDestination(Coordinate start, Coordinate destination, Direction direction) {
        List<Coordinate> possibleCoordinates = new ArrayList<>();
        Coordinate nextCoordinate = start.move(direction);
        while (!nextCoordinate.equals(destination)) {
            possibleCoordinates.add(nextCoordinate);
            nextCoordinate = nextCoordinate.move(direction);
        }
        return possibleCoordinates.stream()
                .map(cells::get)
                .anyMatch(cell -> !cell.isEmpty());
    }
}

