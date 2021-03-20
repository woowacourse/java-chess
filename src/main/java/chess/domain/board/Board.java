package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.player.TeamType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Coordinate, Piece> cells = new HashMap<>();

    private Board() {
    }

    public static Board getInstance() {
        return new Board();
    }

    public Piece find(Coordinate coordinate) {
        return cells.get(coordinate);
    }

    public void initialize() {
        putPawns(TeamType.BLACK, Rank.SEVEN);
        putPiecesNotPawn(TeamType.BLACK, Rank.EIGHT);

        putPawns(TeamType.WHITE, Rank.TWO);
        putPiecesNotPawn(TeamType.WHITE, Rank.ONE);
    }

    private void putPawns(TeamType teamType, Rank rank) {
        for (File file : File.values()) {
            cells.put(new Coordinate(file, rank), new Pawn(teamType));
        }
    }

    private void putPiecesNotPawn(TeamType teamType, Rank rank) {
        List<File> files = Arrays.asList(File.values());
        List<Piece> pieces = createPiecesNotPawn(teamType);

        for (int i = 0; i < files.size(); i++) {
            Coordinate coordinate = new Coordinate(files.get(i), rank);
            cells.put(coordinate, pieces.get(i));
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

    public Piece find(String currentCoordinateInput, TeamType teamType) {
        Coordinate currentCoordinate = Coordinate.from(currentCoordinateInput);
        Piece piece = cells.get(currentCoordinate);
        if (piece == null) {
            throw new IllegalArgumentException("말이 존재하지 않습니다.");
        }
        if (!piece.isTeamOf(teamType)) {
            throw new IllegalArgumentException("자신의 말이 아닙니다.");
        }
        return piece;
    }

    public void move(Coordinate currentCoordinate, Coordinate targetCoordinate, TeamType teamType) {
        if (currentCoordinate.equals(targetCoordinate)) {
            throw new IllegalArgumentException();
        }
        Piece piece = find(currentCoordinate);
        if (piece == null || !piece.isTeamOf(teamType)) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        if (!piece.isMovableTo(this, currentCoordinate, targetCoordinate)) {
            throw new IllegalArgumentException("이동할 수 없는 도착 위치 입니다.");
        }
        cells.put(targetCoordinate, piece);
        cells.remove(currentCoordinate);
    }

    public Map<Coordinate, Piece> getCells() {
        return cells;
    }

    public void put(Piece piece, Coordinate coordinate) {
        cells.put(coordinate, piece);
    }

    public Result calculateScores() {
        double blackTeamScore = calculatePieceScores(TeamType.BLACK);
        double whiteTeamScore = calculatePieceScores(TeamType.WHITE);
        return Result.generateResult(blackTeamScore, whiteTeamScore);
    }

    private double calculatePieceScores(TeamType teamType) {
        double scoreTotalExceptPawn = cells.values()
            .stream()
            .filter(piece -> piece.isTeamOf(teamType) && !piece.isPawn())
            .mapToDouble(Piece::getScore)
            .sum();

        double pawnScores = 0;
        for (File file : File.values()) {
            int pawnCounts = 0;
            for (Rank rank : Rank.values()) {
                Coordinate coordinate = new Coordinate(file, rank);
                Piece piece = cells.get(coordinate);
                if (piece != null && piece.isTeamOf(teamType) && piece.isPawn()) {
                    pawnCounts++;
                }
            }
            if (pawnCounts == 0) {
                continue;
            }
            if (pawnCounts == 1) {
                pawnScores += new Pawn(teamType).getScore();
            } else {
                pawnScores += (new Pawn(teamType).getScore() / 2) * pawnCounts;
            }
        }
        return scoreTotalExceptPawn + pawnScores;
    }

    public boolean isKingCheckmate() {
        return cells.values().stream()
            .filter(Piece::isKing)
            .count() == 1L;
    }

    public TeamType winner() {
        return cells.values().stream()
            .filter(Piece::isKing)
            .findAny()
            .map(Piece::getTeamType)
            .orElseThrow(IllegalStateException::new);
    }
}

