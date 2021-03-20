package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.TeamType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Coordinate, Cell> cells = new HashMap<>();

    private Board() {
    }

    public static Board getInstance() {
        Board board = new Board();
        board.createEmptyCells();
        return board;
    }

    public void initialize() {
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

    private void createEmptyCells() {
        for (File file : File.values()) {
            createCellsOnFile(file);
        }
    }

    private void createCellsOnFile(File file) {
        for (Rank rank : Rank.values()) {
            cells.put(new Coordinate(file, rank), new Cell());
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

    public void move(Coordinate currentCoordinate, Coordinate targetCoordinate, TeamType teamType) {
        Cell currentCell = cells.get(currentCoordinate);
        if (!currentCell.isTeamOf(teamType)) {
            throw new IllegalArgumentException("나의 기물이 아닙니다.");
        }
        if (!currentCell.isMovableTo(this, currentCoordinate, targetCoordinate)) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        Cell targetCell = cells.get(targetCoordinate);
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
            .filter(cell -> cell.isTeamOf(teamType) && cell.hasPawn())
            .count();
    }

    private double getScoreTotalExceptPawn(TeamType teamType) {
        return cells.values()
            .stream()
            .filter(cell -> cell.isTeamOf(teamType) && !cell.hasPawn())
            .mapToDouble(Cell::getPieceScore)
            .sum();
    }

    public boolean isKingCheckmate() {
        return cells.values().stream()
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

    public boolean isMovable(Coordinate nextCoordinate, TeamType teamType) {
        Cell cell = cells.get(nextCoordinate);
        return cell.isMovable(teamType);
    }

    public boolean hasPieceOnRouteBeforeDestination(Coordinate currentCoordinate,
        Coordinate targetCoordinate, Direction moveCommandDirection) {
        Coordinate movingCoordinate = currentCoordinate.move(moveCommandDirection);
        List<Coordinate> possibleCoordinates = new ArrayList<>();
        while (!movingCoordinate.equals(targetCoordinate)) {
            possibleCoordinates.add(movingCoordinate);
            movingCoordinate = movingCoordinate.move(moveCommandDirection);
        }
        return possibleCoordinates.stream()
            .map(cells::get)
            .anyMatch(cell -> !cell.isEmpty());
    }
}

