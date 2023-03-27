package chess.domain;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {

    private static final Map<ChessPiece, Double> SCORE_MAP = generateScoreBoard();

    private final Map<Position, ChessPiece> pieces;
    private final double score;

    private Result(Map<Position, ChessPiece> pieces, double score) {
        this.pieces = pieces;
        this.score = score;
    }

    public static Result of(Map<Position, ChessPiece> pieces, Color color) {
        double score = calculateInitialScore(pieces);

        score = scoreSubtractedPawnDuplication(pieces, color, score);

        return new Result(pieces, score);
    }

    private static double scoreSubtractedPawnDuplication(Map<Position, ChessPiece> pieces, Color color, double score) {
        List<Position> pawns = pickPawns(pieces, color);

        int numberOfPawns = pawns.size();

        int numberOfPawnsWithoutColumnDuplication = getNumberOfPawnWithoutColumnDuplication(pawns);

        if (numberOfPawns != numberOfPawnsWithoutColumnDuplication) {
            score -= 0.5 * numberOfPawns;
        }
        return score;
    }

    private static int getNumberOfPawnWithoutColumnDuplication(List<Position> pawns) {
        return (int) pawns.stream()
                .map(Position::getColumnSequence)
                .distinct()
                .count();
    }

    private static List<Position> pickPawns(Map<Position, ChessPiece> pieces, Color color) {
        return pieces.entrySet().stream()
                .filter(positionChessPieceEntry -> positionChessPieceEntry.getValue().equals(new Pawn(color)))
                .map(positionChessPieceEntry -> positionChessPieceEntry.getKey())
                .collect(Collectors.toList());
    }

    private static double calculateInitialScore(Map<Position, ChessPiece> pieces) {
        return pieces.values().stream()
                .mapToDouble(SCORE_MAP::get)
                .sum();
    }

    private static Map<ChessPiece, Double> generateScoreBoard() {
        Map<ChessPiece, Double> scoreMap = new HashMap<>();
        scoreMap.put(new Queen(Color.WHITE), 9.0);
        scoreMap.put(new Rook(Color.WHITE), 5.0);
        scoreMap.put(new Bishop(Color.WHITE), 3.0);
        scoreMap.put(new Knight(Color.WHITE), 2.5);
        scoreMap.put(new Pawn(Color.WHITE), 1.0);
        scoreMap.put(new King(Color.WHITE), 0.0);

        scoreMap.put(new Queen(Color.BLACK), 9.0);
        scoreMap.put(new Rook(Color.BLACK), 5.0);
        scoreMap.put(new Bishop(Color.BLACK), 3.0);
        scoreMap.put(new Knight(Color.BLACK), 2.5);
        scoreMap.put(new Pawn(Color.BLACK), 1.0);
        scoreMap.put(new King(Color.BLACK), 0.0);
        return scoreMap;
    }

    public double getScore() {
        return score;
    }
}
