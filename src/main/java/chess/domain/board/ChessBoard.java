package chess.domain.board;

import chess.domain.piece.*;

import java.util.*;

public class ChessBoard {
    private static final int CHESS_BOARD_SIZE = 64;

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

    public void initializeDefaultPieces() {
        putPawns(TeamType.BLACK, Rank.SEVEN);
        putPiecesExceptPawn(TeamType.BLACK, Rank.EIGHT);
        putPawns(TeamType.WHITE, Rank.TWO);
        putPiecesExceptPawn(TeamType.WHITE, Rank.ONE);
    }

    private void putPawns(TeamType teamType, Rank pawnDefaultRank) {
        for (File file : File.values()) {
            Coordinate coordinate = new Coordinate(file, pawnDefaultRank);
            Cell emptyCell = cells.get(coordinate);
            emptyCell.put(new Pawn(teamType));
        }
    }

    private void putPiecesExceptPawn(TeamType teamType, Rank piecesDefaultRank) {
        List<Piece> pieces = generatePiecesExceptPawn(teamType);
        File[] files = File.values();
        for (int i = 0; i < files.length; i++) {
            Coordinate coordinate = new Coordinate(files[i], piecesDefaultRank);
            Cell emptyCell = cells.get(coordinate);
            emptyCell.put(pieces.get(i));
        }
    }

    private List<Piece> generatePiecesExceptPawn(TeamType teamType) {
        return Arrays.asList(new Rook(teamType), new Knight(teamType), new Bishop(teamType), new Queen(teamType),
                new King(teamType), new Bishop(teamType), new Knight(teamType), new Rook(teamType));
    }

    public void move(Coordinate current, Coordinate destination, TeamType teamType) {
        Cell currentCell = cells.get(current);
        if (!currentCell.isTeamOf(teamType)) {
            throw new IllegalArgumentException("우리 팀의 기물이 아닙니다.");
        }
        if (!currentCell.hasMovablePiece(this, current, destination)) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
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
                .filter(cell -> !cell.isEmpty() && cell.isTeamOf(teamType) && !cell.hasPawn())
                .mapToDouble(Cell::getPieceScore)
                .sum();
    }

    private int calculatePawnCounts(TeamType teamType, File file) {
        return (int) Arrays.stream(Rank.values())
                .map(rank -> new Coordinate(file, rank))
                .map(cells::get)
                .filter(cell -> !cell.isEmpty() && cell.isTeamOf(teamType) && cell.hasPawn())
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

    public boolean isKingCheckmate() {
        return cells.values()
                .stream()
                .filter(cell -> !cell.isEmpty() && cell.hasKing())
                .count() == 1L;
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

    public boolean isEmptyOrHasEnemyOn(Coordinate coordinate, TeamType teamType) {
        return cells.get(coordinate).isEmptyOrHasEnemy(teamType);
    }

    public boolean isEmptyOn(Coordinate coordinate) {
        return cells.get(coordinate).isEmpty();
    }

    public boolean hasEnemyOn(Coordinate coordinate, TeamType teamType) {
        return cells.get(coordinate).hasEnemy(teamType);
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

    public Map<Coordinate, Cell> getCells() {
        return Collections.unmodifiableMap(cells);
    }
}

