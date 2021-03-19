package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.player.TeamType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        if (currentCoordinate.equals(targetCoordinate)) { //direction 예외 이동
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

    public List<Coordinate> findCheckCoordinatesNew(Coordinate myKingCoordinate, List<Coordinate> possibleCoordinates, TeamType teamType) {
        Piece myKing = cells.get(myKingCoordinate);
        cells.remove(myKingCoordinate);
        List<Coordinate> checkCoordinates = new ArrayList<>();
        Map<Coordinate, Piece> enemyPieces = getEnemyPieces(teamType);
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                Coordinate checkCoordinate = new Coordinate(file, rank);
                for (Coordinate enemyCoordinate : enemyPieces.keySet()) {
                    Piece enemyPiece = enemyPieces.get(enemyCoordinate);
                    if (enemyPiece.isMovableTo(this, enemyCoordinate, checkCoordinate)) {
                        checkCoordinates.add(checkCoordinate);
                    }
                }
            }
        }
        cells.put(myKingCoordinate, myKing);
        return checkCoordinates;
    }

    public List<Coordinate> findCheckCoordinates(List<Coordinate> myKingNextCoordinates, TeamType myTeamType) {
        List<Coordinate> checkCoordinates = new ArrayList<>();
        Map<Coordinate, Piece> enemyPieces = getEnemyPieces(myTeamType);
        enemyPieces.remove(getEnemyKingCoordinate(myTeamType));

        for (Coordinate myKingNextCoordinate : myKingNextCoordinates) { //킹 주위 8개 좌표
            for (Coordinate coordinate : enemyPieces.keySet()) { //coordinate : c5
                Piece piece = find(coordinate); //rook
                System.out.println("======");
                System.out.println("적 기물 이름 : " + piece.getName());
                System.out.println("적 기물 위치 : " + coordinate.getFile().getValue() + coordinate.getRank().getY());
                System.out.println("킹의 다음 위치 : " + myKingNextCoordinate.getFile().getValue() + myKingNextCoordinate.getRank().getY());
                try {
                    if (myKingNextCoordinate.equals(coordinate) || piece.isMovableTo(this, coordinate, myKingNextCoordinate)) {
                        System.out.println("적이 위 킹의 다음 위치로 이동할 수 있어, 체크 자리이다.");
                        checkCoordinates.add(coordinate);
                    }
                } catch (Exception ignored) {
                    System.out.println("적 이동 불가 방향 에러");
                }
            }
        }
        return checkCoordinates;
    }

    public Coordinate getEnemyKingCoordinate(TeamType myTeamType) {
        Map<Coordinate, Piece> enemyPieces = getEnemyPieces(myTeamType);
        return enemyPieces.keySet().stream()
            .filter(coordinate -> enemyPieces.get(coordinate) instanceof King)
            .findAny()
            .orElse(null);
    }

    private Map<Coordinate, Piece> getEnemyPieces(TeamType myTeamType) {
        return cells.keySet().stream()
        .filter(coordinate -> !cells.get(coordinate).isTeamOf(myTeamType))
        .collect(Collectors.toMap(coordinate -> coordinate, cells::get));
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
}

