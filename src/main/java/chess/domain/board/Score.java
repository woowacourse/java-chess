package chess.domain.board;

import chess.domain.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Score {

    private static final double DECREASE_SCORE_BY_DISTINCT = (-0.5);
    private static final int PIECE_HAVE_ONE_MORE_THAN_DUPLICATION_FILE = 1;
    private final double score;
    private final Color color;

    public Score(Map<Position, Piece> pieces, Color color) {
        score = calculateScoreOf(getPieceEntryOf(pieces, color));
        this.color = color;
    }

    private List<Map.Entry<Position, Piece>> getPieceEntryOf(Map<Position, Piece> pieces, Color color) {
        return pieces.entrySet().stream()
                .filter(entry -> entry.getValue().getColor() == color)
                .collect(Collectors.toList());
    }

    private double calculateScoreOf(List<Map.Entry<Position, Piece>> pieces) {
        if (dontHaveKing(pieces)) {
            return 0;
        }
        double result = 0;
        result += calculateScoreExceptPawn(pieces);
        result += calculatePawnScore(pieces);
        return result;
    }

    private boolean dontHaveKing(List<Map.Entry<Position, Piece>> pieces) {
        return !pieces.stream()
                .anyMatch(entry -> entry.getValue().isTypeOf(PieceType.KING));
    }

    private double calculateScoreExceptPawn(List<Map.Entry<Position, Piece>> pieces) {
        List<Map.Entry<Position, Piece>> piecesExceptPawn = getEntryOf(pieces,
                (entry) -> !entry.getValue().isTypeOf(PieceType.PAWN)
        );
        return getScoreSumOf(piecesExceptPawn);
    }

    private double getScoreSumOf(List<Map.Entry<Position, Piece>> piecesExceptPawn) {
        return piecesExceptPawn.stream()
                .mapToDouble(entry -> entry.getValue().getScore())
                .sum();
    }

    private List<Map.Entry<Position, Piece>> getEntryOf(List<Map.Entry<Position, Piece>> pieces, Predicate<Map.Entry<Position, Piece>> predicate) {
        return pieces.stream()
                .filter((entry) -> predicate.test(entry))
                .collect(Collectors.toList());

    }

    private double calculatePawnScore(List<Map.Entry<Position, Piece>> pieces) {
        List<Map.Entry<Position, Piece>> pawnPieces = getEntryOf(pieces,
                (entry) -> entry.getValue().isTypeOf(PieceType.PAWN)
        );
        double origin = getScoreSumOf(pawnPieces);
        return origin + getDecreaseScoreOfPawn(pawnPieces);
    }

    private double getDecreaseScoreOfPawn(List<Map.Entry<Position, Piece>> pieces) {
        long distinctFilePositionSize = pieces.stream().map(entry -> entry.getKey().getFile())
                .distinct()
                .count();

        if (distinctFilePositionSize != pieces.size()) {
            long distinctPieceCount = pieces.size() - distinctFilePositionSize + PIECE_HAVE_ONE_MORE_THAN_DUPLICATION_FILE;
            return distinctPieceCount * DECREASE_SCORE_BY_DISTINCT;
        }

        return 0;
    }

    public double getScore() {
        return score;
    }

    public Color getColor() {
        return color;
    }
}
