package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.InitBoardGenerator;
import chess.domain.board.position.Position;
import chess.domain.board.position.Xpoint;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ScoreCalculator {
    private final List<Map<Position, Piece>> board;

    public ScoreCalculator(Board board) {
        this.board = board.squares();
    }

    public double totalBlackScore() {
        return blackScoreExceptPawns() + blackScoreOfPawns();
    }

    private double blackScoreOfPawns() {
        double blackPawnScoreSum = 0;
        for (Xpoint xpoint : Xpoint.values()) {
            blackPawnScoreSum += verticalBlackPawnScore(xpoint);
        }
        return blackPawnScoreSum;
    }

    private double verticalBlackPawnScore(Xpoint xpoint) {
        long countOfBlackPawnsInVertical = countOfBlackPawnsInVertical(xpoint);
        if (countOfBlackPawnsInVertical > 1) {
            return countOfBlackPawnsInVertical * 0.5;
        }
        return countOfBlackPawnsInVertical;
    }

    private long countOfBlackPawnsInVertical(Xpoint xpoint) {
        List<Position> verticalPositions = verticalPositions(xpoint);
        return board.stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> verticalPositions.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .filter(Piece::isBlack)
                .filter(this::isPawn)
                .count();
    }

    private int blackScoreExceptPawns() {
        return board.stream()
                .flatMap(map -> map.values().stream())
                .filter(Piece::isBlack)
                .filter(this::isNotPawn) // Piece에 isNotPawn 구현해서 대체
                .mapToInt(piece -> 10) // Piece에 getScore 구현해서 대체
                .sum();
    }

    public double totalWhiteScore() {
        return whiteScoreExceptPawns() + whiteScoreOfPawns();
    }

    private double whiteScoreOfPawns() {
        double whitePawnScoreSum = 0;
        for (Xpoint xpoint : Xpoint.values()) {
            whitePawnScoreSum += verticalWhitePawnScore(xpoint);
        }
        return whitePawnScoreSum;
    }

    private double verticalWhitePawnScore(Xpoint xpoint) {
        long countOfWhitePawnsInVertical = countOfWhitePawnsInVertical(xpoint);
        if (countOfWhitePawnsInVertical > 1) {
            return countOfWhitePawnsInVertical * 0.5;
        }
        return countOfWhitePawnsInVertical;
    }

    private long countOfWhitePawnsInVertical(Xpoint xpoint) {
        List<Position> verticalPositions = verticalPositions(xpoint);
        return board.stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> verticalPositions.contains(entry.getKey()))
                .map(Map.Entry::getValue)
                .filter(Piece::isWhite)
                .filter(this::isPawn)
                .count();
    }

    private int whiteScoreExceptPawns() {
        return board.stream()
                .flatMap(map -> map.values().stream())
                .filter(Piece::isWhite)
                .filter(this::isNotPawn) // Piece에 isNotPawn 구현해서 대체
                .mapToInt(piece -> 10) // Piece에 getScore 구현해서 대체
                .sum();
    }

    private List<Position> verticalPositions(Xpoint xpoint) {
        return board.stream()
                .flatMap(map -> map.keySet().stream())
                .filter(position -> position.isSameX(xpoint))
                .collect(Collectors.toList());
    }

    private boolean isPawn(Piece piece) {
        return piece instanceof Pawn;
    }

    private boolean isNotPawn(Piece piece) {
        return !(piece instanceof Pawn);
    }

    // 테스트용
    public static void main(String[] args) {
        ScoreCalculator scoreCalculator = new ScoreCalculator(new Board(InitBoardGenerator.initLines()));
        System.out.println(scoreCalculator.totalWhiteScore());
        System.out.println(scoreCalculator.totalBlackScore());
    }
}
